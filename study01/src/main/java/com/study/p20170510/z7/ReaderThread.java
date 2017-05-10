package com.study.p20170510.z7;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * Created by zd on 2017/5/10.
 */
public class ReaderThread extends Thread {

    private final Socket socket;
    private final InputStream in;

    public ReaderThread(Socket socket) throws IOException {
        this.socket = socket;
        in = socket.getInputStream();
    }

    @Override
    public void interrupt() {
        try {
            socket.close();
        } catch (IOException e) {

        } finally {
            super.interrupt();
        }
    }

    @Override
    public void run() {
        try {
            byte[] buf = new byte[1024];
            while (true) {
                int len = in.read(buf);
                if (len < 0) break;
                else if (len > 0)
                    System.out.println(new String(buf, 0, len));
            }
        } catch (IOException e) {
            System.out.println("结束读取数据!");
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        ServerSocket serverSocket = new ServerSocket(8888);
        Socket socket = serverSocket.accept();
        System.out.println("收到链接-->" + socket);
        ReaderThread thread = new ReaderThread(socket);
        InterruptDemo2.timeRun(thread, TimeUnit.SECONDS, 20);
    }
}
