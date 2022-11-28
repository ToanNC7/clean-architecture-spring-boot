package vn.codingt.clean.core.usecases.user;

import java.util.Optional;
import java.util.Set;

import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.data.db.jpa.entities.UserData;

public interface UserRepository {

    User persist(User user);

    boolean existsByEmail(String email);

    boolean existsById(Long id);

    boolean existsByIds(Set<Long> ids);

    Optional<UserData> findByEmail(String email);

    Optional<UserData> findById(Long id);

}
