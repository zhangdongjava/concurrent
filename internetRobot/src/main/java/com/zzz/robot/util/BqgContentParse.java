package com.zzz.robot.util;

import com.zzz.robot.util.impl.BaseContentParse;
import com.zzz.robot.util.sup.ContentParse;
import com.zzz.robot.util.sup.ZjParse;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;
import java.util.stream.Collectors;

/**
 * Created by dell_2 on 2016/8/23.
 */
public class BqgContentParse extends BaseContentParse {

    @Override
    public String getContentStr(Document doc) {
        return doc.getElementById("content").html();
    }
}























