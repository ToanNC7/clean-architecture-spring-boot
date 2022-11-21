package vn.codingt.clean.presenter.rest.api.customer;

import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import vn.codingt.clean.presenter.rest.api.entities.AuthenticationResponse;
import vn.codingt.clean.presenter.rest.api.entities.SignInRequest;
import vn.codingt.clean.presenter.rest.api.entities.SignUpRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.CompletableFuture;

@CrossOrigin
@RestController
@Api(value = ApiPath.API_CUSTOMER)
@RequestMapping(value = ApiPath.API_CUSTOMER)
public interface CustomerResource {

    @GetMapping(value = "/get")
    ResponseEntity<ApiResponse> get();

    @PostMapping
    CompletableFuture<ResponseEntity<ApiResponse>> signUp(@RequestBody SignUpRequest request,
            HttpServletRequest httpServletRequest);

    @PostMapping("/auth")
    CompletableFuture<ResponseEntity<AuthenticationResponse>> signIn(@RequestBody SignInRequest request);
}
