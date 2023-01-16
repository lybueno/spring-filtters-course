package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.domain.Category;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        Page<Category> categoryPage = repository.findAll(filter.toSpringPegeable());
        PageModel<Category> pageModel = new PageModel<>(categoryPage);
        return pageModel;
    }
}
