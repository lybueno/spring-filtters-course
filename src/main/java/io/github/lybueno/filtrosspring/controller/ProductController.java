package io.github.lybueno.filtrosspring.controller;

import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<PageModel<Product>> list(@RequestParam Map<String, String> params){
        FilterModel filter = new FilterModel(params);
        PageModel<Product> products = service.list(filter);
        return ResponseEntity.ok(products);
    }
}
