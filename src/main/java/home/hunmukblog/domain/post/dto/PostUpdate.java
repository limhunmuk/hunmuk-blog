package home.hunmukblog.domain.post.dto;

import lombok.Data;

@Data
public class PostUpdate {

    private String title;
    private String content;

    public PostUpdate(String title, String content) {
        this.title = title;
        this.content = content;
    }

}
