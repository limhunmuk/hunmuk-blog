package home.hunmukblog.domain.inquirycomment;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentView;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static home.hunmukblog.domain.inquirycomment.entity.QInquiryComment.inquiryComment;

@SpringBootTest
class InquiryCommentRepositoryImplTest {

    @Autowired
    EntityManager em;

    JPAQueryFactory query;

    @BeforeEach
    public void before(){
        query = new JPAQueryFactory(em);

        System.out.println("======= 초기화 ======" );
    }

    @Test
    @DisplayName("문의 댓글 목록 조회")
    public void getComment(){

        List<InquiryCommentView> list = query
                .select(Projections.fields(InquiryCommentView.class,
                        inquiryComment.id,
                        inquiryComment.content,
                        inquiryComment.regId,
                        inquiryComment.regDt,
                        inquiryComment.inquiry.id.as("inquiryId")
                ))
                .from(inquiryComment)
                .where(inquiryComment.inquiry.id.eq(5L))
                .fetch();

        list.forEach((f)->{
            System.out.println("f = " + f);
        });

    }

}