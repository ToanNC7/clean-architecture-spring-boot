package vn.codingt.clean.core.usecases.meta;

import lombok.Value;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;

public class GetAllMetaUseCase extends UseCase<GetAllMetaUseCase.InputValue, GetAllMetaUseCase.OutputValue> {

    private final MetaRepository metaRepository;

    public GetAllMetaUseCase(MetaRepository metaRepository){
        this.metaRepository = metaRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return new OutputValue(metaRepository.getALl());
    }

    @Value
    public static final class InputValue implements UseCase.InputValue{

    }
    @Value
    public static final class OutputValue implements UseCase.OutputValue{
        final List<Meta> metas;
    }
}
