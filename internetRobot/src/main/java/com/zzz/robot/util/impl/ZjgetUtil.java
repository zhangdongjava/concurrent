package com.zzz.robot.util.impl;

import com.zzz.robot.util.impl.BaseContentParse;
import com.zzz.robot.util.sup.ContentParse;
import com.zzz.robot.util.sup.ZjParse;
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
 * 新笔下文学章节获取工具
 * Created by dell_2 on 2016/8/24.
 */
public class ZjgetUtil extends BaseContentParse implements ContentParse {

    @Override
    public String getContentStr(Document doc) {
        return doc.getElementsByClass("novel_content").get(0).html();
    }
}






















