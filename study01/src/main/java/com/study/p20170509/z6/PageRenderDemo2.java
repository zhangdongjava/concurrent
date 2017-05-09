package com.study.p20170509.z6;

import com.sun.scenario.effect.ImageData;

import java.util.List;
import java.util.concurrent.*;

/**
 * 页面渲染
 * Created by zd on 2017/5/9.
 */
public class PageRenderDemo2 {

    private static final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void renderPage(String source) {
        final List<String> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService<>(executorService);
        for (String imageInfo : imageInfos) {
            Callable<ImageData> task = () -> {
                ImageData imageData = downLoadImage(imageInfo);
                return imageData;
            };
            completionService.submit(task);
        }
        renderText(source);
        try {
            for (int i = 0; i < imageInfos.size(); i++) {
                //获取下载完成了的图片
                Future<ImageData> future = completionService.take();
                renderImage(future.get());
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (ExecutionException e) {
            throw new RuntimeException(e.getCause());
        }
    }

    /**
     * 渲染图片未实现
     *
     * @param imageData
     */
    private void renderImage(ImageData imageData) {
    }

    /**
     * 渲染文本 未实现
     *
     * @param source
     */
    private void renderText(String source) {
    }


    /**
     * 未实现
     *
     * @param imageInfo
     * @return
     */
    private ImageData downLoadImage(String imageInfo) {

        return null;
    }

    /**
     * 未实现
     *
     * @param source
     * @return
     */
    private List<String> scanForImageInfo(String source) {

        return null;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runnable r = () -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(111);
        };
        Future<?> f = executorService.submit(r);
        System.out.println(222);
    }
}
