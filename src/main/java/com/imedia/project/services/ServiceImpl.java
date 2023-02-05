package com.imedia.project.services;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;
import com.imedia.project.repositories.CategoryRepository;
import com.imedia.project.repositories.ProductRepository;
import com.imedia.project.utilities.CategoryMapper;
import com.imedia.project.utilities.ProductMapper;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static com.imedia.project.utilities.Tools.ERROR_MESSAGE;
import static com.imedia.project.utilities.Tools.isNullOrEmpty;

@Service
@NoArgsConstructor
public class ServiceImpl implements IServiceInterface {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


    @Override
    public List<Product> productsList() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> productsListByCategory(String name) {
        Category category = getCategoryByName(name);
        return productRepository.findProductsByCategory(category);
    }

    @Override
    public List<Category> categoriesList() {
        return categoryRepository.findAll();
    }

    @Override
    public Product getProductById(Long id) {
        return productRepository.getReferenceById(id);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.getReferenceById(id);
    }

    @Override
    public Category getCategoryByName(String name) {
        if (isNullOrEmpty(name)) throw new IllegalArgumentException(ERROR_MESSAGE);
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    @Transactional
    public Product createProduct(ProductDto productDto) {
        Product product = ProductMapper.INSTANCE.dtoToProduct(productDto);
        if (product == null) throw new NullPointerException("Could not map the product");
        return productRepository.save(product);
    }

    @Override
    @Transactional
    public Category createCategory(CategoryDto categoryDto) {
        Category category = CategoryMapper.INSTANCE.dtoToCategory(categoryDto);
        if (category == null) throw new NullPointerException("Could not map the category");
        return categoryRepository.save(category);
    }

}
