package com.yang.bilisniper.service.impl;

import com.yang.bilisniper.dao.ProxyReposity;
import com.yang.bilisniper.entity.ProxyAddr;
import com.yang.bilisniper.service.ProxyService;
import javaslang.Tuple;
import javaslang.Tuple2;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * Created by ChunqiYang (yangchunqi@guazi.com)
 */
@Service
public class ProxyServiceImpl implements ProxyService {

    @Autowired
    private ProxyReposity proxyReposity;

    @Override
    public ProxyAddr getRandomProxy() {
        List<ProxyAddr> enabledList = proxyReposity.findByEnabled(true);
        if (!CollectionUtils.isEmpty(enabledList)) {
            Random random = new Random();
            int index = Math.abs(random.nextInt()) % enabledList.size();
            return enabledList.get(index);
        }
        return null;
    }

    @Override
    public List<ProxyAddr> grabData5uProxy() throws IOException {
        String url = "http://www.data5u.com/free/country/%s/index.html";
        List<Tuple2<String, Integer>> proxyList = Jsoup.parse(new URL(String.format(url, urlEncode("中国"))), 3000)
                .select("#areaDist ul.bigr span")
                .stream()
                .map(elt -> elt.attr("title"))
                .flatMap(countryName -> parseData5u(String.format(url, urlEncode(countryName))).stream())
                .distinct()
                .collect(toList());
        List<ProxyAddr> proxyAddrs = new ArrayList<>();
        proxyList.forEach(t -> proxyAddrs.add(new ProxyAddr(t._1, t._2 + "")));
        return proxyAddrs;
    }

    private int decodeData5uPort(String rawContent) {
        String rawNum = Stream.of(rawContent.split(""))
                .map("ABCDEFGHIZ"::indexOf)
                .map(Object::toString)
                .collect(Collectors.joining());
        return Integer.parseInt(rawNum) >> 3;
    }

    private List<Tuple2<String, Integer>> parseData5u(String url) {
        try {
            Document document = Jsoup.parse(new URL(url), 3000);
            return document.select(".wlist ul li[style=text-align:center;] ul.l2")
                    .stream()
                    .map(elt -> {
                        String ip = elt.select("span").first().text();
                        Elements portElt = elt.select(".port");
                        if (!portElt.isEmpty() && !portElt.html().contains("*")) {
                            String[] ss = portElt.attr("class").split("\\s+");
                            if (ss.length >= 2) {
                                return Tuple.of(ip, decodeData5uPort(ss[1]));
                            }
                        }
                        return null;
                    })
                    .filter(Objects::nonNull)
                    .collect(toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    private String urlEncode(String raw) {
        try {
            return URLEncoder.encode(raw, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }
}
