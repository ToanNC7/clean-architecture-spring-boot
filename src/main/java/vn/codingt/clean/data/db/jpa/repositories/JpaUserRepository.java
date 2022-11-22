package vn.codingt.clean.data.db.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codingt.clean.data.db.jpa.entities.UserData;

import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {

    boolean existsByEmail(String email);

    Optional<UserData> findByEmail(String email);

}
