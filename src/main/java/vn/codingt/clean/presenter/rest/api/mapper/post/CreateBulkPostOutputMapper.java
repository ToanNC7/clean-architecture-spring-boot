package vn.codingt.clean.presenter.rest.api.mapper.post;

import org.springframework.http.ResponseEntity;
import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;

public class CreateBulkPostOutputMapper {
    public static ResponseEntity<ApiResponse> map(String message) {

        return ResponseEntity.ok().body(new ApiResponse(true, message));
    }
}
