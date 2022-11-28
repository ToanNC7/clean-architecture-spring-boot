package vn.codingt.clean.presenter.rest.api.mapper.user;

import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;
import vn.codingt.clean.presenter.usecases.secutiry.AuthenticateAUserUseCase;
import org.springframework.http.ResponseEntity;

public class AuthenticateAUserUseCaseOutputMapper {
    public static ResponseEntity<AuthenticationResponse> map(AuthenticateAUserUseCase.OutputValues outputValues) {
        return ResponseEntity.ok(new AuthenticationResponse(outputValues.getJwtToken()));
    }
}
