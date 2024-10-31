package home.hunmukblog.domain.post.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    @Column(name = "reg_id", length = 45)
    private String regId;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Column(name = "mod_id", length = 45)
    private String modId;

    @Builder
    public Post(Integer id, String title, String content, Integer viewCnt, LocalDateTime regDt, String regId, LocalDateTime modDt, String modId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.viewCnt = viewCnt;
        this.regDt = regDt;
        this.regId = regId;
        this.modDt = modDt;
        this.modId = modId;
    }

}