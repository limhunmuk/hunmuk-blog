package home.hunmukblog.domain.inquiry.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class InquiryView {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDt;

}
