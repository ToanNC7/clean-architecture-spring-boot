package vn.codingt.clean.core.usecases.post;

import vn.codingt.clean.core.domain.Post;
import lombok.Value;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;

public class GetAllPostUseCase extends UseCase<GetAllPostUseCase.InputValue, GetAllPostUseCase.OutputValue> {
    private final PostRepository postRepository;

    public GetAllPostUseCase(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return new OutputValue(postRepository.getAll());
    }

    @Value
    public static class InputValue implements UseCase.InputValue{
    }

    @Value
    public static class OutputValue implements  UseCase.OutputValue{
        List<Post> posts;
    }
}
