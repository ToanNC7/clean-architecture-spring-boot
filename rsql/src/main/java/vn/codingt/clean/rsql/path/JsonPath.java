package vn.codingt.clean.rsql.path;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonPath implements SqlPath{

    private final Path<?> path;
    private final String[] jsonPathElements;
    private final JsonType jsonType;

    public JsonPath(Path<?> path, String[] jsonPathElements, JsonType jsonType) {
        this.path = path;
        this.jsonPathElements = jsonPathElements;
        this.jsonType = jsonType;
    }


    @Override
    public Expression toExpression(CriteriaBuilder criteriaBuilder) {
        List<Expression<?>> listArg = new ArrayList<>();
        listArg.add(path);
        Arrays.stream(jsonPathElements).map(criteriaBuilder::literal).forEach(listArg::add);
        return criteriaBuilder.function(jsonType.getExtractPathFunction(), String.class, listArg.toArray(new Expression[0]));
    }

    @Override
    public Class<?> getJavaType() {
        return String.class;
    }

    @Override
    public Predicate in(CriteriaBuilder builder, List<Object> args) {
        return toExpression(builder).in(args);
    }
}
