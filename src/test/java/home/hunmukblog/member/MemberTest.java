package home.hunmukblog.member;

import home.hunmukblog.web.api.service.MemberService;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MemberTest {

    @Autowired
    MemberService memberService;

    @Test
    @DisplayName("회원 도메인 테스트")
    public void test(){



    }
}
