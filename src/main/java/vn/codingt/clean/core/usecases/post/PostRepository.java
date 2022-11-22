package vn.codingt.clean.core.usecases.post;

import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.domain.Post;

import java.util.List;
import java.util.Optional;

public interface PostRepository {

    List<Post> getAll();

    List<Post> searchByTitle(String title);

    List<Post> searchByUserId(Long userId);

    Optional<Post> getById(Identity id);

    List<Meta> getMetaById(Identity id);
}
