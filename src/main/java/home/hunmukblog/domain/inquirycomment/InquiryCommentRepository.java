package home.hunmukblog.domain.inquirycomment;

import home.hunmukblog.domain.inquiry.entity.Inquiry;
import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;
import home.hunmukblog.domain.post.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Long>,
                                            QuerydslPredicateExecutor<InquiryComment>, InquiryCommentRepositoryCustom {

}
