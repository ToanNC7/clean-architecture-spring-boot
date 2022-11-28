package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.domain.exceptions.NotFoundException;
import vn.codingt.clean.core.usecases.UseCase;
import vn.codingt.clean.core.usecases.user.UserRepository;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CreateBulkPostUseCase extends UseCase<CreateBulkPostUseCase.InputValue, CreateBulkPostUseCase.OutputValue> {

    private final PostRepository postRepository;
    private final UserRepository userRepository;


    public CreateBulkPostUseCase(PostRepository postRepository,
                                 UserRepository userRepository){
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        validatePost(input);
        List<Post> post = input.posts.
                stream().map(f->  Post.newInstance(
                f.user,
                f.title,
                f.mateTitle,
                f.slug,
                f.summary,
                f.published,
                f.content
        )).collect(Collectors.toList());

        return new OutputValue(postRepository.persistBulk(post));
    }

    private void validatePost(InputValue input) {
        Set<Long> idUser = input.posts.stream()
                .filter(f->f.getUser() != null && f.getUser().getId() != null && f.getUser().getId().getNumber() != null)
                .map(m->m.getUser().getId().getNumber())
                .collect(Collectors.toSet());

        if(!userRepository.existsByIds(idUser)){
            throw new NotFoundException("User not found");
        }
    }

    @Value
    public static class InputValue implements UseCase.InputValue{
        List<InputItem> posts;
    }

    @Value
    public static class OutputValue implements UseCase.OutputValue{
        List<Post> posts;
    }

    @Value
    public static class InputItem{
        User user;

        String title;

        String mateTitle;

        String slug;

        String summary;

        Boolean published;

        String content;
    }
}
