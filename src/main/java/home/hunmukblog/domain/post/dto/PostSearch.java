package home.hunmukblog.domain.post.dto;

import lombok.Data;

@Data
public class PostSearch {

    private String title;
    private String content;

    private String page;
    private String size;
}
