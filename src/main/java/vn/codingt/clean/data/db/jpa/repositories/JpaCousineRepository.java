package vn.codingt.clean.data.db.jpa.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import vn.codingt.clean.data.db.jpa.entities.CousineData;

public interface JpaCousineRepository extends JpaRepository<CousineData, Long> {

    List<CousineData> findByNameContainingIgnoreCase(String search);

}
