package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.usecases.UseCase;
import vn.codingt.clean.data.db.jpa.entities.PostData;
import vn.codingt.clean.data.db.jpa.repositories.JpaPostRepository;
import vn.codingt.clean.rsql.JpaRSqlProcessor;
import vn.codingt.clean.rsql.dto.DataPage;

public class GetAllPostUseCase extends UseCase<GetAllPostUseCase.InputValue, GetAllPostUseCase.OutputValue> {
    private final JpaPostRepository postRepository;

    public GetAllPostUseCase(JpaPostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public OutputValue execute(InputValue input) {
        DataPage<PostData> postDataDataPage = JpaRSqlProcessor.execQuery(postRepository, input.fields, input.filter, input.size, input.page, input.sort);
        return new OutputValue(postDataDataPage);
    }

    @Value
    public static class InputValue implements UseCase.InputValue{
        String filter;
        String fields;
        int size;
        int page;
        String sort;
    }

    @Value
    public static class OutputValue implements  UseCase.OutputValue{
        DataPage<PostData> post;
    }
}
