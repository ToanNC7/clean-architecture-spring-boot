package vn.codingt.clean.presenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import vn.codingt.clean.core.usecases.cousine.CousineRepository;
import vn.codingt.clean.core.usecases.cousine.GetAllCousinesUseCase;
import vn.codingt.clean.core.usecases.cousine.SearchCousineByNameUseCase;
import vn.codingt.clean.core.usecases.customer.CreateCustomerUseCase;
import vn.codingt.clean.core.usecases.customer.CustomerRepository;

@Configuration
public class Module {

    @Bean
    public GetAllCousinesUseCase getAllCousinesUseCase(CousineRepository repository){
        return  new GetAllCousinesUseCase(repository);
    }

    @Bean
    public SearchCousineByNameUseCase searchCousineByNameUseCase(CousineRepository repository){
        return new SearchCousineByNameUseCase(repository);
    }

    @Bean
    public CreateCustomerUseCase createCustomerUseCase(CustomerRepository repository) {
        return new CreateCustomerUseCase(repository);
    }
}
