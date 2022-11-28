package vn.codingt.clean.core.usecases.meta;

import lombok.Value;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.domain.exceptions.NotFoundException;
import vn.codingt.clean.core.usecases.UseCase;

public class GetMetaUseCase extends UseCase<GetMetaUseCase.InputValue, GetMetaUseCase.OutputValue> {

    private  final MetaRepository metaRepository;

    public GetMetaUseCase(MetaRepository metaRepository){
        this.metaRepository = metaRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        return metaRepository
                .getById(input.id.getNumber())
                .map(OutputValue::new)
                .orElseThrow(() -> new NotFoundException("Meta {} not found", input.getId().getNumber()));
    }

    @Value
    public static final class InputValue implements UseCase.InputValue{
        final Identity id;
    }

    @Value
    public static final class OutputValue implements UseCase.OutputValue{
        final Meta meta;
    }
}
