package home.hunmukblog.domain.post;

import home.hunmukblog.domain.member.dto.MemberSearch;
import home.hunmukblog.domain.member.entity.Member;
import home.hunmukblog.domain.post.dto.PostSearch;
import home.hunmukblog.domain.post.entity.Post;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> searchPostList(PostSearch postSearch);
}
