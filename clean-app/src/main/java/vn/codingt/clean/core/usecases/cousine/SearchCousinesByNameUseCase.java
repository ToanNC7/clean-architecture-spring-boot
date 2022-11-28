package vn.codingt.clean.core.usecases.cousine;

import java.util.List;

import vn.codingt.clean.core.domain.Cousine;
import lombok.Value;
import vn.codingt.clean.core.usecases.UseCase;

public class SearchCousinesByNameUseCase
        extends UseCase<SearchCousinesByNameUseCase.InputValue, SearchCousinesByNameUseCase.OutputValue> {

    private final CousineRepository repository;

    public SearchCousinesByNameUseCase(CousineRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return new OutputValue(repository.searchByName(input.getSearchText()));
    }

    @Value
    public static class InputValue implements UseCase.InputValue {
        String searchText;
    }

    @Value
    public static class OutputValue implements UseCase.OutputValue {
        List<Cousine> cousines;
    }

}
