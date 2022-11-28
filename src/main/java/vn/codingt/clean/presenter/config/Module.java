package vn.codingt.clean.presenter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import vn.codingt.clean.core.usecases.cousine.CousineRepository;
import vn.codingt.clean.core.usecases.cousine.GetAllCousinesUseCase;
import vn.codingt.clean.core.usecases.cousine.SearchCousinesByNameUseCase;
import vn.codingt.clean.core.usecases.post.CreateBulkPostUseCase;
import vn.codingt.clean.core.usecases.post.CreatePostUseCase;
import vn.codingt.clean.core.usecases.post.GetAllPostUseCase;
import vn.codingt.clean.core.usecases.post.PostRepository;
import vn.codingt.clean.core.usecases.post.SearchByTitleUseCase;
import vn.codingt.clean.core.usecases.user.CreateAUserUseCase;
import vn.codingt.clean.core.usecases.user.UserRepository;

@Configuration
public class Module {

    @Bean
    public CreatePostUseCase createPostUseCase(PostRepository postRepository, UserRepository userRepository){
        return new CreatePostUseCase(postRepository, userRepository);
    }

    @Bean
    public CreateBulkPostUseCase createBulkPostUseCase(PostRepository postRepository, UserRepository userRepository){
        return new CreateBulkPostUseCase(postRepository, userRepository);
    }
    @Bean
    public SearchByTitleUseCase searchByTitleUseCase(PostRepository repository){
        return new SearchByTitleUseCase(repository);
    }
    @Bean
    public GetAllPostUseCase getAllPostUseCase(PostRepository repository){
        return  new GetAllPostUseCase(repository);
    }

    @Bean
    public GetAllCousinesUseCase getAllCousinesUseCase(CousineRepository repository){
        return new GetAllCousinesUseCase(repository);
    }

    @Bean
    public SearchCousinesByNameUseCase searchCousineByNameUseCase(CousineRepository repository){
        return new SearchCousinesByNameUseCase(repository);
    }

    @Bean
    public CreateAUserUseCase createAUserUseCase(UserRepository repository) {
        return new CreateAUserUseCase(repository);
    }
}
