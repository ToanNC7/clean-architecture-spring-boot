package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.http.ResponseEntity;

import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;

public class AuthenticateCustomerUseCaseOutputMapper {
    public static ResponseEntity<AuthenticationResponse> map(AuthenticateCustomerUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new AuthenticationResponse(outputValues.getJwtToken()));
    }
}
