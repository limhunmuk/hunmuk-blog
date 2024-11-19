package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.inquiry.dto.InquiryCreate;
import home.hunmukblog.domain.inquiry.dto.InquirySearch;
import home.hunmukblog.domain.inquiry.dto.InquiryUpdate;
import home.hunmukblog.domain.inquiry.dto.InquiryView;
import home.hunmukblog.domain.inquiry.entity.Inquiry;
import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostUpdate;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.web.api.service.InquiryService;
import home.hunmukblog.web.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class InquiryController {

    private final InquiryService inquiryService;

    /**
     * 문의글 목록 조회
     * @param search
     * @return
     */
    @GetMapping("/api/inquiry")
    public List<Inquiry> inquiryList(InquirySearch search) {

        List<Inquiry> inquiries = inquiryService.searchInquiryList(search);
        return inquiries;
    }

    /**
     * 문의글 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/api/inquiry/{id}")
    public Inquiry inquiryDetail(@PathVariable Long id) {

        return inquiryService.searcInquiryDetail(id);
    }

    /**
     * 문의글 등록
     * @param request
     * @return
     */
    @PostMapping("/api/inquiry")
    public ResponseEntity<?> InquiryCreate(@RequestBody InquiryCreate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        Inquiry inquiry = inquiryService.saveInquiry(request);
        return ResponseEntity.ok().body(inquiry);
    }

    /**
     * 문의글 수정
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/api/inquiry/{id}")
    public Inquiry PostUpdate(@PathVariable Long id, @RequestBody InquiryUpdate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        return inquiryService.updateInquiry(id, request);
    }

    /**
     * 문의글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/inquiry/{id}")
    public void inquiryDelete(@PathVariable Long id) {

        System.out.println(" ============================================== " );
        System.out.println("id = " + id);
        System.out.println(" ============================================== " );
        inquiryService.deleteInquiry(id);
    }
}
