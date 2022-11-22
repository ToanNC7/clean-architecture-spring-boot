package vn.codingt.clean.core.usecases.user;

import lombok.Value;
import vn.codingt.clean.core.domain.User;
import vn.codingt.clean.core.domain.exceptions.EmailAlreadyUsedException;
import vn.codingt.clean.core.usecases.UseCase;

public class CreateAUserUseCase extends UseCase<CreateAUserUseCase.InputValues, CreateAUserUseCase.OutputValues> {

    private final UserRepository customerRepository;

    public CreateAUserUseCase(UserRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public OutputValues execute(InputValues input) {
        if (customerRepository.existsByEmail(input.getEmail())) {
            throw new EmailAlreadyUsedException("Email address already in use");
        }

        User customer = User.newInstance(input.getName(), input.password, input.address, input.email);
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
        User customer;
    }

}
