package vn.codingt.clean.data.db.jpa.repositories;

import vn.codingt.clean.data.db.jpa.entities.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.Optional;

public interface JpaUserRepository extends JpaRepository<UserData, Long> {

    boolean existsByEmail(String email);

    Optional<UserData> findByEmail(String email);

    @Query("select (count(u) > 0) from user u where u.id in ?1")
    boolean existsByIdIn(Collection<Long> ids);



}
