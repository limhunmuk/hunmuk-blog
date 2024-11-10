package home.hunmukblog.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.member.dto.MemberCreate;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.web.api.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MemberServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    MemberService memberService;

    JPAQueryFactory query;

    @BeforeEach
    public void before(){
        query = new JPAQueryFactory(em);
    }

    @Test
    public void test(){

        MemberCreate memberCreate = MemberCreate.builder()
                .name("아무개")
                .age("20")
                .build();

        Member member = memberService.saveMember(memberCreate);

        Member member1 = memberService.searchMemberDetail(member.getId());

        Assertions.assertEquals(member.getId(), member1.getId());
        Assertions.assertEquals(member.getName(), member1.getName());

    }

}