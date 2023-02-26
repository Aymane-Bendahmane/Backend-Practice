package com.imedia.project.controllersTest;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.imedia.project.controllers.ProductController;
import com.imedia.project.dto.CategoryDto;
import com.imedia.project.dto.ProductDto;
import com.imedia.project.services.ServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * to test only the web layer we use @WebMvcTest annotation , if we want to be more specific we can declare the target controller
 * PS : we need to mock the resources that the controller depends on ( for example our Controller class uses a ServiceImpl dependency ) .
 */
@WebMvcTest(ProductController.class)
class ProductControllerTest {

    static final List<ProductDto> productList = new ArrayList<>();
    @MockBean
    private static ServiceImpl service;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private ObjectMapper mapper;

    @BeforeAll
    static void initServiceDate() {

        productList.add(ProductDto.builder().name("product").price(10d).build());
        productList.add(ProductDto.builder().name("product1").price(11d).build());
        productList.add(ProductDto.builder().name("product2").price(12d).build());
        productList.add(ProductDto.builder().name("product3").price(13d).build());

    }

    @Test
    void productsListTest() throws Exception {
        when(service.productsList()).thenReturn(productList);
        mockMvc.perform(get("/products")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name", is("product"))).
                andExpect(jsonPath("$[0].price", is(10d))).
                andExpect(jsonPath("$", hasSize(4)));
    }

    @Test
    void createProductTest() throws Exception {
        ProductDto p = productList.get(0);
        p.setCategory(new CategoryDto("electronic"));
        when(service.createProduct(productList.get(0))).thenReturn(p);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/products/create").
                        contentType(MediaType.APPLICATION_JSON).
                        content(mapper.writeValueAsString(p).getBytes(StandardCharsets.UTF_8))
        ).andExpect(status().isOk());
                /*andExpect(content().contentType("application/json;charset=UTF-8")).
                andExpect(jsonPath("name", is("product"))).
                andExpect(jsonPath("price", is(10d)));
*/
    }

    @Test
    void productsListByCategoryTest() throws Exception {
        when(service.productsListByCategory("product")).thenReturn(Collections.singletonList(productList.get(0)));
        mockMvc.perform(get("/products/name/product")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name", is("product")));
    }

    @Test
    void productByIDTest() throws Exception {
        when(service.getProductById(1L)).thenReturn(productList.get(0));
        mockMvc.perform(get("/products/id/1")).
                andExpect(status().isOk()).
                andExpect(jsonPath("name", is("product"))).
                andExpect(jsonPath("price", is(10d)));
    }
}
