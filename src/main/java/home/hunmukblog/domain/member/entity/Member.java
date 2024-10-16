package home.hunmukblog.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "member")
public class Member {
    @Id
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

}