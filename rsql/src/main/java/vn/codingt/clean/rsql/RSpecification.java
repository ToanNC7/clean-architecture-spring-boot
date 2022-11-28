package vn.codingt.clean.rsql;

import org.springframework.data.jpa.domain.Specification;

public interface RSpecification<T> extends Specification {

    static <T> RSpecification<T> not(Specification<T> specification){
        return specification == null
                ? (root, query, criteriaBuilder) -> null
                : ((root, query, criteriaBuilder) -> criteriaBuilder.not(specification.toPredicate(root, query, criteriaBuilder)));
    }

    static <T> Specification<T> where(Specification<T> specification){
        return specification == null ? ((root, query, criteriaBuilder) -> null) : specification;
    }

    default RSpecification<T> and(Specification other){
        return SpecificationComposition.composed(this, other, (criteriaBuilder, left, rhs) -> criteriaBuilder.and(left, rhs));
    }

    default RSpecification<T> or(Specification other){
        return SpecificationComposition.composed(this, other, (criteriaBuilder, left, rhs) -> criteriaBuilder.or(left, rhs));
    }
}
