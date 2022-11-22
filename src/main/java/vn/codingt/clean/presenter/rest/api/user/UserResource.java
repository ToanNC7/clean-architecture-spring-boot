package vn.codingt.clean.presenter.rest.api.user;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;
import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@Api(value = ApiPath.API_USER)
@RequestMapping(value = ApiPath.API_USER)
public interface UserResource {

    @PostMapping
    CompletableFuture<ResponseEntity<ApiResponse>> signUp(@Valid @RequestBody SignUpRequest request, HttpServletRequest httpServletRequest);

    @PostMapping("/auth")
    CompletableFuture<ResponseEntity<AuthenticationResponse>> signIn(@Valid @RequestBody SignInRequest request);
}
