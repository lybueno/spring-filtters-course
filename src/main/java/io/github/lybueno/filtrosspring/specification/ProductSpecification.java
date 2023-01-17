package io.github.lybueno.filtrosspring.specification;

import io.github.lybueno.filtrosspring.builder.ExpressionBuilder;
import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class ProductSpecification {

    public static Specification<Product> equal(EqualFilterModel eq){
        return new Specification<Product>() {

            @Override
            public Predicate toPredicate(Root<Product> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ExpressionBuilder<Product> expressionBuilder = new ExpressionBuilder<>(Product.class);
                Expression<Product> expression = expressionBuilder.get(root, eq.getColumn());

                Predicate predicate = null;

                if(expression != null){
                    predicate = (eq.getIsEqual() ? criteriaBuilder.equal(expression, eq.getValue()) :
                            criteriaBuilder.notEqual(expression, eq.getValue()));
                }

                return predicate;
            }
        };
    }

}
