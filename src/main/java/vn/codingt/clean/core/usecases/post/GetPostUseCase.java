package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.domain.exceptions.NotFoundException;
import vn.codingt.clean.core.usecases.UseCase;

public class GetPostUseCase extends UseCase<GetPostUseCase.InputValue, GetPostUseCase.OutputValue> {

    private final PostRepository postRepository;

    public GetPostUseCase(PostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return postRepository
                .getById(input.getId())
                .map(GetPostUseCase.OutputValue::new)
                .orElseThrow(()-> new NotFoundException("Post {} not found", input.id.getNumber()));
    }

    @Value
    public static final class InputValue implements UseCase.InputValue{
        final Identity id;
    }

    @Value
    public static final class OutputValue implements UseCase.OutputValue{
        final Post post;
    }
}
