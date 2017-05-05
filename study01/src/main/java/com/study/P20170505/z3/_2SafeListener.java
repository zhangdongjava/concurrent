package com.study.P20170505.z3;

import java.awt.*;

/**
 * 安全够着
 * Created by zd on 2017/5/5.
 */
public class _2SafeListener {

    private EventListener eventListener;

    private _2SafeListener() {

        eventListener = e -> doSamething(e);
    }

    public static _2SafeListener newInstance(EventSource source) {
        _2SafeListener safeListener = new _2SafeListener();
        source.registerListener(safeListener.eventListener);
        return safeListener;
    }

    public void doSamething(Event e) {

    }

    public static class EventSource {

        public void registerListener(EventListener listener) {

        }
    }

    public interface EventListener {
        void onEvent(Event e);
    }


}
