package io.github.lybueno.filtrosspring.controller;

import io.github.lybueno.filtrosspring.domain.Product;
import io.github.lybueno.filtrosspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping
    public ResponseEntity<List<Product>> list(){
        List<Product> products = service.list();
        return ResponseEntity.ok(products);
    }
}
