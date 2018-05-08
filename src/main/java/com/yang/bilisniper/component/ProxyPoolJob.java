package com.yang.bilisniper.component;

import com.yang.bilisniper.dao.ProxyReposity;
import com.yang.bilisniper.entity.ProxyAddr;
import com.yang.bilisniper.service.ProxyService;
import com.yang.bilisniper.utils.ProxyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Component
public class ProxyPoolJob {

    private static final Logger logger = LoggerFactory.getLogger(ProxyPoolJob.class);

    @Autowired
    private ProxyReposity proxyReposity;

    @Autowired
    private ProxyService proxyService;

    @Scheduled(cron = "0 0/2 * * * ?")
    public void grabData5uJob() {
        try {
            logger.warn("start grab data 5u proxy");
            List<ProxyAddr> addrs = proxyService.grabData5uProxy();
            addrs.forEach(addr -> {
                if (ProxyUtils.checkHostEnabled(addr)) {
                    addr.setEnabled(true);
                    proxyReposity.save(addr);
                }
            });
            logger.warn("finish grab data 5u proxy, total " + addrs.size());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Scheduled(cron = "0/10 * * * * ?")
    public void checkProxyPool() {
        List<ProxyAddr> addrs = proxyReposity.findByEnabled(true);
        addrs.forEach(addr -> {
            if (!ProxyUtils.checkHostEnabled(addr)) {
                logger.warn("proxy unavailable:" + addr.toString());
                addr.setEnabled(false);
                proxyReposity.save(addr);
            }
        });
    }
}
