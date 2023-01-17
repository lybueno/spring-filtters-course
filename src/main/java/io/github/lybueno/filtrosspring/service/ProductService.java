package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
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
        Page<Product> productPage = repository.findAll(filter.toSpringPegeable());
        PageModel<Product> pageModel = new PageModel<>(productPage);
        return pageModel;
    }
}
