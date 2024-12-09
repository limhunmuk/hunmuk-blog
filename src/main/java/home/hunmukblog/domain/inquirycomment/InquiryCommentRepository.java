package home.hunmukblog.domain.inquirycomment;

import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;

public interface InquiryCommentRepository extends JpaRepository<InquiryComment, Long>,
                                            QuerydslPredicateExecutor<InquiryComment>, InquiryCommentRepositoryCustom {

    List<InquiryComment> findByInquiryId(Long inquiryId);

}
