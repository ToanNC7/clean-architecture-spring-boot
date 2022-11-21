package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;
import vn.codingt.clean.core.usecases.customer.CreateCustomerUseCase;

@Value
public class SignUpRequest {
    String name;
    String email;
    String address;
    String password;

    public static CreateCustomerUseCase.InputValues from(SignUpRequest signUpRequest) {
        return new CreateCustomerUseCase.InputValues(signUpRequest.getName(), signUpRequest.getEmail(),
                signUpRequest.getAddress(), signUpRequest.getPassword());
    }
}
