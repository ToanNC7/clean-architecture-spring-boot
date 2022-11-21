package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;

@Value
public class SignInRequest {
    String email;
    String password;
}
