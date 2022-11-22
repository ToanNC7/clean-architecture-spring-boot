package vn.codingt.clean.core.usecases.user;

import java.util.Optional;

import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.data.db.jpa.entities.UserData;

public interface UserRepository {

    User persist(User customer);

    boolean existsByEmail(String email);

    Optional<UserData> findByEmail(String email);

    Optional<UserData> findById(Long id);

}
