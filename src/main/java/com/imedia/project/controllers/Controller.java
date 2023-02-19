package com.imedia.project.controllers;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.exceptions.MyException;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.entites.*;
import com.imedia.project.services.IServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
public class Controller {

    @Autowired
    IServiceInterface service;

    @GetMapping("/products")
    public List<Product> productsList() {
        return service.productsList();
    }

    @PostMapping("/product/create")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) throws MyException {
        return service.createProduct(productDto);
    }

    @PostMapping("/category/create")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) throws MyException {
        return service.createCategory(categoryDto);
    }

    @GetMapping("/categories")
    public List<Category> categoryList() {
        return service.categoriesList();
    }

    @GetMapping("/productsByCategory/{name}")
    public List<Product> productsListByCategory(@PathVariable String name) {
        return service.productsListByCategory(name);
    }

    @GetMapping("/product/{id}")
    public Product productByID(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @GetMapping("/category/{id}")
    public Category categoryByID(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @GetMapping("/convert")
    public ConversionDto convertDevise(@RequestParam String from, @RequestParam String to, @RequestParam String amount) throws Exception {
        return service.convertDevise(from, to, amount);
    }

    @GetMapping("/test")
    public String get() throws MyException {
        throw new MyException("my error meow meow", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
