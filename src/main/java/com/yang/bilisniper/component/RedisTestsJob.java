package com.yang.bilisniper.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Component
public class RedisTestsJob {

    private static final Logger logger = LoggerFactory.getLogger(RedisTestsJob.class);

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate redisTemplate;

    //@Scheduled(cron = "0/10 * * * * ?")
    public void redisSet() {
        //redisTemplate.opsForList().leftPush("springBootTest", "test");
        //System.out.println(redisTemplate.opsForList().leftPop("springBootTest"));
        //redisTemplate.opsForValue().append("test1", "test");

        Integer mid = (Integer) redisTemplate.opsForValue().get("last");
        System.out.println(mid);
    }
}
