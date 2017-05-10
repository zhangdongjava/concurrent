package com.study.p20170510.z7;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd on 2017/5/10.
 */
public class TestSocket {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 8888);
        OutputStream out = socket.getOutputStream();
        long curr = System.currentTimeMillis();
        while (true) {

            long l = System.currentTimeMillis() - curr;
            try {
                TimeUnit.SECONDS.sleep(1);
                out.write(Long.toString(l).getBytes());
            } catch (Exception e) {
                break;
            }
        }
    }
}
