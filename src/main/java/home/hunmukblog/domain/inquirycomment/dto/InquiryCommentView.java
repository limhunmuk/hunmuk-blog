package home.hunmukblog.domain.inquirycomment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InquiryCommentView {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDt;

}
