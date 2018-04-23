package com.yang.bilisniper.service.impl;

import com.yang.bilisniper.service.ApiService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Service
public class ApiServiceImpl implements ApiService {

    private final static Logger LOGGER = LoggerFactory.getLogger(ApiServiceImpl.class);

    @Autowired
    private RestTemplate restTemplate;


    @Override
    public <T> T reqByPost(String url, MediaType contentType, Map<String, String> headerParams,
                           MultiValueMap<String, Object> paramMap, Class<T> entityClass) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(contentType);

        if (headerParams.size() > 0) {
            headerParams.forEach((k, v) -> headers.set(k, v));
        }

        final HttpEntity<MultiValueMap<String, Object>> httpEntity = new HttpEntity<>(paramMap, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(url, httpEntity, String.class, paramMap);

        return (T) response.getBody();
    }
}
