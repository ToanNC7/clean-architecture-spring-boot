package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import lombok.Value;
import vn.codingt.clean.core.usecases.UseCase;

@Service
public class AuthenticateCustomerUseCase
        extends UseCase<AuthenticateCustomerUseCase.InputValues, AuthenticateCustomerUseCase.OutputValues> {

    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    public AuthenticateCustomerUseCase(AuthenticationManager authenticationManager, JwtProvider jwtProvider) {
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;
    }

    @Override
    public OutputValues execute(InputValues input) {
        Authentication authentication = authenticationManager.authenticate(input.getAuthenticationToken());
        return new OutputValues(jwtProvider.generateToken(authentication));
    }

    @Value
    public static class InputValues implements UseCase.InputValue {
        UsernamePasswordAuthenticationToken authenticationToken;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValue {
        String jwtToken;
    }

}
