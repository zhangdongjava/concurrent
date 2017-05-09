package com.study.p20170509.z6;

import com.sun.scenario.effect.ImageData;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 页面渲染还不够好
 * Created by zd on 2017/5/9.
 */
public class PageRenderDemo1 {

    private final ExecutorService executorService = Executors.newFixedThreadPool(2);

    public void renderPage(String source) {
        final List<String> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = () -> {
            List<ImageData> imageDatas = new LinkedList<>();
            for (String imageInfo : imageInfos) {
                ImageData imageData = downLoadImage(imageInfo);
                imageDatas.add(imageData);
            }
            return imageDatas;
        };
        Future<List<ImageData>> future = executorService.submit(task);
        renderText(source);
        try {
            List<ImageData> imageDatas =  future.get();
            for (ImageData imageData : imageDatas) {
                renderImage(imageData);
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            future.cancel(true);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    /**
     * 渲染图片未实现
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
}
