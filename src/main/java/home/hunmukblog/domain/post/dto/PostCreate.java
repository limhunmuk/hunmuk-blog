package home.hunmukblog.domain.post.dto;

import lombok.Data;

@Data
public class PostCreate {

    private String title;
    private String content;
    private String regId;

    public PostCreate(String title, String content, String regId) {
        this.title = title;
        this.content = content;
        this.regId = regId;
    }

}
