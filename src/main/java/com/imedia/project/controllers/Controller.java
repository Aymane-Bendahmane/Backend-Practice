package com.imedia.project.controllers;

import com.imedia.project.exceptions.ExceptionHandler;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.entites.*;
import com.imedia.project.services.IServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Category categoryByID(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @GetMapping("/convert")
    public ConversionDto convertDevise(@RequestParam String from, @RequestParam String to, @RequestParam String amount) throws Exception {
        return service.convertDevise(from, to, amount);
    }

    @GetMapping("/test")
    public String get() throws ExceptionHandler {
        throw new ExceptionHandler("my error meow meow", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
