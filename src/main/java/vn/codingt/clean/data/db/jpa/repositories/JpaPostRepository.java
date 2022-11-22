package vn.codingt.clean.data.db.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.codingt.clean.data.db.jpa.entities.MetaData;
import vn.codingt.clean.data.db.jpa.entities.PostData;

import java.util.List;

public interface JpaPostRepository extends JpaRepository<PostData, Long> {
    List<PostData> findByUser_Id(Long id);

    List<PostData> findByTitleLike(String title);

    @Query("select p from post p inner join p.meta meta where meta.post.id = ?1")
    List<MetaData> findByMeta_Post_Id(Long id);
}
