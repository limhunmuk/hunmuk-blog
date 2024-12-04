package home.hunmukblog.domain.inquirycomment.dto;

import lombok.Data;

@Data
public class InquiryCommentCreate {

    private Long inquiryId;
    private String content;
}
