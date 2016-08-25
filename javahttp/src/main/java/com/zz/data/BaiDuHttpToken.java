package com.zz.data;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.StringReader;

/**
 * Created by dell_2 on 2016/8/24.
 */
public class BaiDuHttpToken {

    private HttpData httpData = HttpData.getHttpData();
    private CookieData cookieData = CookieData.getCookieData();

    private static String token;
    private static BaiDuHttpToken baiDuHttpToken = new BaiDuHttpToken();



}
