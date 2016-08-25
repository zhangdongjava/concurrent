package com.zzz.robot.util;

import com.zzz.robot.util.impl.DocumentUtil;
import com.zzz.robot.util.impl.ZjgetUtil;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.nodes.Element;

import java.io.*;
import java.net.SocketTimeoutException;

/**
 * Created by dell_2 on 2016/8/23.
 */
public class Request {


    private static ZjgetUtil zjgetUtil = new ZjgetUtil();

    public Request() {
    }

    /**
     * get 请求
     *
     * @param href
     * @return
     */
    public String get(String href) {
        StringBuilder builder = new StringBuilder();
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpGet httpGet = new HttpGet(href);

        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                builder.append(EntityUtils.toString(entity, "utf-8"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }



    private static void pritlnNew(DocumentUtil util){
        util.getAs().subList(util.getAs().size()-5,util.getAs().size()).forEach(System.out::println);
    }

    private static void save(DocumentUtil util){
        util.getAs().stream().forEach((e) -> {
            downZj(e, 0);
        });
    }


    private static void downZj(Element e, int count) {
        String dir = "d:/xs/dzz";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        String name = dir + "/" + e.text() + ".txt";
        try {
            FileOutputStream fos = new FileOutputStream(name);
            zjgetUtil.save(e.attr("href"), fos);
            fos.close();

        } catch (SocketTimeoutException e1) {
            if (count < 5) {
                System.out.println("保存文件" + name + "失败!进行第" + (count + 1) + "次重试!" + e1.toString());
                downZj(e, count + 1);
            } else {
                System.out.println(name + "重试5次还是失败！无法下载!" + e1.toString());
            }

        } catch (Exception e1) {
            System.out.println("保存文件" + name + "失败!" + e1.toString());
        }
        System.out.println(name + " : 保存成功!");
    }


}
