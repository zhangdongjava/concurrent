package com.study.p20170506.z4;

import java.util.Vector;

/**
 * Created by zd on 2017/5/6.
 */
public class _1BetterVector<E> extends Vector<E> {

    /**
     * 如果不存在则添加
     * 保证与vector其他方法同一个锁同步
     * @param e
     * @return
     */
    public synchronized boolean addIfAbsent(E e){
        boolean f = !contains(e);
        if(f){
            add(e);
        }
        return f;
    }
}
