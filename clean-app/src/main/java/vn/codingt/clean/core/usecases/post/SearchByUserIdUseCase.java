package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;

public class SearchByUserIdUseCase extends UseCase<SearchByUserIdUseCase.InputValue, SearchByUserIdUseCase.OutputValue> {

    private final PostRepository postRepository;

    public SearchByUserIdUseCase(PostRepository repository){
        this.postRepository = repository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return new OutputValue(postRepository.searchByUserId(input.userId));
    }

    @Value
    public static class InputValue implements UseCase.InputValue{
        Long userId;
    }

    @Value
    public static class OutputValue implements UseCase.OutputValue{
        List<Post> posts;
    }
}
