package com.yang.bilisniper.service;

import com.yang.bilisniper.entity.ProxyAddr;

import java.io.IOException;
import java.util.List;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public interface ProxyService {

    ProxyAddr getRandomProxy();

    List<ProxyAddr> grabData5uProxy() throws IOException;
}
