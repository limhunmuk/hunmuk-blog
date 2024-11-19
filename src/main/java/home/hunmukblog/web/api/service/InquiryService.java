package home.hunmukblog.web.api.service;

import home.hunmukblog.domain.inquiry.InquiryRepository;
import home.hunmukblog.domain.inquiry.InquiryRepositoryImpl;
import home.hunmukblog.domain.inquiry.dto.InquiryCreate;
import home.hunmukblog.domain.inquiry.dto.InquirySearch;
import home.hunmukblog.domain.inquiry.dto.InquiryUpdate;
import home.hunmukblog.domain.inquiry.entity.Inquiry;
import home.hunmukblog.domain.post.PostRepository;
import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostUpdate;
import home.hunmukblog.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    /**
     * 문의글 목록 조회
     * @param search
     * @return
     */
    public List<Inquiry> searchInquiryList(InquirySearch search) {
        return inquiryRepository.findAll();
    }

    /**
     * 문의글 상세 조회
     * @param id
     * @return
     */
    public Inquiry searcInquiryDetail(Long id) {
        return inquiryRepository.findById(id).orElse(null);
    }

    /**
     * 문의글 등록
     * @param request
     * @return
     */
    public Inquiry saveInquiry(InquiryCreate request) {

        Inquiry inquiry = Inquiry.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .regId("auto")
                .regDt(LocalDateTime.now())
                .build();

        return inquiryRepository.save(inquiry);
    }

    /**
     * 문의글 수정
     * @param id
     * @param request
     * @return
     */
    public Inquiry updateInquiry(Long id, InquiryUpdate request) {

            Inquiry inquiry = inquiryRepository.findById(id).orElse(null);

            if (inquiry == null) {
                throw new IllegalArgumentException("해당 문의글이 존재하지 않습니다.");
            }

            inquiry.setTitle(request.getTitle());
            inquiry.setContent(request.getContent());

            return inquiryRepository.save(inquiry);
    }

    /**
     * 문의글 삭제
     * @param id
     */
    public void deleteInquiry(Long id) {

        Inquiry inquiry = inquiryRepository.findById(id).orElse(null);

        if (inquiry == null) {
            throw new IllegalArgumentException("해당 문의글이 존재하지 않습니다.");
        }
        inquiryRepository.deleteById(id);
    }

}
