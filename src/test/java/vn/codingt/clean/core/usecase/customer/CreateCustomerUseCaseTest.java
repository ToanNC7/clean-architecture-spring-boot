package vn.codingt.clean.core.usecase.customer;

import static org.mockito.Mockito.doReturn;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import vn.codingt.clean.core.domain.Customer;
import vn.codingt.clean.core.domain.exceptions.EmailAlreadyUsedException;
import vn.codingt.clean.core.entities.TestCoreEntityGenerator;
import vn.codingt.clean.core.usecases.customer.CreateCustomerUseCase;
import vn.codingt.clean.core.usecases.customer.CustomerRepository;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;

import static org.mockito.ArgumentMatchers.eq;

@RunWith(MockitoJUnitRunner.class)
public class CreateCustomerUseCaseTest {

    @InjectMocks
    private CreateCustomerUseCase useCase;

    @Mock
    private CustomerRepository repository;

    @Test
    public void executeThrowsExceptionWhenEmailIsAlreadyRegister() {

        // given
        CreateCustomerUseCase.InputValues input = new CreateCustomerUseCase.InputValues("name", "email@com.vn",
                "address", "admin@123");

        // add
        doReturn(true)
                .when(repository)
                .existsByEmail(input.getEmail());

        assertThatThrownBy(() -> useCase.execute(input))
                .isInstanceOf(EmailAlreadyUsedException.class)
                .hasMessageContaining("Email address already in use!");

    }

    @Test
    public void executeReturnsCreatedCustomer() {

        // given
        Customer customer = TestCoreEntityGenerator.randomCustomer();
        Customer toBeMatched = Customer.newInstance(customer.getName(), customer.getPassword(), customer.getAddress(),
                customer.getEmail());

        CreateCustomerUseCase.InputValues input = new CreateCustomerUseCase.InputValues(customer.getName(),
                customer.getEmail(), customer.getAddress(),
                customer.getPassword());

        // and
        doReturn(customer)
                .when(repository)
                .persist(eq(toBeMatched));

        // when
        Customer actual = useCase.execute(input).getCustomer();

        assertThat(actual).isEqualTo(customer);

    }
}
