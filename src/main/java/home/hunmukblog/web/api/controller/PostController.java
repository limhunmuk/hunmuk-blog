package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostUpdate;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.web.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    /**
     * 게시글 목록 조회
     * @param search
     * @return
     */
    @GetMapping("/post")
    public List<Post> posts(PostSearch search) {

        List<Post> posts = postService.searchPostList(search);
        return posts;
    }

    /**
     * 게시글 상세 조회
     * @param id
     * @return
     */
    @GetMapping("/post/{id}")
    public Post postDetail(@PathVariable Long id) {

        System.out.println("post detail id = " + id);
        return postService.searchPostDetail(id);
    }

    /**
     * 게시글 등록
     * @param request
     * @return
     */
    @PostMapping("/post")
    public Post PostCreate(@RequestBody PostCreate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        return postService.savePost(request);
    }

    /**
     * 게시글 수정
     * @param id
     * @param request
     * @return
     */
    @PutMapping("/post/{id}")
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
    @DeleteMapping("/post/{id}")
    public void PostDelete(@PathVariable Long id) {

        System.out.println(" ============================================== " );
        System.out.println("id = " + id);
        System.out.println(" ============================================== " );
        postService.deletePost(id);
    }

}
