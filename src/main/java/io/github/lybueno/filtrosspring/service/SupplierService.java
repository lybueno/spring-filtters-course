package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.domain.Supplier;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
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
        return null;
    }
}
