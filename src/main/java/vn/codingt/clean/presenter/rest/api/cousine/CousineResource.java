package vn.codingt.clean.presenter.rest.api.cousine;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import vn.codingt.clean.core.util.constant.ApiPath;
import vn.codingt.clean.presenter.rest.api.entities.CousineResponse;

@CrossOrigin
@RestController
@RequestMapping(ApiPath.API_COUSINE)
@Api(ApiPath.API_COUSINE)
public interface CousineResource {

    @GetMapping
    CompletableFuture<List<CousineResponse>> getAllCousines();

    @GetMapping(value = "/search/{search}}")
    CompletableFuture<List<CousineResponse>> getAllCousineByNameMatching(String name);

}
