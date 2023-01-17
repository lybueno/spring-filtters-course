package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.domain.Category;
import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.repository.CategoryRepository;
import io.github.lybueno.filtrosspring.specification.CategorySpecification;
import io.github.lybueno.filtrosspring.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements IListService<Category> {

    @Autowired
    private CategoryRepository repository;

    @Override
    public List<Category> list() {
        List<Category> categories = repository.findAll();
        return categories;
    }

    @Override
    public PageModel<Category> list(FilterModel filter) {
        Pageable pageable = filter.toSpringPegeable();

        Specification<Category> specification = null;

        List<EqualFilterModel> equalFilters = filter.equalFilters();

        if(!equalFilters.isEmpty()){
            EqualFilterModel firstEqFilter = equalFilters.get(0);
            specification = CategorySpecification.equal(firstEqFilter);

            for (int i = 1; i < equalFilters.size(); i++) {
                specification = specification.and(CategorySpecification.equal(equalFilters.get(i)));
            }
        }
        Page<Category> categoryPage = repository.findAll(specification, pageable);
        PageModel<Category> pageModel = new PageModel<>(categoryPage);
        return pageModel;
    }
}
