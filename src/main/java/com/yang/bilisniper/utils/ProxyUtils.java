package com.yang.bilisniper.utils;

import com.yang.bilisniper.entity.ProxyAddr;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
public class ProxyUtils {

    public static final boolean checkHostEnabled(ProxyAddr proxyAddr) {
        Socket socket = new Socket();
        boolean flag = false;
        try {
            socket.connect(new InetSocketAddress(proxyAddr.getIpAddr(), Integer.valueOf(proxyAddr.getIpPort())), 2000);
            socket.setSoTimeout(2000);
            flag = true;
        } catch (IOException e) {
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return flag;
    }
}
