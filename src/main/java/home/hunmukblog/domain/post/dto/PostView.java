package home.hunmukblog.domain.post.dto;

import home.hunmukblog.domain.member.dto.MemberView;
import home.hunmukblog.domain.member.entity.Member;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class PostView {

    private Long id;
    private String title;
    private String content;

    private String regId;
    private String modId;
    private String regName;
    private String modName;

    private LocalDateTime regDt;
    private LocalDateTime modDt;

}
