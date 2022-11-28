package vn.codingt.clean.rsql.operator.comparision;

import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public final class NotEqual extends JpaOperator {
    @Override
    public Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lsh, List<Object> rhsArgs, Object firstRshArg) {
        return  builder.notEqual(lsh.toExpression(builder), firstRshArg);
    }
}
