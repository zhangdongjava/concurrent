package com.zz.data;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.List;

/**
 * Created by dell_2 on 2016/8/24.
 */
public class CookieData {

    private static CookieData cookieData = new CookieData();

    private CookieStore baiduCookieStore;


    public static CookieData getCookieData() {
        return cookieData;
    }

    public CookieStore getBaiduCookie() {
        if(baiduCookieStore!=null){
            return baiduCookieStore;
        }
        CloseableHttpClient client = new DefaultHttpClient();
        HttpGet httpGet = new HttpGet("http://tieba.baidu.com/f?kw=java&ie=utf-8&tab=good&cid=&pn=50");
        try {
            HttpResponse res = client.execute(httpGet);
            baiduCookieStore = ((AbstractHttpClient) client).getCookieStore();
            return baiduCookieStore;

        } catch (IOException e) {
            System.out.println("获取cookie错误" + e.toString());
        }
        return null;
    }

}
