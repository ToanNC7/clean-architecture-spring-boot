package vn.codingt.clean.rsql.operator.set;

import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class In extends JpaOperator {
    @Override
    public Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lhs, List<Object> rhsArgs, Object firstRshArg) {
        return lhs.in(builder, rhsArgs);
    }
}
