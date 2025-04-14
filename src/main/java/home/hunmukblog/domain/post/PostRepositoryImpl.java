package home.hunmukblog.domain.post;

import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostView;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.web.response.PostResponse;
import jakarta.persistence.EntityManager;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static home.hunmukblog.domain.post.entity.QPost.post;
import static org.springframework.util.StringUtils.hasText;

@Repository
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public PostRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public Page<PostResponse> searchPostList(PostSearch postSearch, Pageable pageable) {
        List<PostResponse> content = queryFactory
                .select(Projections.fields(PostResponse.class,
                        post.id,
                        post.content,
                        post.title,
                        post.regUser.loginId.as("regId"),
                        post.regUser.name.as("regName"),
                        post.regDt,
                        post.modUser.loginId.as("modId"),
                        post.modUser.name.as("modName"),
                        post.modDt
                    )
                )
                .from(post)
                .leftJoin(post.regUser)
                .leftJoin(post.modUser)
                .where(
                        post.regUser.id.isNotNull(),
                        containTitle(postSearch.getTitle()),
                        containContent(postSearch.getContent())
                )
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        JPAQuery<Long> countQuery = queryFactory
                .select(
                        post.count()
                )
                .from(post)
                .where(
                        containTitle(postSearch.getTitle()),
                        containContent(postSearch.getContent())
                );

        return PageableExecutionUtils.getPage(content, pageable, countQuery::fetchCount);
    }

    @Override
    public Post searchPostDetail(Long id) {
        return queryFactory
                .selectFrom(post)
                .where(post.id.eq(id))
                .fetchOne();
    }

    private BooleanExpression containTitle(String title) {
        return hasText(title) ? post.title.contains(title) : null;
    }

    private BooleanExpression containContent(String content) {
        return hasText(content) ? post.content.contains(content) : null;
    }
}
