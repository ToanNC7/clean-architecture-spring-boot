package vn.codingt.clean.presenter.rest.api.mapper.post;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.usecases.post.CreateBulkPostUseCase;
import vn.codingt.clean.core.usecases.user.UserRepository;
import vn.codingt.clean.presenter.rest.api.entities.CreatePostRequest;
import vn.codingt.clean.presenter.usecases.secutiry.UserPrincipal;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreateBulkPostInputMapper {

    private final UserRepository userRepository;

    public CreateBulkPostInputMapper(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    public CreateBulkPostUseCase.InputValue map(List<CreatePostRequest> request, UserDetails userDetails) {
        return new CreateBulkPostUseCase.InputValue(
                request.stream().map(
                        f-> new CreateBulkPostUseCase.InputItem(
                                map(userDetails),
                                f.getTitle(),
                                f.getMateTitle(),
                                f.getSlug(),
                                f.getSummary(),
                                f.getPublished(),
                                f.getContent()
                        )
                ).collect(Collectors.toList()));
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
