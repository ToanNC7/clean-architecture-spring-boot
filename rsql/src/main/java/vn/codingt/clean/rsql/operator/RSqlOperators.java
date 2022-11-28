package vn.codingt.clean.rsql.operator;

import cz.jirutka.rsql.parser.ast.ComparisonOperator;

public class RSqlOperators {

    public static final ComparisonOperator EQUAL_IGNORE_CASE = new ComparisonOperator("=eqIgCase=", "=eic=");
    public static final ComparisonOperator CONTAINS_IGNORE_CASE= new ComparisonOperator("=containsIgCase=", "=cic=");
    public static final ComparisonOperator NOT_CONTAINS_IGNORE_CASE = new ComparisonOperator("=notContainsIgCase=", "=ncic=");
    public static final ComparisonOperator CONTAINS= new ComparisonOperator("=contains=", "=like=");
    public static final ComparisonOperator NOT_CONTAINS= new ComparisonOperator("=notContains=", "=nlike=", "nc");
    public static final ComparisonOperator STARTS_WITH= new ComparisonOperator("=startsWith=", "=swic=");
    public static final ComparisonOperator STARTS_WITH_IGNORE_CASE= new ComparisonOperator("=startWithIgCase=", "=swic=");
    public static final ComparisonOperator ENDS_WITH= new ComparisonOperator("=endsWith=", "=ew=");
    public static final ComparisonOperator ENDS_WITH_IGNORE_CASE= new ComparisonOperator("=endsWithIgCase=", "=ewic=");
}
