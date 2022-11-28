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

public class EqualIgnoreCase extends JpaOperator {
    @Override
    public Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lhs, List<Object> rhsArgs, Object firstRshArg) {
        if (!(lhs.getJavaType().equals(String.class) && firstRshArg instanceof String)) {
            throw new RSQLSyntaxException("");
        }
        return builder.like(
                builder.lower(lhs.toExpression(builder)),
                builder.lower(new LiteralExpression<>((CriteriaBuilderImpl) builder, firstRshArg.toString())),
                getEscapeChar()
        );
    }
}
