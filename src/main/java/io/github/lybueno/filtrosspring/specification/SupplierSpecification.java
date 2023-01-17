package io.github.lybueno.filtrosspring.specification;

import io.github.lybueno.filtrosspring.builder.ExpressionBuilder;
import io.github.lybueno.filtrosspring.domain.Supplier;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class SupplierSpecification {

    public static Specification<Supplier> equal(EqualFilterModel eq){
        return new Specification<Supplier>() {

            @Override
            public Predicate toPredicate(Root<Supplier> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ExpressionBuilder<Supplier> expressionBuilder = new ExpressionBuilder<>(Supplier.class);
                Expression<Supplier> expression = expressionBuilder.get(root, eq.getColumn());

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
