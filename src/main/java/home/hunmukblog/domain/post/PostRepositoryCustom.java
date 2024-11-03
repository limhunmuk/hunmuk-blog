package home.hunmukblog.domain.post;

import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> searchPostList(PostSearch postSearch);

    Post searchPostDetail(Long id);
}
