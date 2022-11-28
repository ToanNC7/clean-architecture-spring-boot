package vn.codingt.clean.rsql;

import cz.jirutka.rsql.parser.ast.AndNode;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.OrNode;
import cz.jirutka.rsql.parser.ast.RSQLVisitor;
import org.springframework.data.jpa.domain.Specification;

public class JpaRSqlVisitor<T> implements RSQLVisitor<Specification<T>, Void> {

    private String sort;

    public JpaRSqlVisitor(String sort){
        this.sort = sort;
    }

    private final  JpaRSqlSpecBuilder<T> builder = new JpaRSqlSpecBuilder<>();

    @Override
    public Specification<T> visit(AndNode andNode, Void unused) {
        return builder.createSpecification(andNode, sort);
    }

    @Override
    public Specification<T> visit(OrNode orNode, Void unused) {
        return builder.createSpecification(orNode, sort);
    }

    @Override
    public Specification<T> visit(ComparisonNode comparisonNode, Void unused) {
        return builder.createSpecification(comparisonNode, sort);
    }
}
