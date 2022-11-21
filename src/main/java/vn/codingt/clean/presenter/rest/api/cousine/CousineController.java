package vn.codingt.clean.presenter.rest.api.cousine;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import org.springframework.stereotype.Component;

import vn.codingt.clean.core.usecases.UseCaseExecute;
import vn.codingt.clean.core.usecases.cousine.GetAllCousinesUseCase;
import vn.codingt.clean.core.usecases.cousine.SearchCousineByNameUseCase;
import vn.codingt.clean.presenter.rest.api.entities.CousineResponse;

@Component
public class CousineController implements CousineResource {

    private final UseCaseExecute useCaseExecute;
    private final GetAllCousinesUseCase getAllCousinesUseCase;
    private final SearchCousineByNameUseCase searchCousineByNameUseCase;

    public CousineController(UseCaseExecute useCaseExecute,
            GetAllCousinesUseCase getAllCousinesUseCase,
            SearchCousineByNameUseCase searchCousineByNameUseCase) {
        this.useCaseExecute = useCaseExecute;
        this.getAllCousinesUseCase = getAllCousinesUseCase;
        this.searchCousineByNameUseCase = searchCousineByNameUseCase;
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousines() {

        return useCaseExecute.execute(
                getAllCousinesUseCase,
                new GetAllCousinesUseCase.InputValues(),
                (outputValues) -> CousineResponse.from(outputValues.getCousines()));
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousineByNameMatching(String name) {

        return useCaseExecute.execute(
                searchCousineByNameUseCase,
                new SearchCousineByNameUseCase.InputValue(name),
                (outputValue) -> CousineResponse.from(outputValue.getCousines()));
    }

}
