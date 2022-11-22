package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.domain.exceptions.NotFoundException;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;

public class GetAllPostUseCase extends UseCase<GetAllPostUseCase.InputValue, GetAllPostUseCase.OutputValue> {
    private final PostRepository postRepository;

    public GetAllPostUseCase(PostRepository postRepositor){
        this.postRepository = postRepositor;
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
        final List<Post> posts;
    }
}
