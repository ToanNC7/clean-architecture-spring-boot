package vn.codingt.clean.rsql;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import vn.codingt.clean.rsql.exception.RSQLSyntaxException;
import vn.codingt.clean.rsql.operator.JpaOperator;
import vn.codingt.clean.rsql.operator.RSqlSearchOperations;
import vn.codingt.clean.rsql.path.PathParser;
import vn.codingt.clean.rsql.path.SqlPath;
import vn.codingt.clean.rsql.util.TypeMapper;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class JpaRSqlSpecification<T> implements RSpecification<T> {



    private String selector;
    private ComparisonOperator operator;
    private List<String> arguments;
    private String sort;



    @Override
    public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder criteriaBuilder) {

        JpaOperator rSqlOperator = RSqlSearchOperations.mapOperator(this.operator);

        if(rSqlOperator == null){
            throw new RSQLSyntaxException("");
        }
        if(!sort.isEmpty()){
            if(!sort.contains(".")){
                query.distinct(true);
            }
        }
        else {
            query.distinct(true);
        }
        SqlPath lhs = new PathParser().parseSelector(root, selector);

        List<Object> rhs = caseArgument(lhs, arguments);

        Object firstArg = rhs.get(0);
        return rSqlOperator.buildPredicate(root, criteriaBuilder, lhs, rhs, firstArg);
    }

    private List<Object> caseArgument(SqlPath lhs, List<String> arguments) {
        Class<?> type = lhs.getJavaType();
        List<Object>  list = new ArrayList<>();
        for(String arg: arguments){
            Object value = TypeMapper.map(type, arg);
            list.add(value);
        }
        return list;
    }
}
