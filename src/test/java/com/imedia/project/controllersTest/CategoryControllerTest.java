package com.imedia.project.controllersTest;

import com.imedia.project.controllers.CategoryController;
import com.imedia.project.dto.CategoryDto;
import com.imedia.project.services.ServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CategoryController.class)
class CategoryControllerTest {

    static final List<CategoryDto> categoryList = new ArrayList<>();
    @MockBean
    private static ServiceImpl service;
    @SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
    @Autowired
    private MockMvc mockMvc;

    @BeforeAll
    static void initServiceDate() {

        categoryList.add(CategoryDto.builder().name("category1").build());
        categoryList.add(CategoryDto.builder().name("category2").build());
        categoryList.add(CategoryDto.builder().name("category3").build());

    }

    @Test
    void categoriesListTest() throws Exception {
        when(service.categoriesList()).thenReturn(categoryList);
        mockMvc.perform(get("/categories")).
                andExpect(status().isOk()).
                andExpect(jsonPath("$[0].name", is("category1"))).
                andExpect(jsonPath("$[1].name", is("category2"))).
                andExpect(jsonPath("$", hasSize(3)));
    }
}
