package home.hunmukblog.domain.post.dto;

import lombok.Data;

@Data
public class PostCreate {

    private String title;
    private String content;

    public PostCreate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
