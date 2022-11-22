package vn.codingt.clean.data.db.jpa.repositories.impl;

import org.springframework.stereotype.Repository;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.usecases.post.PostRepository;
import vn.codingt.clean.data.db.jpa.entities.MetaData;
import vn.codingt.clean.data.db.jpa.entities.PostData;
import vn.codingt.clean.data.db.jpa.repositories.JpaPostRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class PostRepositoryImpl implements PostRepository {

    private final JpaPostRepository postRepository;

    public PostRepositoryImpl(JpaPostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public List<Post> getAll() {
        return postRepository
                .findAll()
                .parallelStream()
                .map(PostData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> searchByTitle(String title) {
        return postRepository.findByTitleLike(title)
                .parallelStream()
                .map(PostData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> searchByUserId(Long userId) {
        return postRepository.findByUser_Id(userId)
                .parallelStream()
                .map(PostData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Post> getById(Identity id) {
        return postRepository.findById(id.getNumber())
                .map(PostData::fromThis);
    }

    @Override
    public List<Meta> getMetaById(Identity id) {
        return postRepository.findByMeta_Post_Id(id.getNumber())
                .stream().map(MetaData::fromThis)
                .collect(Collectors.toList());
    }
}
