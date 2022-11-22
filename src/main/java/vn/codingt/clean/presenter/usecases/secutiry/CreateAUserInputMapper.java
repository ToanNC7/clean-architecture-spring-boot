package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import vn.codingt.clean.core.usecases.user.CreateAUserUseCase;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;

@Service
public class CreateAUserInputMapper {
    private final PasswordEncoder passwordEncoder;

    public CreateAUserInputMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CreateAUserUseCase.InputValues map(SignUpRequest signUpRequest) {
        return new CreateAUserUseCase.InputValues(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getAddress(),
                passwordEncoder.encode(signUpRequest.getPassword()));
    }
}
