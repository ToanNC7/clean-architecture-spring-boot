package vn.codingt.clean.rsql.operator;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.RSQLOperators;
import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.operator.comparision.Equal;
import vn.codingt.clean.rsql.operator.comparision.Greater;
import vn.codingt.clean.rsql.operator.comparision.GreaterEqual;
import vn.codingt.clean.rsql.operator.comparision.Less;
import vn.codingt.clean.rsql.operator.comparision.LessEqual;
import vn.codingt.clean.rsql.operator.comparision.NotEqual;
import vn.codingt.clean.rsql.operator.set.In;
import vn.codingt.clean.rsql.operator.set.Out;
import vn.codingt.clean.rsql.operator.string.Contains;
import vn.codingt.clean.rsql.operator.string.ContainsIgnoreCase;
import vn.codingt.clean.rsql.operator.string.EndsWith;
import vn.codingt.clean.rsql.operator.string.EndsWithIgnoreCase;
import vn.codingt.clean.rsql.operator.string.EqualIgnoreCase;
import vn.codingt.clean.rsql.operator.string.NotContains;
import vn.codingt.clean.rsql.operator.string.NotContainsIgnoreCase;
import vn.codingt.clean.rsql.operator.string.StartsWith;
import vn.codingt.clean.rsql.operator.string.StartsWithIgnoreCase;

import java.util.HashMap;

public class RSqlSearchOperations {

    private static final HashMap<ComparisonOperator, JpaOperator> MAPPER = new HashMap<>();

    static {
        MAPPER.put(RSQLOperators.EQUAL, new Equal());
        MAPPER.put(RSQLOperators.NOT_EQUAL, new NotEqual());
        MAPPER.put(RSQLOperators.GREATER_THAN, new Greater());
        MAPPER.put(RSQLOperators.GREATER_THAN_OR_EQUAL, new GreaterEqual());
        MAPPER.put(RSQLOperators.LESS_THAN, new Less());
        MAPPER.put(RSQLOperators.LESS_THAN_OR_EQUAL, new LessEqual());
        MAPPER.put(RSQLOperators.IN, new In());
        MAPPER.put(RSQLOperators.NOT_IN, new Out());
        MAPPER.put(RSqlOperators.EQUAL_IGNORE_CASE, new EqualIgnoreCase());
        MAPPER.put(RSqlOperators.CONTAINS_IGNORE_CASE, new ContainsIgnoreCase());
        MAPPER.put(RSqlOperators.NOT_CONTAINS_IGNORE_CASE, new NotContainsIgnoreCase());
        MAPPER.put(RSqlOperators.CONTAINS, new Contains());
        MAPPER.put(RSqlOperators.NOT_CONTAINS, new NotContains());
        MAPPER.put(RSqlOperators.STARTS_WITH, new StartsWith());
        MAPPER.put(RSqlOperators.STARTS_WITH_IGNORE_CASE, new StartsWithIgnoreCase());
        MAPPER.put(RSqlOperators.ENDS_WITH, new EndsWith());
        MAPPER.put(RSqlOperators.ENDS_WITH_IGNORE_CASE, new EndsWithIgnoreCase());
    }

    public static JpaOperator mapOperator(ComparisonOperator operator){
        return MAPPER.get(operator);
    }
}
