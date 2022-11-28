package vn.codingt.clean.rsql.operator.string;

import vn.codingt.clean.rsql.exception.RSQLSyntaxException;
import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class EndsWith extends JpaOperator {
    @Override
    public Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lhs, List<Object> rhsArgs, Object firstRshArg) {
        if (!(lhs.getJavaType().equals(String.class) && firstRshArg instanceof String)) {
            throw new RSQLSyntaxException("");
        }
        String rhs = escapeSqlWildCards((String) firstRshArg);
        rhs = "%" + rhs;
        return builder.like(lhs.toExpression(builder), rhs, getEscapeChar());
    }
}
