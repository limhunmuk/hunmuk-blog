package home.hunmukblog.domain.post;

import home.hunmukblog.domain.member.MemberRepositoryCustom;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface PostRepository extends JpaRepository<Post, Long>,
                                            QuerydslPredicateExecutor<Post>, PostRepositoryCustom {

}
