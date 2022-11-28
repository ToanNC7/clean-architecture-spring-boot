package vn.codingt.clean.rsql.operator.comparision;

import vn.codingt.clean.rsql.exception.RSQLSyntaxException;
import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract  class JpaComparison extends JpaOperator {


    @Override
    public Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lhs, List<Object> rhsArgs, Object firstRshArg) {

        boolean isComparison = Comparable.class.isAssignableFrom(lhs.getJavaType());
        if(isComparison){
            if(firstRshArg instanceof Comparable){
                Comparable rhsArg = (Comparable) firstRshArg;
                return makeOperator(root, builder, lhs, rhsArg);
            }
            else {
                throw new RSQLSyntaxException("Cannot perform comparing with data type"+ firstRshArg.getClass());
            }
        }else {
            throw new RSQLSyntaxException("Cannot perform comparing with data type"+ firstRshArg.getClass());
        }
    }

    protected abstract Predicate makeOperator(Root<?> root, CriteriaBuilder builder, SqlPath lhs, Comparable rhsArg);
}
