package vn.codingt.clean.rsql.operator.comparision;

import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class Less extends JpaComparison {
    @Override
    protected Predicate makeOperator(Root<?> root, CriteriaBuilder builder, SqlPath lhs, Comparable rhsArg) {
        Path p = null;
        return builder.lessThan(p, rhsArg);
    }
}
