package com.study.P20170505.z3;

import java.util.HashSet;
import java.util.Set;

/**
 * 4.2章实例封闭
 * Created by zd on 2017/5/5.
 */
public class _6PersonSet {

    private final Set<String> mySet = new HashSet<>();

    public synchronized void addPersion(String name) {
        mySet.add(name);
    }

    public synchronized boolean containsPersion(String name) {

        return mySet.contains(name);
    }
}
