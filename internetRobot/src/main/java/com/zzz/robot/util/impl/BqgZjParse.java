package com.zzz.robot.util.impl;

import com.zzz.robot.util.sup.ZjParse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 笔趣阁章节解析器
 * Created by dell_2 on 2016/8/25.
 */
public class BqgZjParse implements ZjParse {

    private String baseUrl = "http://www.biquge66.com";

    private Elements as;
    private int linkCount = 0;
    private Document document;

    @Override
    public List<Element> getZjList(String url) {
        if(linkCount>5)return null;
        linkCount++;
        try {
            document = Jsoup.parse(new URL(url),2000);
            bulidAs();
            return as;
        } catch (IOException e) {
            getZjList(url);
            e.printStackTrace();
        }
        return null;
    }

    public void bulidAs(){
        Element list = document.getElementById("list");
        as = list.getElementsByTag("a");
         as.stream().forEach((e)->e.attr("href",baseUrl+e.attr("href")));


    }

}
























