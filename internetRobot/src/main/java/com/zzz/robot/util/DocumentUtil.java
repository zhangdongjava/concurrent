package com.zzz.robot.util;

import com.zzz.robot.util.sup.ZjParse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by dell_2 on 2016/8/23.
 */
public class DocumentUtil implements ZjParse {

    private String url;

    private Document doc;

    private String baseUrl;

    private  Elements as;

    List<Element> list;


    private Consumer consumer;

    public static Request request = new Request();



    public void setUrl(String url) {
        this.url = url;
    }

    public void connect() {
        try {
            doc = Jsoup.parse(new URL(url),2000);
            bulida();
        } catch (IOException e) {
            System.out.println("链接失败!"+e.toString());
        }

    }

    private void bulida(){
        as = doc.getElementsByTag("a");
        list = as.parallelStream()
                .filter((e) -> e.text().contains("章") && e.text().indexOf("第") == 0)
                .collect(Collectors.toList());
        list.parallelStream()
                .filter((e)-> !e.attr("href").startsWith("http"))
                .forEach((e)->e.attr("href",baseUrl+e.attr("href")));
    }

    public List<Element> getAs(){
        return list;
    }

    public void printAs(){
        list.stream().forEach((e)->{
            System.out.print(e.text());
            System.out.println(e.attr("href"));
        });
    }

    public void saveAll(SaveFile saveFile){
        list.subList(1,2).parallelStream().forEach((e)->saveFile.saveFile(e));
    }

    @Override
    public List<Element> getZjList(String url) {
        this.url = url;
        baseUrl = url.substring(0,url.lastIndexOf("/")+1);
        this.connect();
        return list;
    }
}























