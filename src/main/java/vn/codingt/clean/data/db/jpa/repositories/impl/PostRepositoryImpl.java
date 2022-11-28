package vn.codingt.clean.data.db.jpa.repositories.impl;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import vn.codingt.clean.core.domain.Identity;
import vn.codingt.clean.core.domain.Meta;
import vn.codingt.clean.core.domain.Post;
import vn.codingt.clean.core.usecases.post.PostRepository;
import vn.codingt.clean.core.util.helper.SaveAllJdbcBatchCallable;
import vn.codingt.clean.data.db.jpa.entities.MetaData;
import vn.codingt.clean.data.db.jpa.entities.PostData;
import vn.codingt.clean.data.db.jpa.entities.UserData;
import vn.codingt.clean.data.db.jpa.repositories.JpaPostRepository;

import javax.persistence.Table;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Repository
public class PostRepositoryImpl extends SaveAllJdbcBatchCallable<PostData> implements PostRepository {

    private final JpaPostRepository postRepository;

    public PostRepositoryImpl(JpaPostRepository postRepository){
        this.postRepository = postRepository;
    }

    @Override
    public Post persist(Post post) {
        final PostData postData = PostData.from(post);
        return postRepository.save(postData).fromThis();
    }


    @Value("${spring.jpa.properties.hibernate.jdbc.batch_size}")
    private int batchSize;


    @Autowired
    private HikariDataSource hikariDataSource;

    @Override
    public List<Post> persistBulk(List<Post> post) {

        List<PostData> postDataList = PostData.fromList(post);

        String sql = String.format(
                "INSERT INTO %s (address, email, name, password) " +
                        "VALUES (?, ?, ?, ?)",
                UserData.class.getAnnotation(Table.class).name()
        );
        saveAllJdbcBatchCallable(hikariDataSource, batchSize, postDataList, sql);

        return postDataList.stream().map(PostData::fromThis).collect(Collectors.toList());
    }

    @Override
    public List<Post> getAll() {
        return StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(
                        postRepository.findAll().iterator(),
                        Spliterator.ORDERED
                ), false)
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
            @Override
    public void setStatement(PreparedStatement statement, PostData o) throws SQLException {
        statement.setString(1, o.getMetaTitle());
    }
}
