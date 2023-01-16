package io.github.lybueno.filtrosspring.controller;

import io.github.lybueno.filtrosspring.domain.Category;
import io.github.lybueno.filtrosspring.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "categories")
public class CategoryController {

    @Autowired
    private CategoryService service;

    @GetMapping
    public ResponseEntity<List<Category>> list(){
        List<Category> categories = service.list();
        return ResponseEntity.ok(categories);
    }
}
