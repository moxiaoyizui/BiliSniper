package com.yang.bilisniper.component;

import com.yang.bilisniper.constant.RedisConstants;
import com.yang.bilisniper.service.MemberService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Component
public class GetInfoCreateJob {

    private static final Logger logger = LoggerFactory.getLogger(GetInfoCreateJob.class);

    private static final String URL_GETINFO = "https://space.bilibili.com/ajax/member/GetInfo";

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate redisTemplate;

    @Autowired
    private MemberService memberService;

    @Scheduled(cron = "0/5 * * * * ?")
    public void createUrl() {
        Long mid = Long.valueOf((String) redisTemplate.opsForValue().get(RedisConstants.KEY_LASTMID));
        if (mid == null) {
            redisTemplate.opsForValue().set(RedisConstants.KEY_LASTMID, 0);
        }

        ExecutorService getInfoThreadPool = Executors.newFixedThreadPool(1);
        while (true) {
            mid = Long.valueOf((String) redisTemplate.opsForValue().get(RedisConstants.KEY_LASTMID)) + 1;
            getInfoThreadPool.submit(new GrabMemberThread(mid));
            redisTemplate.opsForValue().append(RedisConstants.KEY_LASTMID, mid + "");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class GrabMemberThread extends Thread {
        private Long mid;

        public GrabMemberThread(Long mid) {
            this.mid = mid;
        }

        public void run() {
            try {
                if (memberService.getMemberInfo(URL_GETINFO, mid)) {
                    return;
                }
            } catch (Exception e) {
                logger.error("采集成员" + mid + "信息异常", e);
            }
            redisTemplate.opsForSet().add(RedisConstants.SET_FAILMID, mid);
        }
    }
}
