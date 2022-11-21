package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;
import vn.codingt.clean.core.usecases.customer.CreateCustomerUseCase;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;

@Service
public class CreateCustomerInputMapper {
    private final PasswordEncoder passwordEncoder;

    public CreateCustomerInputMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public CreateCustomerUseCase.InputValues map(SignUpRequest signUpRequest) {
        return new CreateCustomerUseCase.InputValues(
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getAddress(),
                passwordEncoder.encode(signUpRequest.getPassword()));
    }
}
