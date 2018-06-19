package chapter6;

import com.sun.scenario.effect.ImageData;

import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;

public class Readerer extends AbstractReaderer{
    private final ExecutorService executor;

    Readerer(ExecutorService executor) {
        this.executor = executor;
    }

    void renderPage(CharSequence source) throws Exception {
        List<ImageInfo> imageInfos = scanForImageInfo(source);
        CompletionService<ImageData> completionService =
                new ExecutorCompletionService(executor);

        for (final ImageInfo imageInfo : imageInfos) {
            completionService.submit(new Callable<ImageData>() {
                @Override
                public ImageData call() throws Exception {
                    return imageInfo.downloadImage();
                }

            });
        }

        renderText(source);
        for (int n = 0; n < imageInfos.size(); n++) {
            try {
                Future<ImageData> imageDataFutureTask =
                        completionService.take();
                ImageData imageData = imageDataFutureTask.get();
                renderImage(imageData);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (ExecutionException e) {
                throw launderThrowable(e.getCause());
            }
        }


    }

    private Exception launderThrowable(Throwable cause) throws Exception {
        throw new Exception(cause);
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }
}

