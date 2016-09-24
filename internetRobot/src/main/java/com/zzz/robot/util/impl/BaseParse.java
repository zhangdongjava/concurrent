package com.zzz.robot.util.impl;

import com.zzz.robot.util.ToSz;
import com.zzz.robot.util.sup.ZjParse;
import org.jsoup.nodes.Element;

import java.util.*;

/**
 * Created by dell_2 on 2016/8/25.
 */
public abstract class BaseParse implements ZjParse {

    public static void listDistinct(List<Element> elements){
        Set<String> set = new HashSet<>();
        Iterator<Element> it = elements.iterator();
        while(it.hasNext()){
            Element e = it.next();
            String href = e.attr("href");
            if(set.contains(href)){
                it.remove();
            }else{
                set.add(href);
            }
        }
        set.clear();
        Collections.sort(elements,new MyShor());
    }

    private static class MyShor implements  Comparator<Element>{

        @Override
        public int compare(Element o1, Element o2) {
            String text1 = o1.text();
            int textIndex = text1.indexOf("第")+1;
            String text2 = o2.text();
            int text2Index = text1.indexOf("第")+1;
            int r = 0;
            try {
                int i1 = Integer.valueOf(text1.substring(textIndex,text1.indexOf("章")));
                int i2 = Integer.valueOf(text2.substring(text2Index,text2.indexOf("章")));
                r = i1 - i2;
            }catch (NumberFormatException e){
                r= ToSz.toSz(text1.substring(textIndex,text1.indexOf("章")))-ToSz.toSz(text2.substring(text2Index,text2.indexOf("章")));
            }

            return r;
        }

    }

}
