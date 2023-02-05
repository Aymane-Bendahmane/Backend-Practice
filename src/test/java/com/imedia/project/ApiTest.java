package com.imedia.project;

import com.imedia.project.dto.ConversionDto;
import com.imedia.project.services.ServiceImpl;
import com.imedia.project.utilities.ApiConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDate;

@ExtendWith(MockitoExtension.class)
public class ApiTest {
    @InjectMocks
    ServiceImpl service;
    @Mock
    private RestTemplate restTemplate;

    @Mock
    private ApiConfig apiConfig ;

    /*@Test
    void testApi(){
        HttpHeaders httpHeaders = new HttpHeaders();
        ConversionDto conversionDto = new ConversionDto();
        conversionDto.builder().success(true).date(LocalDate.now()).result(10.2);
        httpHeaders.add("apikey",apiConfig.getKey());
        HttpEntity<String> entity =  new HttpEntity<>(httpHeaders);
        Mockito.when(restTemplate.exchange(apiConfig.getConvert(), HttpMethod.GET, entity, ConversionDto.class)).thenReturn(new ResponseEntity<>(conversionDto, HttpStatus.OK));
        ConversionDto conversionDto1 = service.convertDevise("ze","mad","10");
        Assertions.assertTrue(conversionDto1.success);
    }*/
}
