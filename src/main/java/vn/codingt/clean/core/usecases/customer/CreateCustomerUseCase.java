package vn.codingt.clean.core.usecases.customer;

import lombok.Value;
import vn.codingt.clean.core.domain.Customer;
import vn.codingt.clean.core.domain.exceptions.EmailAlreadyUsedException;
import vn.codingt.clean.core.usecases.UseCase;

public class CreateCustomerUseCase extends UseCase<CreateCustomerUseCase.InputValues, CreateCustomerUseCase.OutputValues> {

    private final CustomerRepository customerRepository;

    public CreateCustomerUseCase(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (customerRepository.existsByEmail(input.getEmail())) {
            throw new EmailAlreadyUsedException("Email address already in use");
        }

        Customer customer = Customer.newInstance(input.getName(), input.password, input.address, input.email);
        return new OutputValues(customerRepository.persist(customer));
    }

    @Value
    public static class InputValues implements UseCase.InputValue {
        String name;
        String email;
        String address;
        String password;
    }

    @Value
    public static class OutputValues implements UseCase.OutputValue {
        Customer customer;
    }

}
