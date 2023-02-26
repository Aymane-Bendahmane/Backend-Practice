package com.imedia.project.controllers;


import com.imedia.project.dto.CategoryDto;
import com.imedia.project.exceptions.MyException;
import com.imedia.project.services.IServiceInterface;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
@RequestMapping("/categories")
public class CategoryController {

    private final IServiceInterface service;


    @GetMapping
    public List<CategoryDto> categoryList() {
        return service.categoriesList();
    }

    @GetMapping("/id/{id}")
    public CategoryDto categoryByID(@PathVariable Long id) {
        return service.getCategoryById(id);
    }

    @PostMapping("/create")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) throws MyException {
        return service.createCategory(categoryDto);
    }
}
