package com.soustock.stockquote.crawl.connect;

import com.soustock.stockquote.crawl.connect.HttpRequester;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by xuyufei on 2015/9/19.
 */
public class XueqiuConnector {

    public final static String STR_COOKIE_XUEQIU = "s=2x1h11znh4; bid=9b15a2aa90149bcac4ad19830e783e9d_iirkz8b3; webp=0; __utmt=1; xq_a_token=86043239fe5c660053437856993357e187e20f30; xq_r_token=4d927337ee819ed0f0c7907f023c442f5019d90a; u=4330033544; xq_token_expire=Thu%20Apr%2014%202016%2003%3A26%3A44%20GMT%2B0800%20(CST); xq_is_login=1; Hm_lvt_1db88642e346389874251b5a1eded6e3=1458415601; Hm_lpvt_1db88642e346389874251b5a1eded6e3=1458415628; __utma=1.764053233.1451399966.1455110875.1458415601.15; __utmb=1.4.10.1458415601; __utmc=1; __utmz=1.1455110875.14.7.utmcsr=baidu|utmccn=(organic)|utmcmd=organic";

    /**
     * send a GET request
     *
     * @param urlString
     *            URL String
     * @param params
     *            parameters map
     * @param properties
     *            properties map
     * @return reponsing string
     * @throws IOException
     */
    public static String sendGet(String urlString, Map<String, String> params,
                          Map<String, String> properties) throws IOException {
        HttpRequester httpRequester = new HttpRequester();
        if (properties==null){
            properties = new HashMap<String, String>();
        }
        properties.put("Cookie", STR_COOKIE_XUEQIU);
        return httpRequester.sendGet(urlString, params, properties);
    }
}
