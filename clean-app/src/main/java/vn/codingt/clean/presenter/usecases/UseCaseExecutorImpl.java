package vn.codingt.clean.presenter.usecases;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import org.springframework.stereotype.Service;
import vn.codingt.clean.core.usecases.UseCase;
import vn.codingt.clean.core.usecases.UseCase.InputValue;
import vn.codingt.clean.core.usecases.UseCase.OutputValue;
import vn.codingt.clean.core.usecases.UseCaseExecute;

@Service
public class UseCaseExecutorImpl implements UseCaseExecute {

    @Override
    public <RX, I extends InputValue, O extends OutputValue> CompletableFuture<RX> execute(UseCase<I, O> useCase,
            I input, Function<O, RX> outputMapper) {
        return CompletableFuture.supplyAsync(() -> input)
                .thenApplyAsync(useCase::execute)
                .thenApplyAsync(outputMapper);
    }

}
