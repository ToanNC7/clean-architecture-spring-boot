package vn.codingt.clean.presenter.rest.api.mapper.post;

import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.codingt.clean.core.util.constant.ApiPath;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class CreatePostOutputMapper {
    public static ResponseEntity<ApiResponse> map(Post post, HttpServletRequest httpServletRequest) {

        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path(ApiPath.API_POST +  "{id}")
                .buildAndExpand(post.getId().getNumber())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "registered successfully"));
    }
}
