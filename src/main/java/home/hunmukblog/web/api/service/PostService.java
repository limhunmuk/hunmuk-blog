package home.hunmukblog.web.api.service;

import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.domain.post.PostRepository;
import home.hunmukblog.domain.post.dto.PostCreate;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostUpdate;
import home.hunmukblog.domain.post.dto.PostView;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.domain.post.entity.PostEditor;
import home.hunmukblog.web.response.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    /**
     * 게시글 목록 조회
     * @param postSearch
     * @return
     */
    public Page<PostResponse> searchPostList(PostSearch postSearch, Pageable pageable) {
        return postRepository.searchPostList(postSearch, pageable);
    }

    /**
     * 게시글 상세 조회
     * @param id
     * @return
     */
    public Post searchPostDetail(Long id) {
        return postRepository.findById(id).orElse(null);
    }

    /**
     * 게시글 등록
     * @param request
     * @return
     */
    @Transactional
    public Post savePost(PostCreate request, Member member) {

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(member)
                .build();

        //return postRepository.save(post);
        return post;
    }

    /**
     * 게시글 수정
     * @param id
     * @param request
     * @return
     */
    public Post updatePost(Long id, PostUpdate request) {

            Post post = postRepository.findById(id).orElse(null);

            if (post == null) {
                throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
            }

        PostEditor.PostEditorBuilder editor = post.toEditor();

        editor.title(request.getTitle());
        editor.content(request.getContent());

        post.edit(editor.build());
       // return postRepository.save(post);
        return post;
    }

    /**
     * 게시글 삭제
     * @param id
     */
    public void deletePost(Long id) {

        Post post = postRepository.findById(id).orElse(null);

        if (post == null) {
            throw new IllegalArgumentException("해당 게시글이 존재하지 않습니다.");
        }
        postRepository.deleteById(id);
    }
}
