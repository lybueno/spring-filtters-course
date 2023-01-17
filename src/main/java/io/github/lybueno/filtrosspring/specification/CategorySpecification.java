package io.github.lybueno.filtrosspring.specification;

import io.github.lybueno.filtrosspring.builder.ExpressionBuilder;
import io.github.lybueno.filtrosspring.domain.Category;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import jakarta.persistence.criteria.*;
import lombok.Data;
import org.springframework.data.jpa.domain.Specification;

@Data
public class CategorySpecification {

    public static Specification<Category> equal(EqualFilterModel eq){
        return new Specification<Category>() {

            @Override
            public Predicate toPredicate(Root<Category> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                ExpressionBuilder<Category> expressionBuilder = new ExpressionBuilder<>(Category.class);
                Expression<Category> expression = expressionBuilder.get(root, eq.getColumn());

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
