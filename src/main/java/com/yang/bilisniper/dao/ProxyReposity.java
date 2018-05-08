package com.yang.bilisniper.dao;

import com.yang.bilisniper.entity.ProxyAddr;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public interface ProxyReposity extends JpaRepository<ProxyAddr, Long> {

    List<ProxyAddr> findByEnabled(boolean enabled);
}
