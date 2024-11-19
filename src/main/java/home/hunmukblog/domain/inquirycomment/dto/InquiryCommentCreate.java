package home.hunmukblog.domain.inquirycomment.dto;

import lombok.Data;

@Data
public class InquiryCommentCreate {

    private String content;

    public InquiryCommentCreate(String content) {
        this.content = content;
    }
}
