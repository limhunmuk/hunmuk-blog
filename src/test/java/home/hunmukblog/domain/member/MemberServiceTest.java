package home.hunmukblog.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.member.dto.MemberCreate;
import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.dto.MemberView;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.web.api.service.MemberService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Random;

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

    //@Test
    public void test(){

        Random random = new Random();
        MemberCreate memberCreate = MemberCreate.builder()
                .name("생성회원_"+(random.nextInt(10)))
                .loginId("login_id_"+(random.nextInt(100)))
                .age(String.valueOf(random.nextInt(10)))
                .build();

        Member member = memberService.saveMember(memberCreate);

        MemberSearch search = new MemberSearch();
        search.setId(member.getId());
        MemberView memberView = memberService.searchMemberDetail(search);

        Assertions.assertEquals(member.getId(), memberView.getId());
        Assertions.assertEquals(member.getName(), memberView.getName());

    }

}