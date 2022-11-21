package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;

@Value
public class AuthenticationResponse {
    boolean success = true;
    String token;
}
