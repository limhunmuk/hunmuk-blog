package home.hunmukblog.web.api.service;

import home.hunmukblog.domain.post.PostRepository;
import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public List<Post> searchPostList(PostSearch postSearch) {
        return postRepository.searchPostList(postSearch);
    }

    public Post searchPostDetail(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    public Post savePost(PostCreate request) {

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .regId("auto")
                .regDt(LocalDateTime.now())
                .build();

        return postRepository.save(post);
    }
}
