package vn.codingt.clean.rsql.operator;

import vn.codingt.clean.rsql.path.SqlPath;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public abstract class JpaOperator {

    public abstract Predicate buildPredicate(Root<?> root, CriteriaBuilder builder, SqlPath lhs, List<Object> rhsArgs, Object firstRshArg);

    protected final String escapeSqlWildCards(String s){
        char escapeChar = getEscapeChar();
        return s.replace("%", escapeChar + "%")
                .replace("_", escapeChar  + "_");
    }

    protected char getEscapeChar(){return '\\';}
}
