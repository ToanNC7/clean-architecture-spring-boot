package vn.codingt.clean.core.usecases.meta;

import lombok.Value;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;

public class SearchByKeyUseCase extends UseCase<SearchByKeyUseCase.InputValue, SearchByKeyUseCase.OutputValue> {

    private final MetaRepository metaRepository;


    public SearchByKeyUseCase(MetaRepository metaRepository){
        this.metaRepository = metaRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return new OutputValue(metaRepository.searchByKey(input.getKey()));
    }

    @Value
    public static final class InputValue implements UseCase.InputValue{
        final String key;
    }

    @Value
    public static final class OutputValue implements UseCase.OutputValue{
        final List<Meta> metas;
    }
}
