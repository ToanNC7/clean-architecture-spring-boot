package vn.codingt.clean.data.db.jpa.repositories;

import java.util.List;

import vn.codingt.clean.data.db.jpa.entities.CousineData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaCousineRepository extends JpaRepository<CousineData, Long> {

    List<CousineData> findByNameContainingIgnoreCase(String search);

}
