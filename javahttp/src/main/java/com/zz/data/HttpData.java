package com.zz.data;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by dell_2 on 2016/8/24.
 */
public class HttpData {

    private static  HttpData httpData = new HttpData();

    public static  HttpClient client = HttpClients.createDefault();

    private static  String token;

    public static HttpData getHttpData(){
        return httpData;
    }

    String  byGet(String url){
        HttpGet httpget = new HttpGet(url);
        HttpResponse response ;
        try {
            response = client.execute(httpget);
            response.addHeader("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            response.addHeader("Accept-Encoding","gzip, deflate, sdch, br");
            response.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.106 Safari/537.36");
            response.addHeader("Upgrade-Insecure-Requests","1");
            response.addHeader("Cookie","BAIDUID=9C2097F8DDF47D8E83777CE3C4CA2722:FG=1");
            response.addHeader("Connection","keep-alive");
            response.addHeader("Connection","keep-alive");
            response.addHeader("Accept-Language","zh-CN,zh;q=0.8,en;q=0.6");
            return EntityUtils.toString(response.getEntity());
        }catch ( Exception e){

        }
        return null;
    }

    public String  byPost(String url,List<NameValuePair> params) throws IOException {

        HttpPost httpPost = new HttpPost(url);
        try {
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params,"utf-8");
            httpPost.setEntity(entity);
            HttpResponse res = client.execute(httpPost);
            return EntityUtils.toString(res.getEntity());
        } catch (Exception e) {
           throw  e;
        }
    }

    public String getBaiduToken() {
        if(token!=null)return token;
        getBaiduCookie();
        String str = "http://passport.baidu.com/v2/api/?getapi&class=login&tpl=pp&tangram=false";
        HttpGet httpget = new HttpGet(str);
        HttpResponse response = null;
        try {
            response = client.execute(httpget);
            String content = EntityUtils.toString(response.getEntity());
            BufferedReader reader = new BufferedReader(new StringReader(content));
            String line = reader.readLine();
            while(line != null){
                if(line.contains("bdPass.api.params.login_token")){
                    int sIndex = line.indexOf("'");
                    int eIndex = line.lastIndexOf("'");
                    return token = line.substring(sIndex+1,eIndex);
                }
                line = reader.readLine();
            }

        } catch (Exception e) {

        }
        return null;
    }

    public CookieStore getBaiduCookie() {
        HttpGet httpGet = new HttpGet("http://tieba.baidu.com/f?kw=java&ie=utf-8&tab=good&cid=&pn=50");
        try {
            HttpResponse res = client.execute(httpGet);
        } catch (IOException e) {
            System.out.println("获取cookie错误" + e.toString());
        }
        return null;
    }


    public void loginBaidu(){
        String u = "https://passport.baidu.com/v2/api/?login";
        List<NameValuePair> params = new ArrayList<>();
        long tt = Calendar.getInstance().getTime().getTime();
        try {
            params.add(new BasicNameValuePair("charset","utf-8"));
            params.add(new BasicNameValuePair("tpl","mn"));
            params.add(new BasicNameValuePair("apiver","v3"));
            params.add(new BasicNameValuePair("tt",String.valueOf(tt)));
            params.add(new BasicNameValuePair("safeflg","0"));
            params.add(new BasicNameValuePair("isPhone","false"));
            params.add(new BasicNameValuePair("quick_user","0"));
            params.add(new BasicNameValuePair("logintype","dialogLogin"));
            params.add(new BasicNameValuePair("rsakey","Jp8dttpWj6TiheTyqFQnoRN0txjNlBuP"));
            params.add(new BasicNameValuePair("token",this.getBaiduToken()));
            params.add(new BasicNameValuePair("username","18423418323"));
            params.add(new BasicNameValuePair("password","bdy315441573"));
            params.add(new BasicNameValuePair("mem_pass","on"));
            params.add(new BasicNameValuePair("logLoginType","pc_loginDialog"));
            params.add(new BasicNameValuePair("verifycode",""));
            params.add(new BasicNameValuePair("callback","parent.bd_pcbs_axjnsn"));

            System.out.println( byPost(u,params));
        } catch (IOException e) {
            System.out.println("登录百度失败！"+e.toString());
        }
    }

    public static void main(String[] args) {
        System.out.println(Calendar.getInstance().getTime().getTime());
        HttpData httpData =  new HttpData();
        httpData.loginBaidu();
        String content = httpData.byGet("http://localhost/fish/login/userLogin");
        System.out.println(content);
       if(content.contains("马上登录")){
           System.out.println("登录失败！");
       }
    }

}























