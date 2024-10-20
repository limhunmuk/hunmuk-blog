package home.hunmukblog.domain.member.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MemberCreate {

    private String name;
    private Integer age;
}
