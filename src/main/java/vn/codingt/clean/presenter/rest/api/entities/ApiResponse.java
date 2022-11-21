package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;

@Value
public class ApiResponse {
    Boolean success;
    String message;
}
