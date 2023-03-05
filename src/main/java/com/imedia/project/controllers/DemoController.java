package com.imedia.project.controllers;


import com.imedia.project.dto.CategoryDto;
import com.imedia.project.exceptions.MyException;
import com.imedia.project.services.IServiceInterface;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class DemoController {

    private final IServiceInterface service;
    

    @GetMapping("/demo")
    public String demo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return "demo";
    }

    @PostMapping("/create")
    public CategoryDto createCategory(@Valid @RequestBody CategoryDto categoryDto) throws MyException {
        return service.createCategory(categoryDto);
    }
}
