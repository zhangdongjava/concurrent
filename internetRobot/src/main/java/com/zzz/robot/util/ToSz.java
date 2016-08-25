package com.zzz.robot.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by dell_2 on 2016/8/25.
 */
public class ToSz {

    static Map<Character, Integer> map1 = new HashMap<>();
    static Map<Character, Integer> map2 = new HashMap<>();

    static {
        map1.put('一', 1);
        map1.put('两', 2);
        map1.put('二', 2);
        map1.put('三', 3);
        map1.put('四', 4);
        map1.put('五', 5);
        map1.put('六', 6);
        map1.put('七', 7);
        map1.put('八', 8);
        map1.put('九', 9);
        map2.put('十', 10);
        map2.put('百', 100);
        map2.put('千', 1000);
    }

    public static int toSz(String s) {
        char[] c = s.toCharArray();
        if(c.length==2&&c[0]=='十')
            return 10+map1.get(c[1]);
        int sum = 0;
        int prev = 0;
        for (char c1 : c) {
            Integer i1 = map1.get(c1);
            Integer i2 = map2.get(c1);
            if(i1==null&&i2 !=null){
                sum += prev * i2;
                prev = 0;
            }else if(i1 != null){
                prev = i1;
            }
        }
        return sum+prev;
    }

    public static void main(String[] args) {
        System.out.println(toSz("十一"));
    }
}
