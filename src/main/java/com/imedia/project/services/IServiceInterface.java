package com.imedia.project.services;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;

import java.util.List;

public interface IServiceInterface {
    List<Product> productsList();

    List<Category> categoriesList();

    List<Product> productsListByCategory(String name);

    Product getProductById(Long id);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    Product createProduct(ProductDto productDto);

    Category createCategory(CategoryDto categoryDto);

    ConversionDto convertDevise(String deviseSource , String deviseTarget , String amount) throws Exception;

}
