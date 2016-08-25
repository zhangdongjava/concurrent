package com.zzz.robot.util;

import com.zzz.robot.util.impl.BqgZjParse;
import com.zzz.robot.util.sup.ContentParse;
import com.zzz.robot.util.sup.ZjParse;
import org.jsoup.nodes.Element;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class ParseUtil {
    private static Map<String,ZjParse> map = new HashMap<>();
    private static Map<String,ContentParse> contentMap = new HashMap<>();

    static{
        map.put("http://www.biquge66.com",new BqgZjParse());
        map.put("http://www.bxwx.cc",new DocumentUtil());
        contentMap.put("http://www.bxwx.cc",new ZjgetUtil());
        contentMap.put("http://www.biquge66.com",new BqgContentParse());
    }

    /**
     * 根据地址获取对应解析器
     * @param url
     * @return
     */
   public static ZjParse getZjparse(String url){
       for (Map.Entry<String, ZjParse> entry : map.entrySet()) {
           if(url.contains(entry.getKey())){
               return entry.getValue();
           }
       }
        return null;
   }

    /**
     * 根据地址获取对应解析器
     * @param url
     * @return
     */
    public static ContentParse getContentparse(String url){
        for (Map.Entry<String, ContentParse> entry : contentMap.entrySet()) {
            if(url.contains(entry.getKey())){
                return entry.getValue();
            }
        }
        return null;
    }

    public static List<Element> getZjList(String url) {
       return  getZjparse(url).getZjList(url);
    }

    public static String getContent(String url) throws Exception {
        ContentParse contentParse =  getContentparse(url);
        return contentParse.getContent(url);
    }
}













