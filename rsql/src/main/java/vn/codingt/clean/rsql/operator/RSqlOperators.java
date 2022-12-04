package vn.codingt.clean.rsql.operator;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

public abstract class RSqlOperators {

    public static final ComparisonOperator EQUAL_IGNORE_CASE = new ComparisonOperator(new String[]{"=eqIgCase=", "=eic="});
    public static final ComparisonOperator CONTAINS_IGNORE_CASE= new ComparisonOperator(new String[]{"=containsIgCase=", "=cic="});
    public static final ComparisonOperator NOT_CONTAINS_IGNORE_CASE = new ComparisonOperator(new String[] {"=notContainsIgCase=", "=ncic="});
    public static final ComparisonOperator CONTAINS= new ComparisonOperator(new String[]{"=contains=", "=like="});
    public static final ComparisonOperator NOT_CONTAINS= new ComparisonOperator(new String[]{"=notContains=", "=nlike="});
    public static final ComparisonOperator STARTS_WITH= new ComparisonOperator(new String[]{"=startsWith=", "=swic="});
    public static final ComparisonOperator STARTS_WITH_IGNORE_CASE= new ComparisonOperator(new String[]{"=startWithIgCase=", "=swic="});
    public static final ComparisonOperator ENDS_WITH= new ComparisonOperator(new String[]{"=endsWith=", "=ew="});
    public static final ComparisonOperator ENDS_WITH_IGNORE_CASE= new ComparisonOperator(new String[]{"=endsWithIgCase=", "=ewic="});
}
