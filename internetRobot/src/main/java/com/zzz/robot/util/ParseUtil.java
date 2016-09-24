package com.zzz.robot.util;

import com.zzz.robot.util.impl.BqgContentParse;
import com.zzz.robot.util.impl.BqgZjParse;
import com.zzz.robot.util.impl.DocumentUtil;
import com.zzz.robot.util.impl.ZjgetUtil;
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
        return new DocumentUtil();
   }

    /**
     * 根据地址获取对应解析器
     * @param url
     * @return
     */
    public static ContentParse getContentparse(String url){
        return new BqgContentParse();
    }

    public static List<Element> getZjList(String url) {
       return  getZjparse(url).getZjList(url);
    }

    public static String getContent(String url) throws Exception {
        ContentParse contentParse =  getContentparse(url);
        return contentParse.getContent(url);
    }
}













