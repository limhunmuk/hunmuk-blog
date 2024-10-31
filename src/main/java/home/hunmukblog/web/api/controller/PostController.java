package home.hunmukblog.web.api.controller;

import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.web.api.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @GetMapping("/post")
    public List<Post> posts(PostSearch search) {

        List<Post> posts = postService.searchPostList(search);
        return posts;
    }

    @GetMapping("/post/{id}")
    public Post postDetail(@PathVariable Long id) {

        return postService.searchPostDetail(id);
    }


    @PostMapping("/post")
    public Post PostCreate(@RequestBody PostCreate request) {

        System.out.println(" ============================================== " );
        System.out.println("request = " + request);
        System.out.println(" ============================================== " );
        return postService.savePost(request);
    }
}
