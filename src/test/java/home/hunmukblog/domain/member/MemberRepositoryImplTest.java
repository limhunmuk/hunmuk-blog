package home.hunmukblog.domain.member;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static home.hunmukblog.domain.member.entity.QMember.member;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MemberRepositoryImplTest {


    @Autowired
    EntityManager em;

    @Autowired
    MemberRepository memberRepository;

    JPAQueryFactory query;


    @BeforeEach
    public void before(){
        query = new JPAQueryFactory(em);

        System.out.println("======= 초기화 ======" );
    }

    @Test
    @DisplayName("테스트")
    public void test(){

        System.out.println("테스트");
        query.select(member)
                .from(member)
                .fetch();
    }


}