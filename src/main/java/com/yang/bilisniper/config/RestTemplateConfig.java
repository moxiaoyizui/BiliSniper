package com.yang.bilisniper.config;

import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import com.yang.bilisniper.entity.ProxyAddr;
import com.yang.bilisniper.service.ProxyService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.SocketAddress;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Configuration
@ComponentScan("com.yang.bilisniper.service.impl")
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(ClientHttpRequestFactory factory) {
        RestTemplate restTemplate = new RestTemplate(factory);
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        return restTemplate;
    }

    @Bean
    public ClientHttpRequestFactory simpleClientHttpRequestFactory() {
        return buildRequestFactory(false);
    }

    @Resource(name = "proxyServiceImpl")
    private ProxyService proxyService;

    private ClientHttpRequestFactory buildRequestFactory(boolean isProxy) {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setReadTimeout(5000);
        factory.setConnectTimeout(15000);

        if (isProxy) {
            ProxyAddr proxyAddr = proxyService.getRandomProxy();
            SocketAddress address = new InetSocketAddress(proxyAddr.getIpAddr(), Integer.valueOf(proxyAddr.getIpPort()));
            Proxy proxy = new Proxy(Proxy.Type.HTTP, address);
            factory.setProxy(proxy);
        }

        return factory;
    }

    public RestTemplate buildProxyRestTemplate() {
        RestTemplate restTemplate = new RestTemplate(buildRequestFactory(true));
        restTemplate.getMessageConverters().add(new FastJsonHttpMessageConverter());
        return restTemplate;
    }
}
