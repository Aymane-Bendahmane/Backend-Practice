package com.imedia.project.controllers;

import com.imedia.project.entites.*;
import com.imedia.project.services.IServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    IServiceInterface service;

    @GetMapping("/products")
    public List<Product> productsList() {
        return service.productsList();
    }

    @GetMapping("/categories")
    public List<Category> categoryList() {
        return service.categoriesList();
    }

    @GetMapping("/productsByCategory")
    public List<Product> productsListByCategory(@PathVariable String name) {
        return service.productsListByCategory(name);
    }

    @GetMapping("/product")
    public Product productByID(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @GetMapping("/category")
    public Category categoryByID(@PathVariable Long id){
        return service.getCategoryById(id);
    }
}
