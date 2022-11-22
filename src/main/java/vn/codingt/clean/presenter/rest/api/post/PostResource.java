package vn.codingt.clean.presenter.rest.api.post;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.entities.PostResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = ApiPath.API_POST)
@Api(value = ApiPath.API_POST)
public interface PostResource {

    @GetMapping
    CompletableFuture<List<PostResponse>> getAllPosts();

    @GetMapping(value = "/search/{search}}")
    CompletableFuture<List<PostResponse>> getAllPostsByTypeMatching(String type);


}
