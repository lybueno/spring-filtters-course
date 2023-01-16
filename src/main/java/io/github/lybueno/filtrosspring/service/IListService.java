package io.github.lybueno.filtrosspring.service;

import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;

import java.util.List;

public interface IListService<T> {

    List<T> list();

    PageModel<T> list(FilterModel filter);
}
