package vn.codingt.clean.presenter.usecases.secutiry;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import vn.codingt.clean.core.usecases.customer.CustomerRepository;
import vn.codingt.clean.data.db.jpa.entities.CustomerData;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public CustomUserDetailsService(CustomerRepository repository) {
        this.customerRepository = repository;
    }

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        CustomerData customerData = customerRepository
                .findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException(String.format("User %s not fount", email)));
        return UserPrincipal.from(customerData);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        CustomerData customerData = customerRepository
                .findById(id)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User %s not found", id)));
        return UserPrincipal.from(customerData);
    }
}
