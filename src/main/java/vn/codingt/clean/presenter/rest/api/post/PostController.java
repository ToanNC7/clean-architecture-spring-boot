package vn.codingt.clean.presenter.rest.api.post;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import vn.codingt.clean.core.usecases.UseCaseExecute;
import vn.codingt.clean.core.usecases.post.CreateBulkPostUseCase;
import vn.codingt.clean.core.usecases.post.CreatePostUseCase;
import vn.codingt.clean.core.usecases.post.GetAllPostUseCase;
import vn.codingt.clean.core.usecases.post.SearchByTitleUseCase;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import vn.codingt.clean.presenter.rest.api.entities.CreatePostRequest;
import vn.codingt.clean.presenter.rest.api.entities.PostResponse;
import vn.codingt.clean.presenter.rest.api.mapper.post.CreateBulkPostInputMapper;
import vn.codingt.clean.presenter.rest.api.mapper.post.CreateBulkPostOutputMapper;
import vn.codingt.clean.presenter.rest.api.mapper.post.CreatePostInputMapper;
import vn.codingt.clean.presenter.rest.api.mapper.post.CreatePostOutputMapper;
import vn.codingt.clean.presenter.usecases.secutiry.CurrentUser;
import vn.codingt.clean.presenter.usecases.secutiry.UserPrincipal;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class PostController implements PostResource {

    private final UseCaseExecute useCaseExecute;
    private final GetAllPostUseCase getAllPostUseCase;
    private final SearchByTitleUseCase searchByTitleUseCase;
    private final CreatePostUseCase createPostUseCase;
    private final CreateBulkPostUseCase createBulkPostUseCase;
    private final CreatePostInputMapper createPostInputMapper;
    private final CreateBulkPostInputMapper createBulkPostInputMapper;


    public PostController(UseCaseExecute execute,
                          GetAllPostUseCase getAllPostUseCase,
                          SearchByTitleUseCase searchByTitleUseCase,
                          CreatePostUseCase createPostUseCase,
                          CreateBulkPostUseCase createBulkPostUseCase,
                          CreatePostInputMapper createPostInputMapper,
                          CreateBulkPostInputMapper createBulkPostInputMapper) {
        this.useCaseExecute = execute;
        this.getAllPostUseCase = getAllPostUseCase;
        this.searchByTitleUseCase = searchByTitleUseCase;
        this.createBulkPostUseCase = createBulkPostUseCase;
        this.createPostUseCase =createPostUseCase;
        this.createPostInputMapper = createPostInputMapper;
        this.createBulkPostInputMapper =createBulkPostInputMapper;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> createPost(@CurrentUser UserPrincipal principal,
                                                                     HttpServletRequest httpServletRequest,
                                                                     @Valid @RequestBody CreatePostRequest request) {
        return useCaseExecute.execute(
                createPostUseCase,
                createPostInputMapper.map(request, principal),
                (outputValue) -> CreatePostOutputMapper.map(outputValue.getPost(), httpServletRequest)
        );
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> createBulkPost(
            @CurrentUser UserPrincipal principal,
            HttpServletRequest httpServletRequest,
            @Valid @RequestBody List<CreatePostRequest> requests) {

        return useCaseExecute.execute(
                createBulkPostUseCase,
                createBulkPostInputMapper.map(requests, principal),
                (outputValue) -> CreateBulkPostOutputMapper.map("Create Bulk Post Successfully")
        );
    }

    @Override
    public CompletableFuture<List<PostResponse>> getAllPosts() {
        return useCaseExecute.execute(
                getAllPostUseCase,
                new GetAllPostUseCase.InputValue(),
                (outputValue) -> PostResponse.from(outputValue.getPosts())
        );
    }

    @Override
    public CompletableFuture<List<PostResponse>> getAllPostsByTypeMatching(@PathVariable String type) {
        return useCaseExecute.execute(
                searchByTitleUseCase,
                new SearchByTitleUseCase.InputValue(type),
                outputValue -> PostResponse.from(outputValue.getPosts())
        );
    }
}
