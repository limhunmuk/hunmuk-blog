package home.hunmukblog.domain.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;
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
    public Page<Post> searchPostList(PostSearch postSearch, Pageable pageable) {
        List<Post> content = queryFactory
                .selectFrom(post)
                .where(
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
