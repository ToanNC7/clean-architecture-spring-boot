package vn.codingt.clean.rsql;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;

public class SpecificationComposition {

    interface  Combiner extends Serializable{
        Predicate combiner(CriteriaBuilder criteriaBuilder, Predicate lhs, Predicate rhs);
    }

    static <T> RSpecification<T> composed(Specification<T> lhs, Specification<T> rhs, Combiner combiner){
        return ((root, query, criteriaBuilder) -> {
            Predicate otherPredicate = toPredicate(lhs, root, query, criteriaBuilder);
            Predicate thisPredicate = toPredicate(rhs, root, query, criteriaBuilder);
            if(thisPredicate == null){
                return otherPredicate;
            }

            return otherPredicate == null ? thisPredicate : combiner.combiner(criteriaBuilder, thisPredicate, otherPredicate);
        });
    }

    private static <T> Predicate toPredicate(Specification<T> specification, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder){
        return specification  != null ? specification.toPredicate(root, query, builder) : null;
    }

}
