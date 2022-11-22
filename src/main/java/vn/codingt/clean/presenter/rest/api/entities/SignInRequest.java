package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Value
public class SignInRequest {

    @NotBlank
    @Email
    String email;

    @NotBlank
    @Size(min = 6, max = 50)
    String password;
}
