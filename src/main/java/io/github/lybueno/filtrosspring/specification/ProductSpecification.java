package io.github.lybueno.filtrosspring.specification;

import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ProductSpecification {

    public static Specification<Product> equal(EqualFilterModel eq){
        return (root, query, criteriaBuilder) -> {
            Expression<Product> expression = root.get(eq.getColumn());
            Predicate predicate = (eq.getIsEqual() ? criteriaBuilder.equal(expression, eq.getValue()) :
                    criteriaBuilder.notEqual(expression, eq.getValue()));
            return predicate;
        };
    }

}
