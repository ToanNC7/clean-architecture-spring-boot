package vn.codingt.clean.presenter.rest.api.entities;

import java.util.List;
import java.util.stream.Collectors;

import lombok.Value;
import vn.codingt.clean.core.domain.Cousine;

@Value
public class CousineResponse {
    Long id;
    String name;

    private static CousineResponse from(Cousine cousine) {
        return new CousineResponse(cousine.getId().getNumber(), cousine.getName());
    }

    public static List<CousineResponse> from(List<Cousine> cousines) {
        return cousines
                .stream()
                .map(CousineResponse::from)
                .collect(Collectors.toList());
    }
}
