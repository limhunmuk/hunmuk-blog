package home.hunmukblog.domain.inquiry;

import home.hunmukblog.domain.inquiry.entity.Inquiry;
import home.hunmukblog.domain.inquirycomment.InquiryCommentRepository;
import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

@SpringBootTest
public class InquiryTest {

    @Autowired
    InquiryRepository inquiryRepository;

    @Autowired
    InquiryCommentRepository inquiryCommentRepository;


    @Test
    @DisplayName("문의 등록")
    public void inquiry(){

        Inquiry inquiry = Inquiry.builder()
                .title("문의 제목 새글")
                .content("문의 내용")
                .viewCnt(0)
                .regId("hunmuk")
                //.regDt(LocalDateTime.now())
                .build();

        System.out.println("inquiry = " + inquiry);

        inquiryRepository.save(inquiry);

        InquiryComment comment = InquiryComment.builder()
                .inquiry(inquiry)
                .content("문의 댓글 내용 새글")
                .regId("hunmuk")
                .build();

        System.out.println("comment = " + comment);

        inquiryCommentRepository.save(comment);

    }

    @Test
    @DisplayName("문의 목록 조회")
    public void commentList(){

        List<InquiryComment> comment = inquiryCommentRepository.findByInquiryId(1L);
        comment.forEach((f)->{
            System.out.println("title = " + f.getInquiry().getTitle());
            System.out.println("conte = " + f.getInquiry().getContent());
            System.out.println(" ================= ");
            System.out.println("comment = " + f.getContent());
            System.out.println(" ================= ");

        });

    }

    @Transactional(readOnly = true)
    @Test
    @DisplayName("문의 목록 조회(역방향)")
    public void test(){

        Inquiry inquiry = inquiryRepository.findById(1L).get();
/**        List<InquiryComment> inquiryComments = inquiry.getInquiryComments();
        inquiryComments.forEach((f)->{
            System.out.println("f = " + f);
        });
 */

    }

    @Transactional(readOnly = true)
    @Test
    @DisplayName("문의 목록 조회(1+n)")
    public void nquery(){

        //List<Inquiry> all = inquiryRepository.findAll();
        List<Inquiry> all = inquiryRepository.findAllById(Collections.singleton(1L));

        System.out.println(" =================== n + 1 확인 해볼가 ??" );
        all.forEach((article)->{
            System.out.println("board t = " + article.getTitle());
            System.out.println("board cont = " + article.getContent());
           /**List<InquiryComment> inquiryComments = article.getInquiryComments();
            inquiryComments.forEach((comment)->{
                System.out.println("comment = " + comment);
            });
            */
        });

    }
}
