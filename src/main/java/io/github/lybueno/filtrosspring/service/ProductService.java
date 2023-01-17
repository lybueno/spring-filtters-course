package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.repository.ProductRepository;
import io.github.lybueno.filtrosspring.specification.ProductSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IListService<Product> {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> list() {
        List<Product> products = repository.findAll();
        return products;
    }

    @Override
    public PageModel<Product> list(FilterModel filter) {
        Pageable pageable = filter.toSpringPegeable();

        Specification<Product> specification = null;

        List<EqualFilterModel> equalFilters = filter.equalFilters();

        if(!equalFilters.isEmpty()){
            EqualFilterModel firstEqFilter = equalFilters.get(0);
            specification = ProductSpecification.equal(firstEqFilter);

            for (int i = 1; i < equalFilters.size(); i++) {
                specification = specification.and(ProductSpecification.equal(equalFilters.get(i)));
            }
        }

        Page<Product> productPage = repository.findAll(specification, pageable);
        PageModel<Product> pageModel = new PageModel<>(productPage);
        return pageModel;
    }
}
