package vn.codingt.clean.core.usecases.cousine;

import java.util.List;

import lombok.Value;
import vn.codingt.clean.core.domain.Cousine;
import vn.codingt.clean.core.usecases.UseCase;

public class GetAllCousinesUseCase
        extends UseCase<GetAllCousinesUseCase.InputValues, GetAllCousinesUseCase.OutputValue> {

    private final CousineRepository repository;

    public GetAllCousinesUseCase(CousineRepository repository) {
        this.repository = repository;
    }

    @Override
    public OutputValue execute(InputValues input) {
        return new OutputValue(repository.getAll());
    }

    @Value
    public static class InputValues implements UseCase.InputValue {

    }

    @Value
    public static class OutputValue implements UseCase.OutputValue {
        List<Cousine> cousines;
    }

}
