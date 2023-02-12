package com.imedia.project.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class HttpRequestsExecutor {

    @Autowired
    ApiConfig apiConfig;

    public final RestTemplate restTemplate = new RestTemplate();

    protected static final HttpHeaders httpHeaders = new HttpHeaders();

    HttpEntity<String> entity;
    Logger logger = LoggerFactory.getLogger(HttpRequestsExecutor.class);

    public Object executeGet(Map<String, String> params) {
        initHeaders();

        Object object = restTemplate.exchange(apiConfig.getConvert(), HttpMethod.GET, entity, Object.class, params).getBody();
        logger.info("Response : {} ",object.toString());
        return object;
    }

    public ResponseEntity<Object> executePost(Object o) {
        initHeaders();
        logger.info("Object  : " + o.toString());
        return restTemplate.postForEntity(apiConfig.getConvert(), o, Object.class);
    }

    public void initHeaders() {
        httpHeaders.add("apikey", apiConfig.getKey());
        httpHeaders.add("user-agent", "Application");
        entity = new HttpEntity<>(httpHeaders);
        logger.info("Headers : " + entity.getHeaders());
    }
}
