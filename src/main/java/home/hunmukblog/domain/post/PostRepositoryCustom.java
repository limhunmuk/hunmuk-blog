package home.hunmukblog.domain.post;

import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PostRepositoryCustom {

    Page<Post> searchPostList(PostSearch postSearch, Pageable pageable);

    Post searchPostDetail(Long id);
}
