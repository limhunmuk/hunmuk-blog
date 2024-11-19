package home.hunmukblog.domain.inquiry.dto;

import lombok.Data;

@Data
public class InquiryCreate {

    private String title;
    private String content;

    public InquiryCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
