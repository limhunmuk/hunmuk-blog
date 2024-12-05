package home.hunmukblog.web.api.service;

import home.hunmukblog.domain.inquirycomment.InquiryCommentRepository;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentCreate;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentSearch;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentUpdate;
import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final InquiryCommentRepository commentRepository;

    /**
     * 댓글 목록 조회
     * @param search
     * @return
     */
    public List<InquiryComment> searchCommentList(InquiryCommentSearch search) {
        return commentRepository.searchInquiryCommentList(search.getInquiryId());
    }

    /**
     * 댓글 상세 조회
     * @param id
     * @return
     */
    public InquiryComment searchCommentDetail(Long id) {
        return commentRepository.findById(id).orElse(null);
    }

    /**
     * 댓글 등록
     * @param request
     * @return
     */
    public InquiryComment saveComment(InquiryCommentCreate request) {

        InquiryComment comment = InquiryComment.builder()
                .content(request.getContent())
                .regId("auto")
                .regDt(LocalDateTime.now())
                .build();

        return commentRepository.save(comment);
    }

    /**
     * 댓글 수정
     * @param commentId
     * @param request
     * @return
     */
    public InquiryComment updateComment(Long commentId, InquiryCommentUpdate request) {

        InquiryComment comment = commentRepository.findById(commentId).orElse(null);

            if (comment == null) {
                throw new IllegalArgumentException("해당 댓글이 존재하지 않습니다.");
            }

            comment.setContent(request.getContent());

            return commentRepository.save(comment);
    }

    /**
     * 댓글 삭제
     * @param id
     */
    public void deleteComment(Long id) {

        InquiryComment comment = commentRepository.findById(id).orElse(null);

        if (comment == null) {
            throw new IllegalArgumentException("해당 문의글이 존재하지 않습니다.");
        }
        commentRepository.deleteById(id);
    }

}
