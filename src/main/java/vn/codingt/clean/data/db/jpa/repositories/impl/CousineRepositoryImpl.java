package vn.codingt.clean.data.db.jpa.repositories.impl;

import java.time.chrono.JapaneseChronology;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import vn.codingt.clean.core.domain.Cousine;
import vn.codingt.clean.core.usecases.cousine.CousineRepository;
import vn.codingt.clean.data.db.jpa.entities.CousineData;
import vn.codingt.clean.data.db.jpa.repositories.JpaCousineRepository;

@Repository
public class CousineRepositoryImpl implements CousineRepository {

    private final JpaCousineRepository jpaCousineRepository;

    public CousineRepositoryImpl(JpaCousineRepository jpaCousineRepository) {
        this.jpaCousineRepository = jpaCousineRepository;
    }

    @Override
    public List<Cousine> getAll() {

        return jpaCousineRepository.findAll()
                .parallelStream()
                .map(CousineData::fromThis)
                .collect(Collectors.toList());
    }

    @Override
    public List<Cousine> searchByName(String name) {
        return jpaCousineRepository
                .findByNameContainingIgnoreCase(name)
                .parallelStream()
                .map(CousineData::fromThis)
                .collect(Collectors.toList());
    }

}
