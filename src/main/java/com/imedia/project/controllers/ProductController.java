package com.imedia.project.controllers;

import com.imedia.project.aspj.LogExecutionTime;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.exceptions.MyException;
import com.imedia.project.services.IServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final IServiceInterface service;


    @GetMapping()
    @LogExecutionTime
    public List<ProductDto> productsList() {
        return service.productsList();
    }

    @PostMapping("/create")
    public ProductDto createProduct(@Valid @RequestBody ProductDto productDto) throws MyException {
        return service.createProduct(productDto);
    }

    @GetMapping("/name/{name}")
    public List<ProductDto> productsListByCategory(@PathVariable String name) {
        return service.productsListByCategory(name);
    }

    @GetMapping("/id/{id}")
    public ProductDto productByID(@PathVariable Long id) {
        return service.getProductById(id);
    }

    @GetMapping("/convert")
    @LogExecutionTime
    public ConversionDto convertDevise(@RequestParam String from, @RequestParam String to, @RequestParam String amount) throws Exception {
        return service.convertDevise(from, to, amount);
    }
}
