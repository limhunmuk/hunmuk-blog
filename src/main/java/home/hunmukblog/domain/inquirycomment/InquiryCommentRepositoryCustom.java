package home.hunmukblog.domain.inquirycomment;

import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;

import java.util.List;

public interface InquiryCommentRepositoryCustom {

    List<InquiryComment> searchInquiryCommentList(Long id);

}
