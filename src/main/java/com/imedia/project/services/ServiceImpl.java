package com.imedia.project.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ConversionDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.entites.Category;
import com.imedia.project.entites.Product;
import com.imedia.project.exceptions.MyException;
import com.imedia.project.mappers.CategoryMapper;
import com.imedia.project.mappers.ProductMapper;
import com.imedia.project.repositories.CategoryRepository;
import com.imedia.project.repositories.ProductRepository;
import com.imedia.project.utilities.HttpRequestsExecutor;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static com.imedia.project.utilities.Tools.*;

@Service
@AllArgsConstructor
public class ServiceImpl implements IServiceInterface {
    private final ProductRepository productRepository;

    private final CategoryRepository categoryRepository;

    private final HttpRequestsExecutor httpRequestsExecutor;

    ObjectMapper objectMapper;

    @Override
    public List<ProductDto> productsList() {
        return ProductMapper.INSTANCE.DTO_LIST(productRepository.findAll());
    }

    @Override
    public List<ProductDto> productsListByCategory(String name) {
        Category category = getCategoryByName(name);
        return ProductMapper.INSTANCE.DTO_LIST(productRepository.findProductsByCategory(category));
    }

    @Override
    public List<CategoryDto> categoriesList() {
        return CategoryMapper.INSTANCE.DTO_LIST(categoryRepository.findAll());
    }

    @Override
    public ProductDto getProductById(Long id) {
        return ProductMapper.INSTANCE.productToDto(productRepository.getReferenceById(id));
    }

    @Override
    public CategoryDto getCategoryById(Long id) {
        return CategoryMapper.INSTANCE.categoryToDto(categoryRepository.getReferenceById(id));
    }

    @Override
    public Category getCategoryByName(String name) {
        if (isNullOrEmpty(name)) throw new IllegalArgumentException(ERROR_MESSAGE);
        return categoryRepository.findCategoryByName(name);
    }

    @Override
    @Transactional
    public ProductDto createProduct(ProductDto productDto) throws MyException {
        Category category = categoryRepository.findCategoryByName(productDto.getCategory().getName());
        if (category == null) throw new MyException(ERROR_MESSAGE);
        Product product = ProductMapper.INSTANCE.dtoToProduct(productDto);
        product.setCategory(category);
        return ProductMapper.INSTANCE.productToDto(productRepository.save(product));
    }

    @Override
    @Transactional
    public CategoryDto createCategory(CategoryDto categoryDto) throws MyException {
        Category category = CategoryMapper.INSTANCE.dtoToCategory(categoryDto);
        if (category == null) throw new MyException(ERROR_MESSAGE);
        return CategoryMapper.INSTANCE.categoryToDto(categoryRepository.save(category));
    }

    /**
     * This method convert a given amount from a currency to a target one using external api
     *
     * @param deviseSource
     * @param deviseTarget
     * @param amount
     * @return ConversionDto
     * @throws Exception
     * @Author Aymane BENDAHMANE
     */
    @Override
    public ConversionDto convertDevise(String deviseSource, String deviseTarget, String amount) throws MyException {
        if (isNullOrEmpty(deviseSource) || isNullOrEmpty(deviseTarget) || isNullOrEmpty(amount))
            throw new MyException(BAD_PARAMETER, HttpStatus.BAD_REQUEST);
        ConversionDto conversionDto;
        try {
            Map<String, String> params = new HashMap<>();
            params.put(FROM, deviseSource);
            params.put(TO, deviseTarget);
            params.put(AMOUNT, amount);
            conversionDto = objectMapper.convertValue(httpRequestsExecutor.executeGet(params), ConversionDto.class);
        } catch (IllegalArgumentException e) {
            throw new MyException(ERROR_CASTING_OBJECTS, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (RestClientException e) {
            throw new MyException(ERROR_EXTERNAL_API_CALL, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            throw new MyException(INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return conversionDto;
    }

    @Override
    @Transactional
    @PostConstruct
    public void initData() {
        System.out.println("init data ");
        Category electronic = Category.builder().name("electronic").build();
        Category gaming = Category.builder().name("gaming").build();
        Category food = Category.builder().name("food").build();
        Category sport = Category.builder().name("sport").build();

        categoryRepository.save(electronic);
        categoryRepository.save(gaming);
        categoryRepository.save(food);
        categoryRepository.save(sport);

        List<Product> products = IntStream.range(0, 10).mapToObj(i -> Product.builder().name("DELL-".concat(String.valueOf(i))).price(100.0).category(electronic).build()).collect(Collectors.toList());
        List<Product> products1 = IntStream.range(0, 10).mapToObj(i -> Product.builder().name("Accessories-".concat(String.valueOf(i))).price(90.0).category(gaming).build()).collect(Collectors.toList());
        List<Product> products2 = IntStream.range(0, 10).mapToObj(i -> Product.builder().name("JunkFood-".concat(String.valueOf(i))).price(80.0).category(food).build()).collect(Collectors.toList());
        List<Product> products3 = IntStream.range(0, 10).mapToObj(i -> Product.builder().name("Equipment-".concat(String.valueOf(i))).price(60.0).category(sport).build()).collect(Collectors.toList());

        productRepository.saveAll(products);
        productRepository.saveAll(products1);
        productRepository.saveAll(products2);
        productRepository.saveAll(products3);
    }

}
