package home.hunmukblog.domain.post;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "post")
public class Post {

    @Id
    @Column(name = "post_id", nullable = false)
    private Integer id;

    @Column(name = "tiitle")
    private String tiitle;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "view_cnt")
    private Integer viewCnt;

    @Column(name = "reg_dt")
    private Instant regDt;

    @Column(name = "reg_id", length = 45)
    private String regId;

    @Column(name = "mod_dt")
    private Instant modDt;

    @Column(name = "mod_id", length = 45)
    private String modId;

}