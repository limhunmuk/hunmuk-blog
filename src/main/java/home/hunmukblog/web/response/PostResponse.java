package home.hunmukblog.web.response;

import home.hunmukblog.domain.post.entity.Post;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

/**
 * 서비스 정책에 맞는 클래스
 */
@Getter
public class PostResponse {

    private Long id;
    private String title;
    private String content;

    private String regId;
    private String modId;
    private String regName;
    private String modName;

    private LocalDateTime regDt;
    private LocalDateTime modDt;

    /**
    public PostResponse(Post post) {
        this.id = post.getId();
        this.title = post.getTitle();
        this.content = post.getContent();
    }

    @Builder
    public PostResponse(Long id, String title, String content) {
        this.id = id;
        this.title = title.substring(0, Math.min(title.length(), 10));
        this.content = content;
    }
    */
}
