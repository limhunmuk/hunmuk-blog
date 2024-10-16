package home.hunmukblog.domain.member.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MemberView {

    private Long id;
    private String name;
    private Integer age;

    private LocalDateTime regDt;
}
