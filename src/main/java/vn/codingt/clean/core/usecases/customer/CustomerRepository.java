package vn.codingt.clean.core.usecases.customer;

import java.util.Optional;

import vn.codingt.clean.core.domain.Customer;
import vn.codingt.clean.data.db.jpa.entities.CustomerData;

public interface CustomerRepository {

    Customer persist(Customer customer);

    boolean existsByEmail(String email);

    Optional<CustomerData> findByEmail(String email);

    Optional<CustomerData> findById(Long id);

}
