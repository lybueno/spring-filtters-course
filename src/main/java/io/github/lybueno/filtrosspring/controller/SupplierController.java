package io.github.lybueno.filtrosspring.controller;

import io.github.lybueno.filtrosspring.domain.Supplier;
import io.github.lybueno.filtrosspring.model.FilterModel;
import io.github.lybueno.filtrosspring.model.PageModel;
import io.github.lybueno.filtrosspring.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "suppliers")
public class SupplierController {

    @Autowired
    private SupplierService service;

    @GetMapping
    public ResponseEntity<PageModel<Supplier>> list(@RequestParam Map<String, String> params){
        FilterModel filter = new FilterModel(params);
        PageModel<Supplier> suppliers = service.list(filter);
        return ResponseEntity.ok(suppliers);
    }
}
