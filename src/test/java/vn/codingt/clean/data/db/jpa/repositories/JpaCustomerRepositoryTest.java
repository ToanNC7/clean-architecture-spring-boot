package vn.codingt.clean.data.db.jpa.repositories;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import vn.codingt.clean.data.db.jpa.entities.CustomerData;

import static vn.codingt.clean.TestUtils.newInstanceWithProperties;
import static org.assertj.core.api.Assertions.assertThat;
import vn.codingt.clean.core.entities.TestCoreEntityGenerator;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaCustomerRepositoryTest {

    @Autowired
    private JpaCustomerRepository repository;

    @Autowired
    private TestEntityManager entityManager;

    @AutoConfigurationPackage
    @Configuration
    @EntityScan("vn.codingt.clean.data.db.jpa.entities")
    static class Config {

    }

    @Test
    public void existsByEmailReturnTrue() throws Exception {
        // given
        CustomerData customerData = newInstanceWithProperties(CustomerData.class,
                TestCoreEntityGenerator.randomCustomer());

        // and
        entityManager.persistAndFlush(customerData);

        // when
        boolean actual = repository.existsByEmail(customerData.getEmail());

        // then
        assertThat(actual).isTrue();
    }

    @Test
    public void existsByEmailReturnFalse() throws Exception {
        // given
        CustomerData customerData = newInstanceWithProperties(CustomerData.class,
                TestCoreEntityGenerator.randomCustomer());
        // and
        entityManager.persistAndFlush(customerData);

        // when
        boolean actual = repository.existsByEmail("not fould" + customerData.getEmail());

        // Then
        assertThat(actual).isFalse();
    }

}
