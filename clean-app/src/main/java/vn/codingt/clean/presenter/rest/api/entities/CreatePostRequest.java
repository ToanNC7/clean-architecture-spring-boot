package vn.codingt.clean.presenter.rest.api.entities;

import lombok.Value;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Value
public class CreatePostRequest {
    @NotBlank
    @NotNull
    String title;

    @NotBlank
    @NotNull
    String mateTitle;

    @NotBlank
    @NotNull
    String slug;

    @NotBlank
    @NotNull
    String summary;

    @NotBlank
    @NotNull
    Boolean published;

    @NotBlank
    @NotNull
    String content;
}
