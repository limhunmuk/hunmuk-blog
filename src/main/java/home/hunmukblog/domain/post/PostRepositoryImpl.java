package home.hunmukblog.domain.post;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;
import jakarta.persistence.EntityManager;
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
    public List<Post> searchPostList(PostSearch postSearch) {
        return queryFactory
                .selectFrom(post)
                .where(
                        containTitle(postSearch.getTitle()),
                        containContent(postSearch.getContent())
                )
                .fetch();
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
