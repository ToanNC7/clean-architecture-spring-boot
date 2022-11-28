package vn.codingt.clean.rsql.path;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.List;

public class DefaultSqlPath implements SqlPath {

    private final Path<?> path;

    public DefaultSqlPath(Path<?> path) {
        this.path = path;
    }

    @Override
    public Expression toExpression(CriteriaBuilder criteriaBuilder) {
        return path;
    }

    @Override
    public Class<?> getJavaType() {
        return path.getJavaType();
    }

    @Override
    public Predicate in(CriteriaBuilder builder, List<Object> args) {
        return path.in(args);
    }

    public Path<?> getPath(){return  path;}
}
