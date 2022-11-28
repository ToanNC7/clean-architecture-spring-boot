package vn.codingt.clean.presenter.rest.api.cousine;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import vn.codingt.clean.core.usecases.cousine.GetAllCousinesUseCase;
import vn.codingt.clean.core.usecases.cousine.SearchCousinesByNameUseCase;
import vn.codingt.clean.presenter.rest.api.entities.CousineResponse;
import org.springframework.stereotype.Component;

import vn.codingt.clean.core.usecases.UseCaseExecute;

@Component
public class CousineController implements CousineResource {

    private final UseCaseExecute useCaseExecute;
    private final GetAllCousinesUseCase getAllCousinesUseCase;
    private final SearchCousinesByNameUseCase searchCousineByNameUseCase;

    public CousineController(UseCaseExecute useCaseExecute,
            GetAllCousinesUseCase getAllCousinesUseCase,
            SearchCousinesByNameUseCase searchCousineByNameUseCase) {
        this.useCaseExecute = useCaseExecute;
        this.getAllCousinesUseCase = getAllCousinesUseCase;
        this.searchCousineByNameUseCase = searchCousineByNameUseCase;
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousines() {

        return useCaseExecute.execute(
                getAllCousinesUseCase,
                new GetAllCousinesUseCase.InputValues(),
                (outputValues) -> CousineResponse.from(outputValues.getCousins()));
    }

    @Override
    public CompletableFuture<List<CousineResponse>> getAllCousineByNameMatching(String name) {

        return useCaseExecute.execute(
                searchCousineByNameUseCase,
                new SearchCousinesByNameUseCase.InputValue(name),
                (outputValue) -> CousineResponse.from(outputValue.getCousines()));
    }

}
