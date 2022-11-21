package vn.codingt.clean.core.usecases;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

public interface UseCaseExecute {
    <RX, I extends UseCase.InputValue, O extends UseCase.OutputValue> CompletableFuture<RX> execute(
            UseCase<I, O> useCase,
            I input,
            Function<O, RX> outputMapper);
}
