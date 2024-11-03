package home.hunmukblog.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@Getter
@Setter
@Entity
@Table(name = "member")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mem_id", nullable = false)
    private Long id;

    @Column(name = "login_id", length = 45)
    private String loginId;

    @Column(name = "pwd", length = 100)
    private String pwd;

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return AuthorityUtils.createAuthorityList("ROLE_USER");
    }

    @Override
    public String getPassword() {
        return  this.pwd;
    }

    @Override
    public String getUsername() {
        return this.loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}