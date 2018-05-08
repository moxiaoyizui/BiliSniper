package com.yang.bilisniper.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Entity
@Table(name = "proxy_addr")
public class ProxyAddr {

    @Id
    @GeneratedValue
    private Integer id;

    private String ipAddr;

    private String ipPort;

    private boolean enabled;

    public ProxyAddr() {
    }

    public ProxyAddr(String ipAddr, String ipPort) {
        this.ipAddr = ipAddr;
        this.ipPort = ipPort;
    }

    public ProxyAddr(String ipAddr, String ipPort, boolean enabled) {
        this.ipAddr = ipAddr;
        this.ipPort = ipPort;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }

    public String getIpPort() {
        return ipPort;
    }

    public void setIpPort(String ipPort) {
        this.ipPort = ipPort;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    @Override
    public String toString() {
        return "ProxyAddr{" +
                "id=" + id +
                ", ipAddr='" + ipAddr + '\'' +
                ", ipPort='" + ipPort + '\'' +
                ", enabled=" + enabled +
                '}';
    }
}
