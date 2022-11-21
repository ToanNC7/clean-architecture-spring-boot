package vn.codingt.clean.data.db.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codingt.clean.data.db.jpa.entities.CustomerData;

import java.util.Optional;

public interface JpaCustomerRepository extends JpaRepository<CustomerData, Long> {

    boolean existsByEmail(String email);

    Optional<CustomerData> findByEmail(String email);

}
