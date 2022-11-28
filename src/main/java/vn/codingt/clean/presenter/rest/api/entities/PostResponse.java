package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.domain.User;

import java.util.List;
import java.util.stream.Collectors;

@Value
public class PostResponse {
    Long id;

    User user;

    String title;

    String mateTitle;

    String slug;

    String summary;

    Boolean published;

    String content;
    private static PostResponse from(Post post) {
        return new PostResponse(
                post.getId().getNumber(),
                post.getUser(),
                post.getTitle(),
                post.getMateTitle(),
                post.getSlug(),
                post.getSummary(),
                post.getPublished(),
                post.getContent()
        );
    }

    public static List<PostResponse> from(List<Post> cousines) {
        return cousines
                .parallelStream()
                .map(PostResponse::from)
                .collect(Collectors.toList());
    }
}
