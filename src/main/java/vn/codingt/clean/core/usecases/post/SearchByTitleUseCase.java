package vn.codingt.clean.core.usecases.post;

import lombok.Value;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.usecases.UseCase;

import java.util.List;

public class SearchByTitleUseCase extends UseCase<SearchByTitleUseCase.InputValue, SearchByTitleUseCase.OutputValue> {


    private final PostRepository postRepository;

    public SearchByTitleUseCase(PostRepository postRepository){
        this.postRepository = postRepository;
    }


    @Override
    public OutputValue execute(InputValue input) {
        return new OutputValue(postRepository.searchByTitle(input.getTitle()));
    }

    @Value
    public static  class InputValue implements UseCase.InputValue{
        final String title;
    }
    @Value
    public  static class  OutputValue implements UseCase.OutputValue{
        final List<Post> posts;
    }
}
