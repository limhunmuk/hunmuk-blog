package home.hunmukblog.domain.inquirycomment.dto;

import lombok.Data;

@Data
public class InquiryCommentUpdate {

    private String content;

    public InquiryCommentUpdate(String content) {
        this.content = content;
    }

}
