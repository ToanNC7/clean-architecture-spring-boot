package vn.codingt.clean.presenter.rest.api.user;

import vn.codingt.clean.core.usecases.UseCaseExecute;
import vn.codingt.clean.core.usecases.user.CreateAUserUseCase;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;
import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;
import vn.codingt.clean.presenter.rest.api.mapper.user.AuthenticateAUserUseCaseInputMapper;
import vn.codingt.clean.presenter.rest.api.mapper.user.AuthenticateAUserUseCaseOutputMapper;
import vn.codingt.clean.presenter.rest.api.mapper.user.CreateAUserInputMapper;
import vn.codingt.clean.presenter.rest.api.mapper.user.CreateAUserUseCaseOutputMapper;
import vn.codingt.clean.presenter.usecases.secutiry.AuthenticateAUserUseCase;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@Component
public class UserController implements UserResource {

    private final UseCaseExecute useCaseExecute;
    private final CreateAUserUseCase createAUserUseCase;
    private final CreateAUserInputMapper createAUserInputMapper;
    private final AuthenticateAUserUseCase authenticateAUserUseCase;

    public UserController(UseCaseExecute useCaseExecute, CreateAUserUseCase createAUserUseCase,
                          CreateAUserInputMapper createAUserInputMapper,
                          AuthenticateAUserUseCase authenticateAUserUseCase) {
        this.useCaseExecute = useCaseExecute;
        this.createAUserUseCase = createAUserUseCase;
        this.createAUserInputMapper = createAUserInputMapper;
        this.authenticateAUserUseCase = authenticateAUserUseCase;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> signUp(@Valid @RequestBody SignUpRequest request,
                                                                 HttpServletRequest httpServletRequest) {
        return useCaseExecute.execute(
                createAUserUseCase,
                createAUserInputMapper.map(request),
                (outputValues -> CreateAUserUseCaseOutputMapper.map(outputValues.getUser(),
                        httpServletRequest)));
    }

    @Override
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> signIn(@Valid @RequestBody SignInRequest request) {
        return useCaseExecute.execute(
                authenticateAUserUseCase,
                AuthenticateAUserUseCaseInputMapper.map(request),
                AuthenticateAUserUseCaseOutputMapper::map);
    }
}
