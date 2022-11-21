package vn.codingt.clean.data.db.jpa.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.codingt.clean.core.domain.Customer;
import vn.codingt.clean.core.usecases.customer.CustomerRepository;
import vn.codingt.clean.data.db.jpa.entities.CustomerData;
import vn.codingt.clean.data.db.jpa.repositories.JpaCustomerRepository;

import java.util.Optional;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private final JpaCustomerRepository jpaCustomerRepository;

    public CustomerRepositoryImpl(JpaCustomerRepository jpaCustomerRepository) {
        this.jpaCustomerRepository = jpaCustomerRepository;
    }

    @Override
    public Customer persist(Customer customer) {

        final CustomerData customerData = CustomerData.from(customer);

        return jpaCustomerRepository.save(customerData).fromThis();
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaCustomerRepository.existsByEmail(email);
    }

    @Override
    public Optional<CustomerData> findByEmail(String email) {
        return jpaCustomerRepository.findByEmail(email);
    }

    @Override
    public Optional<CustomerData> findById(Long id) {
        return jpaCustomerRepository.findById(id);
    }
}
