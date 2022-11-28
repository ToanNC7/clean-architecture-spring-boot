package vn.codingt.clean.rsql.sort;

import org.springframework.data.jpa.domain.Specification;
import vn.codingt.clean.rsql.path.PathParser;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

public class SortBuilder<T> implements Specification<T> {

    private  final String sortField;

    public SortBuilder(String sortField){
        this.sortField= sortField;
    }


    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {

        List<Order> orderList = parseSort(root, sortField, criteriaBuilder);
        query.orderBy(orderList);
        return criteriaBuilder.conjunction();
    }

    private List<Order> parseSort(Root<T> root, String sortQuery, CriteriaBuilder criteriaBuilder) {
        if(sortQuery == null|| sortQuery.trim().isEmpty()){
            return new ArrayList<>();
        }
        PathParser pathParser = new PathParser();

        List<Order> orderList = new ArrayList<>();

        String[] sortFields = sortQuery.split(";");
        for(String sortField: sortFields){
            String[] parts = sortField.split(",");
            if(parts.length > 2){
                Expression  path = pathParser.parseSelector(root, parts[0]).toExpression(criteriaBuilder);
                String orderStr = parts[1];
                Order order  = isAscOrder(orderStr.trim())?  criteriaBuilder.asc(path):  criteriaBuilder.desc(path);
                orderList.add(order);
            }
        }
        return orderList;
    }

    private boolean isAscOrder(String order) {
        if(order.startsWith("A") || order.startsWith("a")){
            return true;
        }
        try {
            int number = Integer.parseInt(order);
            return number > 0;
        }
        catch (NumberFormatException e){

        }
        return false;
    }
}
