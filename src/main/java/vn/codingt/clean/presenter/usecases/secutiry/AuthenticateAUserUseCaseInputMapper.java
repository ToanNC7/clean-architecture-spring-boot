package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;

public class AuthenticateAUserUseCaseInputMapper {
    public static AuthenticateAUserUseCase.InputValues map(SignInRequest inRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                inRequest.getEmail(), inRequest.getPassword());
        return new AuthenticateAUserUseCase.InputValues(authenticationToken);
    }
}
