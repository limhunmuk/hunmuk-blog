package home.hunmukblog.domain.post.entity;

import home.hunmukblog.domain.member.entity.Member;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@SuperBuilder
@Table(name = "post")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id", nullable = false)
    private Long id;

    @Column(name = "title")
    private String title;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    @ManyToOne
    @JoinColumn(name = "reg_id")
    private Member regUser;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Builder
    public Post(String title, String content, Member user) {
        this.title = title;
        this.content = content;
        this.regUser = user;
    }

    public PostEditor.PostEditorBuilder toEditor() {
        return PostEditor.builder()
                .title(title)
                .content(content);
    }

    public void edit(PostEditor postEditor) {
        title = postEditor.getTitle();
        content = postEditor.getContent();
    }

    @ManyToOne
    @JoinColumn(name = "mod_id")
    private Member modUser;


}