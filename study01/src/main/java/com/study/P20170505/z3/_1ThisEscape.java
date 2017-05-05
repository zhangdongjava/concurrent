package com.study.P20170505.z3;

import java.awt.*;
import java.util.EventListener;

/**
 * 不安全构造
 * Created by zd on 2017/5/5.
 */
public class _1ThisEscape {

    public _1ThisEscape(EventSource source) {

        source.registerListener(new EventListener() {
            //该方法可能在其他线程执行 调用 doSamething 将ThisEscape对象逸出到了其他线程
            //而且这是构造方法  如果this引用在构造方法中逸出 ，那么这种现象就被认为是不正确构造
            //(不要在构造方法中使this引用逸出)
            public void onEvent(Event e) {
                doSamething(e);
            }
        });
    }

    public void doSamething(Event e){

    }

    public static class EventSource {

        public void registerListener(EventListener listener) {

        }
    }

    public interface EventListener {
        void onEvent(Event e);
    }


}
