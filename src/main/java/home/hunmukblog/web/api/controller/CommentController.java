package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentCreate;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentSearch;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentUpdate;
import home.hunmukblog.domain.inquirycomment.dto.InquiryCommentView;
import home.hunmukblog.domain.inquirycomment.entity.InquiryComment;
import home.hunmukblog.web.api.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    /**
     * 댓글 목록 조회
     * @param search
     * @return
     */
    @GetMapping("/api/inquiry/{id}/comment")
    public List<InquiryCommentView> inquiryList(InquiryCommentSearch search, @PathVariable Long id) {

        if(id == null) {
            throw new IllegalArgumentException("문의 일련번호가 필요합니다.");
        }

        search.setInquiryId(id);
        return commentService.searchCommentList(search);
    }

    /**
     * 댓글 상세 조회
     * @param commentId
     * @return
     */
    @GetMapping("/api/comment/{commentId}")
    public InquiryComment inquiryDetail(@PathVariable Long commentId) {

        return commentService.searchCommentDetail(commentId);
    }

    /**
     * 댓글 등록
     * @param request
     * @return
     */
    @PostMapping("/api/inquiry/{id}/comment")
    public ResponseEntity<?> CommentCreate(@RequestBody InquiryCommentCreate request, @PathVariable Long id) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        InquiryComment comment = commentService.saveComment(request);
        return ResponseEntity.ok().body(comment);
    }

    /**
     * 댓글 수정
     * @param commentId
     * @param request
     * @return
     */
    @PutMapping("/api/inquiry/comment/{commentId}")
    public InquiryComment CommentUpdate(@PathVariable Long commentId, @RequestBody InquiryCommentUpdate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        InquiryComment comment = commentService.updateComment(commentId, request);
        return comment;
    }

    /**
     * 댓글 삭제
     * @param commentId
     * @return
     */
    @DeleteMapping("/api/inquiry/comment/{commentId}")
    public void CommentDelete(@PathVariable Long commentId) {

        System.out.println(" ============================================== " );
        System.out.println("id = " + commentId);
        System.out.println(" ============================================== " );
        commentService.deleteComment(commentId);
    }
}
