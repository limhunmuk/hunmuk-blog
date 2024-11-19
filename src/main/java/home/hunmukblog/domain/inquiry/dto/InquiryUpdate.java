package home.hunmukblog.domain.inquiry.dto;

import lombok.Data;

@Data
public class InquiryUpdate {

    private String title;
    private String content;

    public InquiryUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
