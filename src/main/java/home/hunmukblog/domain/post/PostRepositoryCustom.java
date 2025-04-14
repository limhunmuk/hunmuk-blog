package home.hunmukblog.domain.post;

import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.dto.PostView;
import home.hunmukblog.domain.post.entity.Post;
import home.hunmukblog.web.response.PostResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<PostResponse> searchPostList(PostSearch postSearch, Pageable pageable);

    Post searchPostDetail(Long id);
}
