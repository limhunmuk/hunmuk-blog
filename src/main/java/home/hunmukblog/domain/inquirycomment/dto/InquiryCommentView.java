package home.hunmukblog.domain.inquirycomment.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InquiryCommentView {

    private Long inquiryId;
    private String content;
    private String regId;
    private LocalDateTime regDt;

}
