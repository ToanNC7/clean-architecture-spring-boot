package vn.codingt.clean.core.usecases.post;

import vn.codingt.clean.core.domain.Post;
import lombok.Value;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.domain.exceptions.NotFoundException;
import vn.codingt.clean.core.usecases.UseCase;
import vn.codingt.clean.core.usecases.user.UserRepository;

public class CreatePostUseCase extends UseCase<CreatePostUseCase.InputValue, CreatePostUseCase.OutputValue> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public CreatePostUseCase(PostRepository postRepository,
                             UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {

        validatePost(input);
        Post post = Post.newInstance(
                input.user,
                input.title,
                input.mateTitle,
                input.slug,
                input.summary,
                input.published,
                input.content
        );

        return new OutputValue(postRepository.persist(post));
    }

    private void validatePost(InputValue input) {

        if (input.getUser() != null && input.getUser().getId() != null && input.getUser().getId() != null) {

            if (!userRepository.existsById(input.getUser().getId().getNumber())) {
                throw new NotFoundException("User not found");
            }
        } else {
            throw new NotFoundException("User not found");
        }
    }

    @Value
    public static class InputValue implements UseCase.InputValue {
        User user;

        String title;

        String mateTitle;

        String slug;

        String summary;

        Boolean published;

        String content;
    }

    @Value
    public static class OutputValue implements UseCase.OutputValue {
        Post post;
    }
}
