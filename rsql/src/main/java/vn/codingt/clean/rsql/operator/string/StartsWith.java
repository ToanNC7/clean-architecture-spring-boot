package vn.codingt.clean.rsql.operator.string;

import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;
import vn.codingt.clean.rsql.exception.RSQLSyntaxException;
import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class StartsWith extends JpaOperator {
    @Override
    public Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lhs, List<Object> rhsArgs, Object firstRshArg) {
        if (!(lhs.getJavaType().equals(String.class) && firstRshArg instanceof String)) {
            throw new RSQLSyntaxException("");
        }
        String rhsParam = escapeSqlWildCards((String) firstRshArg);
        rhsParam = "%" + rhsParam;
        return builder.like(lhs.toExpression(builder), rhsParam, getEscapeChar());
    }
}
