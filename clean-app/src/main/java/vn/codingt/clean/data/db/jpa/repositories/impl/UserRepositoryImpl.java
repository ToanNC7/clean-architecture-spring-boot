package vn.codingt.clean.data.db.jpa.repositories.impl;

import vn.codingt.clean.data.db.jpa.entities.UserData;
import vn.codingt.clean.data.db.jpa.repositories.JpaUserRepository;
import org.springframework.stereotype.Repository;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.usecases.user.UserRepository;

import java.util.Optional;
import java.util.Set;

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
    public boolean existsById(Long id) {
        return jpaUserRepository.existsById(id);
    }

    @Override
    public boolean existsByIds(Set<Long> ids) {
        return jpaUserRepository.existsByIdIn(ids);
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
