package home.hunmukblog.domain.inquiry;

import home.hunmukblog.domain.inquiry.dto.InquiryCreate;
import home.hunmukblog.domain.inquiry.dto.InquirySearch;
import home.hunmukblog.domain.inquiry.dto.InquiryUpdate;
import home.hunmukblog.domain.inquiry.dto.InquiryView;
import home.hunmukblog.domain.inquiry.entity.Inquiry;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;

import java.util.List;

public interface InquiryRepositoryCustom {

    List<InquiryView> searchInquiryList(InquirySearch search);

}
