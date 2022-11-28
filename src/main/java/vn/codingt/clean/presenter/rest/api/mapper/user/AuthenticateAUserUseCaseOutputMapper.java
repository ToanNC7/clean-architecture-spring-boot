package vn.codingt.clean.presenter.rest.api.mapper.user;

import org.springframework.http.ResponseEntity;

import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;
import vn.codingt.clean.presenter.usecases.secutiry.AuthenticateAUserUseCase;

public class AuthenticateAUserUseCaseOutputMapper {
    public static ResponseEntity<AuthenticationResponse> map(AuthenticateAUserUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new AuthenticationResponse(outputValues.getJwtToken()));
    }
}
