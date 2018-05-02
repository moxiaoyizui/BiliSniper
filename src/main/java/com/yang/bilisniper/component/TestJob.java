package com.yang.bilisniper.component;

import com.yang.bilisniper.dao.MemberReposity;
import com.yang.bilisniper.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Component
public class TestJob {

    @Autowired
    private MemberReposity memberReposity;

    @Scheduled(cron = "0/10 * * * * ?")
    public void testJob() {
        System.out.println("start insert");

        Member member1 = new Member();
        Member member2 = new Member();
        member1.setMid(1L);
        member2.setMid(2L);


        memberReposity.save(member1);
        memberReposity.save(member2);

        System.out.println("end insert");
    }
}
