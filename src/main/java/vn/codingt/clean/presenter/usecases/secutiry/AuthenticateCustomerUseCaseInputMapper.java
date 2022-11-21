package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;

public class AuthenticateCustomerUseCaseInputMapper {
    public static AuthenticateCustomerUseCase.InputValues map(SignInRequest inRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                inRequest.getEmail(), inRequest.getPassword());
        return new AuthenticateCustomerUseCase.InputValues(authenticationToken);
    }
}
