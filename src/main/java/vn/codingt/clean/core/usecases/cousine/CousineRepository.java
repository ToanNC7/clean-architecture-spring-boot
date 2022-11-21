package vn.codingt.clean.core.usecases.cousine;

import java.util.List;

import vn.codingt.clean.core.domain.Cousine;

public interface CousineRepository {

    List<Cousine> getAll();

    List<Cousine> searchByName(String name);

}
