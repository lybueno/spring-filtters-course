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
        Integer page = filter.getPage();
        Integer limit = filter.getLimit();

        Page<Category> categoryPage = repository.findAll(PageRequest.of(page, limit));

        List<Category> categories = categoryPage.getContent();
        Long totalElements = categoryPage.getTotalElements();
        Integer currentPage = categoryPage.getNumber();
        Integer totalPages = categoryPage.getTotalPages();
        Integer pageSize = categoryPage.getSize();

        PageModel<Category> pageModel = new PageModel<>(totalElements, currentPage, totalPages, pageSize, categories);
        return pageModel;
    }
}
