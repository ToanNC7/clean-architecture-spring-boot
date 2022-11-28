package vn.codingt.clean.core.usecases.meta;

import vn.codingt.clean.core.domain.Meta;

import java.util.List;
import java.util.Optional;

public interface MetaRepository {

    List<Meta> getALl();

    List<Meta> searchByKey(String key);

    Optional<Meta> getById(Long id);
}
