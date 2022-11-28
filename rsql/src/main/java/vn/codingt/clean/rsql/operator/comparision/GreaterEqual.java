package vn.codingt.clean.rsql.operator.comparision;

import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class GreaterEqual extends JpaComparison {
    @Override
    protected Predicate makeOperator(Root<?> root, CriteriaBuilder builder, SqlPath lhs, Comparable rhsArg) {
        return builder.greaterThanOrEqualTo(lhs.toExpression(builder), rhsArg);
    }
}
