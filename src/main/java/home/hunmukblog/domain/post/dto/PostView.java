package home.hunmukblog.domain.post.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostView {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime regDt;

}
