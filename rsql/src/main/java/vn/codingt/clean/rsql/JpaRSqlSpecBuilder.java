package vn.codingt.clean.rsql;

import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.ComparisonOperator;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.LogicalOperator;
import cz.jirutka.rsql.parser.ast.Node;
import cz.jirutka.rsql.parser.ast.RSQLOperators;
import org.springframework.data.jpa.domain.Specification;
import vn.codingt.clean.rsql.exception.RSQLSyntaxException;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class JpaRSqlSpecBuilder<T> {


    public static <T>Specification<T> build(String query, String sort) throws RSQLSyntaxException{
        if(query == null && query.isEmpty()){
            return Specification.where(null);
        }

        Set<ComparisonOperator> operators = RSQLOperators.defaultOperators();


        RSQLParser rsqlParser = new RSQLParser(operators);

        try {
            Node rootNode = rsqlParser.parse(query);
            return  rootNode.accept(new JpaRSqlVisitor<>(sort));
        }
        catch (RSQLSyntaxException e)
        {
            throw  new RSQLSyntaxException("Syntax Error "+ e.getMessage(), e);
        }
    }


    public RSpecification<T> createSpecification(LogicalNode logicalNode, String sort){
        List<RSpecification<T>>  specs = logicalNode.getChildren()
                .stream().map(t-> createSpecification(t, sort))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        RSpecification<T> result = specs.get(0);

        if(logicalNode.getOperator() == LogicalOperator.AND){
            for(int i = 1; i< specs.size(); i++){
                result= result.and(specs.get(i));
            }
        }
        if(logicalNode.getOperator() == LogicalOperator.OR){
            for(int i = 1; i< specs.size(); i++){
                result= result.or(specs.get(i));
            }
        }

        return  result;
    }

    public RSpecification<T> createSpecification(ComparisonNode comparisonNode, String sort){
        return new JpaRSqlSpecification<>(
                comparisonNode.getSelector(),
                comparisonNode.getOperator(),
                comparisonNode.getArguments(),
                sort
        );
    }

    RSpecification<T> createSpecification(Node node, String sort){
        if(node instanceof LogicalNode){
            return createSpecification(node, sort);
        }
        if(node instanceof ComparisonNode){
            return createSpecification(node, sort);
        }
        return null;
    }
}
