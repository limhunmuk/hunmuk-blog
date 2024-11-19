package home.hunmukblog.domain.inquiry;

import home.hunmukblog.domain.inquiry.entity.Inquiry;
import home.hunmukblog.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InquiryRepository extends JpaRepository<Inquiry, Long>,
                                            QuerydslPredicateExecutor<Inquiry>, InquiryRepositoryCustom {

}
