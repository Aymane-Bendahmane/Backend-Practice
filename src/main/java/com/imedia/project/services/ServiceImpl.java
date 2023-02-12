package com.imedia.project.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imedia.project.exceptions.ExceptionHandler;
import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;
import com.imedia.project.repositories.*;
import com.imedia.project.mappers.*;
import com.imedia.project.utilities.HttpRequestsExecutor;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.imedia.project.utilities.Tools.*;

@Service
@NoArgsConstructor
public class ServiceImpl implements IServiceInterface {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    HttpRequestsExecutor httpRequestsExecutor;
    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

    ObjectMapper objectMapper = new ObjectMapper();
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

    /**
     * This method convert a given amount from a currency to a target one using external api
     * @param deviseSource
     * @param deviseTarget
     * @param amount
     * @return ConversionDto
     * @throws Exception
     *
     * @Author Aymane BENDAHMANE
     */
    @Override
    public ConversionDto convertDevise(String deviseSource, String deviseTarget, String amount) throws Exception {
        if (isNullOrEmpty(deviseSource) || isNullOrEmpty(deviseTarget) || isNullOrEmpty(amount))
            throw new ExceptionHandler(BAD_PARAMETER, HttpStatus.BAD_REQUEST);
        ConversionDto conversionDto;
        try {
            Map<String, String> params = new HashMap<>();
            params.put(FROM, deviseSource);
            params.put(TO, deviseTarget);
            params.put(AMOUNT, amount);
            conversionDto = objectMapper.convertValue(httpRequestsExecutor.executeGet(params),ConversionDto.class);
        }catch(IllegalArgumentException e){
            throw new ExceptionHandler(ERROR_CASTING_OBJECTS, HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (RestClientException e) {
            throw new ExceptionHandler(ERROR_EXTERNAL_API_CALL,HttpStatus.INTERNAL_SERVER_ERROR);
        }catch (Exception e){
            throw new ExceptionHandler(INTERNAL_SERVER_ERROR,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return conversionDto ;
    }

}
