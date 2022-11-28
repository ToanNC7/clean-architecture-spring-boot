package vn.codingt.clean.presenter.rest.api.mapper.user;

import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.util.constant.ApiPath;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;

public class CreateAUserUseCaseOutputMapper {

    public static ResponseEntity<ApiResponse> map(User user, HttpServletRequest httpServletRequest) {

        URI location = ServletUriComponentsBuilder
                .fromContextPath(httpServletRequest)
                .path(ApiPath.API_USER+"/{id}")
                .buildAndExpand(user.getId().getNumber())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "registered successfully"));
    }

}
