package vn.codingt.clean.data.db.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.codingt.clean.data.db.jpa.entities.MetaData;

public interface JpaMetaRepository extends JpaRepository<MetaData, Long> {
}
