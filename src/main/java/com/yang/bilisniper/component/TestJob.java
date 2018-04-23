package com.yang.bilisniper.component;

import org.springframework.stereotype.Component;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Component
public class TestJob {

    //@Scheduled(cron = "0/10 * * * * ?")
    public void testJob() {
        System.out.println("hello world");
    }
}
