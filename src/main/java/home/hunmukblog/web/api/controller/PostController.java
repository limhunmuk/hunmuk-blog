package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostUpdate;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.web.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 목록 조회
     * @param condition
     * @return
     */
    @GetMapping("/api/post")
    public Page<Post> posts(PostSearch condition) {

        int setPage = 1;
        int setSize = 10;

        String page = condition.getPage();
        String size = condition.getSize();

        if (hasText(page) && page.matches("^\\d+$")) setPage = Math.max(Integer.parseInt(page), 1);
        if (hasText(size) && size.matches("^\\d+$")) setSize = Math.max(Integer.parseInt(size), 1);

        condition.setPage(String.valueOf(setPage));
        condition.setSize(String.valueOf(setSize));

        Pageable pageable = PageRequest.of(setPage - 1, setSize, Sort.by("id").descending());

        return postService.searchPostList(condition, pageable);
    }

    /**
     * 게시글 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/api/post/{id}")
    public Post postDetail(@PathVariable Long id) {

        return postService.searchPostDetail(id);
    }

    /**
     * 게시글 등록
     * @param request
     * @return
     */
    @PostMapping("/api/post")
    public ResponseEntity<?> PostCreate(@RequestBody PostCreate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        Post post = postService.savePost(request);
        return ResponseEntity.ok().body(post);
    }

    /**
     * 게시글 수정
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/api/post/{id}")
    public Post PostUpdate(@PathVariable Long id, @RequestBody PostUpdate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        return postService.updatePost(id, request);
    }

    /**
     * 게시글 삭제
     * @param id
     * @return
     */
    @DeleteMapping("/api/post/{id}")
    public void PostDelete(@PathVariable Long id) {

        System.out.println(" ============================================== " );
        System.out.println("id = " + id);
        System.out.println(" ============================================== " );
        postService.deletePost(id);
    }
}
