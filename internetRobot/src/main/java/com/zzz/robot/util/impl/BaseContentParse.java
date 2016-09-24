package com.zzz.robot.util.impl;

import com.zzz.robot.util.sup.ContentParse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by dell_2 on 2016/8/25.
 */
public abstract class BaseContentParse implements ContentParse {
    public  int  LINE_LENGTH = 1000;
    private static int  TIME_OUT = 10000;
    private static Map<String,String> map = new ConcurrentHashMap<>();

    public  String  getContent(String url) throws Exception {
        String context = map.get(url);
        if(context!=null){
            return context;
        }
        Document doc = Jsoup.parse(new URL(url),TIME_OUT);
        context = getContentStr(doc);
        context = bulidContent(context,null);
        map.put(url,context);
        return context;
    }


    public abstract String getContentStr(Document doc);




    public  void  save(String url, FileOutputStream fos) throws Exception {
        Document doc = Jsoup.parse(new URL(url),TIME_OUT);
        String context = doc.getElementsByClass("novel_content").get(0).html();
        context = bulidContent(context,fos);
        map.put(url,context);
    }

    protected   String bulidContent(String context, FileOutputStream fos){
        StringBuilder builder = new StringBuilder();
        StringReader stringReader = new StringReader(context);
        BufferedReader reader = new BufferedReader(stringReader);
        String line;
        try {
            while((line = reader.readLine())!=null){
                line = line.replaceAll("&nbsp;"," ").replaceAll("<br>","");
                line = lineAddEnter(line);
                if(fos!=null){
                    outFile(line,fos);
                }
                builder.append(line);
                builder.append("\r\n");
            }
        }catch ( Exception e){
            System.out.println("章节转换失败!");
        }

        return builder.toString();
    }

    private   void outFile(String context, FileOutputStream fos) throws IOException {
        fos.write(context.getBytes());
        fos.write("\r\n".getBytes());
    }

    private  String lineAddEnter(String line){
        StringBuilder b = new StringBuilder();
        char[] cs = line.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            b.append(c);
            if(i%LINE_LENGTH==0){
                b.append("\r\n");
            }
        }
        return b.toString();
    }

}
