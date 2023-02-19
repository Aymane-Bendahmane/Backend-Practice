package com.imedia.project.services;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;
import com.imedia.project.exceptions.MyException;
import java.util.List;

public interface IServiceInterface {
    List<Product> productsList();

    List<Category> categoriesList();

    List<Product> productsListByCategory(String name);

    Product getProductById(Long id);

    Category getCategoryById(Long id);

    Category getCategoryByName(String name);

    ProductDto createProduct(ProductDto productDto) throws MyException;

    CategoryDto createCategory(CategoryDto categoryDto) throws MyException;

    ConversionDto convertDevise(String deviseSource , String deviseTarget , String amount) throws MyException;

    void initData();

}
