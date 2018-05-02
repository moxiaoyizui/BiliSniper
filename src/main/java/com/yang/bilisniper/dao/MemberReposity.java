package com.yang.bilisniper.dao;

import com.yang.bilisniper.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public interface MemberReposity extends JpaRepository<Member, Long> {
}
