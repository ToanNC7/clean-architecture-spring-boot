package vn.codingt.clean.data.db.jpa.repositories;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vn.codingt.clean.data.db.jpa.entities.MetaData;
import vn.codingt.clean.data.db.jpa.entities.PostData;

import java.util.List;

public interface JpaPostRepository extends CrudRepository<PostData, Long>, JpaSpecificationExecutor<PostData> {
    List<PostData> findByUser_Id(Long id);

    List<PostData> findByTitleLike(String title);

    @Query("select p from post p inner join p.meta meta where meta.post.id = ?1")
    List<MetaData> findByMeta_Post_Id(Long id);
}
