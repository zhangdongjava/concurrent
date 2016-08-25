package com.zzz.robot.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.SocketTimeoutException;

/**
 * Created by dell_2 on 2016/8/23.
 */
public class Request {
    public String cooike = "tvfe_boss_uuid=e0d0f8558553adc6; pac_uid=1_787395919; eas_sid=T1n4w669B5T947o7m0b7v9f6n8; ptui_loginuin=2390779415; p_o2_uin=787395919; ts_refer=ADTAGCLIENT.QQ.5485_.0; RK=gE+LdW83TX; uid=131004559; pt2gguin=o0787395919; uin=o0787395919; skey=@7ou3iuz0Z; ptisp=ctc; ptcz=9a7a83f63dade10656b226de95f995f85de9e7e06a8deddb700f3d8e13cf1489; ad_play_index=39; ptag=aio2015|; o_cookie=787395919; ts_uid=2496443416; pgv_info=ssid=s4115400492; pgv_pvid=4159370366; Hm_lvt_3c8ecbfa472e76b0340d7a701a04197e=1471571592,1471659727,1471830903,1471916808; Hm_lpvt_3c8ecbfa472e76b0340d7a701a04197e=1471941230; dc_vplaying=0";


    private static ZjgetUtil zjgetUtil = new ZjgetUtil();

    public Request(String cooike) {
        this.cooike = cooike;
    }

    public Request() {
    }

    /**
     * get 请求
     *
     * @param href
     * @param params
     * @return
     */
    public String get(String href, String params) {
        StringBuilder builder = new StringBuilder();
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = HttpClients.createDefault();
        // 创建httppost
        HttpGet httpGet = new HttpGet(href);

        try {
            CloseableHttpResponse response = httpclient.execute(httpGet);
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                builder.append(EntityUtils.toString(entity, "gbk"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        DocumentUtil util = new DocumentUtil("http://www.bxwx.cc/60/60915/index.html");
        util.connect();
        pritlnNew(util);
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
