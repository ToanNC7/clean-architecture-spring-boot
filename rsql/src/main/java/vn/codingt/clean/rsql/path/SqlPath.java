package vn.codingt.clean.rsql.path;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import java.util.List;

public interface SqlPath {

    Expression toExpression(CriteriaBuilder criteriaBuilder);

    Class<?> getJavaType();

    Predicate in(CriteriaBuilder builder, List<Object> args);

}
