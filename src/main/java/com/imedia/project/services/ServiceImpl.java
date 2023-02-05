package com.imedia.project.services;

import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;
import com.imedia.project.repositories.CategoryRepository;
import com.imedia.project.repositories.ProductRepository;
import com.imedia.project.utilities.ApiConfig;
import com.imedia.project.utilities.CategoryMapper;
import com.imedia.project.utilities.ProductMapper;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.imedia.project.utilities.Tools.ERROR_MESSAGE;
import static com.imedia.project.utilities.Tools.isNullOrEmpty;

@Service
@NoArgsConstructor
public class ServiceImpl implements IServiceInterface {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;


   public final RestTemplate restTemplate = new RestTemplate();

    @Autowired
    ApiConfig apiConfig;

    Logger logger = LoggerFactory.getLogger(ServiceImpl.class);

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

    @Override
    public ConversionDto convertDevise(String deviseSource, String deviseTarget, String amount) {
        if (isNullOrEmpty(deviseSource) || isNullOrEmpty(deviseTarget) || isNullOrEmpty(amount))
            throw new IllegalArgumentException("Bad parameters format");
        System.out.println("Key : "+apiConfig.toString());
        ResponseEntity<ConversionDto> o = null;
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("apikey", apiConfig.getKey());
            httpHeaders. add("user-agent", "Application");
            Map<String,String> params = new HashMap<>();
            params.put("from",deviseSource);
            params.put("to",deviseTarget);
            params.put("amount",amount);
            HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
            logger.info("Params : "+ params);
            logger.info("Headers : "+entity.getHeaders());
            o = restTemplate.exchange(apiConfig.getConvert(), HttpMethod.GET, entity, ConversionDto.class,params);
        }catch (Exception  e ){
            System.out.println(e);
        }

        return o.getBody();
    }

}
