package com.yang.bilisniper.service.impl;

import com.alibaba.fastjson.JSON;
import com.yang.bilisniper.entity.Member;
import com.yang.bilisniper.service.ApiService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(ApiServiceImplTest.class);

    private String url;

    @Autowired
    private ApiService apiService;

    @Before
    public void setUp() throws Exception {
        url = "http://space.bilibili.com/ajax/member/GetInfo";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void reqByPost() {
        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("mid", "111999199");

        Map<String, String> headerParam = new HashMap<>();
        headerParam.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        headerParam.put("Host", "space.bilibili.com");
        headerParam.put("Accept", "application/json, text/plain, */*");
        headerParam.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        headerParam.put("X-Requested-With", "XMLHttpRequest");
        headerParam.put("Referer", "http://space.bilibili.com/10513807/");

        String res = apiService.reqByPost(url, MediaType.APPLICATION_FORM_URLENCODED, headerParam, paramMap, String.class);

        logger.warn(res);

        Member member = JSON.parseObject(JSON.parseObject(res).get("data").toString(), Member.class);

        System.out.println(res);

        logger.warn("爬取用户信息结果：" + member.toString());
    }
}