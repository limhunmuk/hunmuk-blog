package home.hunmukblog.domain.inquirycomment;

import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentView;

import java.util.List;

public interface InquiryCommentRepositoryCustom {

    List<InquiryCommentView> searchInquiryCommentList(Long id);

}
