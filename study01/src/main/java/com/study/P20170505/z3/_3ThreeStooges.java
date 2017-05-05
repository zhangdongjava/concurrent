package com.study.P20170505.z3;

import java.util.HashSet;
import java.util.Set;

/**
 * 可变对象基础上的不可变类
 * Created by zd on 2017/5/5.
 */
public class _3ThreeStooges {

    private    final  Set<String> stooges = new HashSet<>();

    public _3ThreeStooges(){
        stooges.add("111");
        stooges.add("2434");
        stooges.add("33");
    }

    public boolean isStooge(String stooge){
        return stooges.contains(stooge);
    }
}
