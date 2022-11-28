package vn.codingt.clean.presenter.rest.api.mapper.post;

import vn.codingt.clean.core.usecases.post.CreatePostUseCase;
import vn.codingt.clean.presenter.rest.api.entities.CreatePostRequest;
import vn.codingt.clean.presenter.usecases.secutiry.UserPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.usecases.user.UserRepository;

import javax.validation.constraints.NotNull;

@Service
public class CreatePostInputMapper {

    private final UserRepository userRepository;

    public CreatePostInputMapper(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public CreatePostUseCase.InputValue map(@NotNull CreatePostRequest request, @NotNull UserDetails userDetails) {
        return new CreatePostUseCase.InputValue(
                map(userDetails),
                request.getTitle(),
                request.getMateTitle(),
                request.getSlug(),
                request.getSummary(),
                request.getPublished(),
                request.getContent()
        );
    }

    private User map( UserDetails userDetails){
        UserPrincipal user = (UserPrincipal) userDetails;
        return new User(
                new Identity(user.getId()),
                user.getName(),
                user.getPassword(),
                user.getAddress(),
                user.getEmail()
        );
    }
}
