package home.hunmukblog.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id", nullable = false)
    private Long id;

    @Column(name = "name", length = 45)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "reg_dt")
    private LocalDateTime regDt;

    @Column(name = "reg_id", length = 45)
    private String regId;

    @Column(name = "mod_dt")
    private LocalDateTime modDt;

    @Column(name = "mod_id", length = 45)
    private String modId;

    @Builder
    public Member(Long id, String name, Integer age, LocalDateTime regDt, String regId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.regDt = regDt;
        this.regId = regId;
    }

}