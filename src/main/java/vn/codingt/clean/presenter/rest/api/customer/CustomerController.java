package vn.codingt.clean.presenter.rest.api.customer;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.codingt.clean.core.usecases.UseCaseExecute;
import vn.codingt.clean.core.usecases.customer.CreateCustomerUseCase;

import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;
import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;
import vn.codingt.clean.presenter.usecases.secutiry.*;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@Component
public class CustomerController implements CustomerResource {

    private final UseCaseExecute useCaseExecute;
    private final CreateCustomerUseCase createCustomerUseCase;
    private final CreateCustomerInputMapper createCustomerInputMapper;
    private final AuthenticateCustomerUseCase authenticateCustomerUseCase;

    public CustomerController(UseCaseExecute useCaseExecute, CreateCustomerUseCase createCustomerUseCase,
            CreateCustomerInputMapper createCustomerInputMapper,
            AuthenticateCustomerUseCase authenticateCustomerUseCase) {
        this.useCaseExecute = useCaseExecute;
        this.createCustomerUseCase = createCustomerUseCase;
        this.createCustomerInputMapper = createCustomerInputMapper;
        this.authenticateCustomerUseCase = authenticateCustomerUseCase;
    }

    @Override
    public CompletableFuture<ResponseEntity<ApiResponse>> signUp(SignUpRequest request,
            HttpServletRequest httpServletRequest) {
        return useCaseExecute.execute(
                createCustomerUseCase,
                createCustomerInputMapper.map(request),
                (outputValues -> CreateCustomerUseCaseOutputMapper.map(outputValues.getCustomer(),
                        httpServletRequest)));
    }

    @Override
    public CompletableFuture<ResponseEntity<AuthenticationResponse>> signIn(SignInRequest request) {
        return useCaseExecute.execute(
                authenticateCustomerUseCase,
                AuthenticateCustomerUseCaseInputMapper.map(request),
                AuthenticateCustomerUseCaseOutputMapper::map);
    }

    @Override
    public ResponseEntity<ApiResponse> get() {

        return null;
    }
}
