package com.study.p20170506.z5;

import java.io.File;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * Created by zd on 2017/5/8.
 */
public class FileCrawler implements Runnable {

    private final BlockingQueue<File> fileQueue;

    public FileCrawler(){
        fileQueue = new ArrayBlockingQueue<>(10);
    }

    @Override
    public void run() {

    }
}
