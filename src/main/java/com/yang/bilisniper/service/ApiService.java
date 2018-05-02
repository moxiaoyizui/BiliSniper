package com.yang.bilisniper.service;

import org.springframework.http.MediaType;
import org.springframework.util.MultiValueMap;

import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public interface ApiService {
    <T> T reqByPost(@NotNull final String url, MediaType contentType, final Map<String, String> headerParams, final MultiValueMap<String, Object> paramMap,
                    Class<T> entityClass);
}
