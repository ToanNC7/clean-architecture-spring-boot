package vn.codingt.clean.data.db.jpa.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.usecases.user.UserRepository;
import vn.codingt.clean.data.db.jpa.entities.UserData;
import vn.codingt.clean.data.db.jpa.repositories.JpaUserRepository;

import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private final JpaUserRepository jpaUserRepository;

    public UserRepositoryImpl(JpaUserRepository jpaUserRepository) {
        this.jpaUserRepository = jpaUserRepository;
    }

    @Override
    public User persist(User customer) {

        final UserData customerData = UserData.from(customer);

        return jpaUserRepository.save(customerData).fromThis();
    }

    @Override
    public boolean existsByEmail(String email) {
        return jpaUserRepository.existsByEmail(email);
    }

    @Override
    public Optional<UserData> findByEmail(String email) {
        return jpaUserRepository.findByEmail(email);
    }

    @Override
    public Optional<UserData> findById(Long id) {
        return jpaUserRepository.findById(id);
    }
}
