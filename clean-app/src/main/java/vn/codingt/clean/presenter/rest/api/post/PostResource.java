package vn.codingt.clean.presenter.rest.api.post;

import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import vn.codingt.clean.presenter.usecases.secutiry.CurrentUser;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.entities.CreatePostRequest;
import vn.codingt.clean.presenter.rest.api.entities.PostResponse;
import vn.codingt.clean.presenter.usecases.secutiry.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(value = ApiPath.API_POST)
@Api(value = ApiPath.API_POST)
public interface PostResource {

    @PostMapping
    CompletableFuture<ResponseEntity<ApiResponse>> createPost(@CurrentUser UserPrincipal principal,
                                                              HttpServletRequest httpServletRequest,
                                                              @Valid @RequestBody CreatePostRequest request);

    @PostMapping("/bulk")
    CompletableFuture<ResponseEntity<ApiResponse>> createBulkPost(@CurrentUser UserPrincipal principal,
                                                              HttpServletRequest httpServletRequest,
                                                              @Valid @RequestBody List<CreatePostRequest> request);

    @GetMapping
    CompletableFuture<List<PostResponse>> getAllPosts();

    @GetMapping(value = "/search/{search}}")
    CompletableFuture<List<PostResponse>> getAllPostsByTypeMatching(String type);


}
