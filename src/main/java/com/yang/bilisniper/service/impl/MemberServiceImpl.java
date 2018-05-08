package com.yang.bilisniper.service.impl;

import com.yang.bilisniper.dao.MemberReposity;
import com.yang.bilisniper.entity.Member;
import com.yang.bilisniper.responseModel.MemberRes;
import com.yang.bilisniper.service.ApiService;
import com.yang.bilisniper.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Service
public class MemberServiceImpl implements MemberService {

    private static final Logger logger = LoggerFactory.getLogger(MemberServiceImpl.class);

    @Autowired
    private ApiService apiService;

    @Autowired
    private MemberReposity memberReposity;

    @Override
    public boolean getMemberInfo(String url, Long mid) throws Exception {
        boolean flag = false;

        MultiValueMap<String, Object> paramMap = new LinkedMultiValueMap<>();
        paramMap.add("mid", mid + "");
        Map<String, String> headerParam = new HashMap<>();
        headerParam.put("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
        headerParam.put("Host", "space.bilibili.com");
        headerParam.put("Accept", "application/json, text/plain, */*");
        headerParam.put("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
        headerParam.put("X-Requested-With", "XMLHttpRequest");
        headerParam.put("Referer", "http://space.bilibili.com/10513807/");

        MemberRes memberRes = apiService.reqByPost(url, MediaType.APPLICATION_FORM_URLENCODED, headerParam, paramMap, MemberRes.class);
        if (memberRes.getData() != null) {
            Member member = memberRes.getData();
            logger.warn(member.toString());
            memberReposity.save(member);
            flag = true;
        }

        return flag;
    }

    @Override
    public void saveMemberInfo(Long mid) {

    }
}
