package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.domain.exceptions.NotFoundException;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;
import java.util.stream.Collectors;

public class GetMetaByPostIdUseCase extends UseCase<GetMetaByPostIdUseCase.InputValue, GetMetaByPostIdUseCase.OutputValue> {

    private final PostRepository postRepository;

    public GetMetaByPostIdUseCase(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        List<Meta> metas = postRepository.getMetaById(input.postId);
        if (metas == null || metas.isEmpty()) {
            throw new NotFoundException("Post id {} not found", input.postId.getNumber());
        }
        return new OutputValue(metas);
    }

    @Value
    public static final class InputValue implements UseCase.InputValue {
        final Identity postId;

    }

    @Value
    public static final class OutputValue implements UseCase.OutputValue {
        final List<Meta> metas;
    }

}
