package vn.codingt.clean.presenter.rest.api.post;

import org.springframework.stereotype.Component;
import vn.codingt.clean.core.usecases.UseCaseExecute;
import vn.codingt.clean.core.usecases.post.GetAllPostUseCase;
import vn.codingt.clean.core.usecases.post.SearchByTitleUseCase;
import vn.codingt.clean.presenter.rest.api.entities.PostResponse;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class PostController implements PostResource {

    private final UseCaseExecute useCaseExecute;
    private final GetAllPostUseCase getAllPostUseCase;
    private final SearchByTitleUseCase searchByTitleUseCase;


    public PostController(UseCaseExecute execute, GetAllPostUseCase getAllPostUseCase, SearchByTitleUseCase searchByTitleUseCase) {
        this.useCaseExecute = execute;
        this.getAllPostUseCase = getAllPostUseCase;
        this.searchByTitleUseCase = searchByTitleUseCase;
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
    public CompletableFuture<List<PostResponse>> getAllPostsByTypeMatching(String type) {
        return useCaseExecute.execute(
                searchByTitleUseCase,
                new SearchByTitleUseCase.InputValue(type),
                outputValue -> PostResponse.from(outputValue.getPosts())
        );
    }
}
