package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.domain.Category;
import io.github.lybueno.filtrosspring.domain.Supplier;
import io.github.lybueno.filtrosspring.model.EqualFilterModel;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.repository.SupplierRepository;
import io.github.lybueno.filtrosspring.specification.CategorySpecification;
import io.github.lybueno.filtrosspring.specification.SupplierSpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SupplierService implements IListService<Supplier> {

    @Autowired
    private SupplierRepository repository;

    @Override
    public List<Supplier> list() {
        List<Supplier> suppliers = repository.findAll();
        return suppliers;
    }

    @Override
    public PageModel<Supplier> list(FilterModel filter) {
        Pageable pageable = filter.toSpringPegeable();

        Specification<Supplier> specification = null;

        List<EqualFilterModel> equalFilters = filter.equalFilters();

        if(!equalFilters.isEmpty()){
            EqualFilterModel firstEqFilter = equalFilters.get(0);
            specification = SupplierSpecification.equal(firstEqFilter);

            for (int i = 1; i < equalFilters.size(); i++) {
                specification = specification.and(SupplierSpecification.equal(equalFilters.get(i)));
            }
        }
        Page<Supplier> supplierPage = repository.findAll(specification, pageable);
        PageModel<Supplier> pageModel = new PageModel<>(supplierPage);
        return pageModel;
    }
}
