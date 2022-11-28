package vn.codingt.clean.presenter.rest.api.mapper.post;

import vn.codingt.clean.presenter.rest.api.entities.ApiResponse;
import org.springframework.http.ResponseEntity;

public class CreateBulkPostOutputMapper {
    public static ResponseEntity<ApiResponse> map(String message) {

        return ResponseEntity.ok().body(new ApiResponse(true, message));
    }
}
