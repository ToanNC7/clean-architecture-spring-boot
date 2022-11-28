package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;
import vn.codingt.clean.core.usecases.user.CreateAUserUseCase;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class SignUpRequest {
    @NotBlank
    String name;

    @Email
    @NotBlank
    String email;

    @NotBlank
    String address;

    @NotBlank
    @Size(min = 5, max = 50)
    String password;

    public static CreateAUserUseCase.InputValues from(SignUpRequest signUpRequest) {
        return new CreateAUserUseCase.InputValues(signUpRequest.getName(), signUpRequest.getEmail(),
                signUpRequest.getAddress(), signUpRequest.getPassword());
    }
}
