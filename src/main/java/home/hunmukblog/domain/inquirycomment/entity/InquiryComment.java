package home.hunmukblog.domain.inquirycomment.entity;

import home.hunmukblog.domain.inquiry.entity.Inquiry;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "inquiry_comment", schema = "hlog")
@SuperBuilder
@NoArgsConstructor
public class InquiryComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "inquiry_comment_id", nullable = false)
    private Integer id;

    //@Column(name = "inquiry_id")
    //private Integer inquiryId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inquiry_id")
    private Inquiry inquiry;

    @Lob
    @Column(name = "content")
    private String content;

    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    @Column(name = "reg_id", length = 45)
    private String regId;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Column(name = "mod_id", length = 45)
    private String modId;

}