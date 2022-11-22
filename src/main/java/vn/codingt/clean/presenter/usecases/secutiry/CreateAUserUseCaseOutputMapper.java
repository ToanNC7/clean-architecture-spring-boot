package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class CreateAUserUseCaseOutputMapper {

    public static ResponseEntity<ApiResponse> map(User customer, HttpServletRequest httpServletRequest) {

        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path("/customer/{id}")
                .buildAndExpand(customer.getId().getNumber())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "registered successfully"));
    }

}
