package chapter6;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.ExecutorService;

public class FutureRenderer extends AbstractReaderer{
    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    void renderPage(CharSequence source) throws Exception {
        final List<ImageInfo> imageInfos = scanForImageInfo(source);
        Callable<List<ImageData>> task = new Callable<List<ImageData>>() {
            @Override
            public List<ImageData> call() throws Exception {
                List<ImageData> result =
                        new ArrayList<ImageData>();
                for (ImageInfo imageInfo : imageInfos)
                    result.add(imageInfo.downloadImage());
                return result;
            }
        };

        FutureTask<List<ImageData>> future = (FutureTask<List<ImageData>>) executor.submit(task);
        renderText(source);

        try {
            List<ImageData> imageData = future.get();
            for (ImageData data: imageData)
                renderImage(data);
        } catch (InterruptedException e) {
            Thread.currentThread();
            future.cancel(true);
        } catch (ExecutionException e) {
            throw launderThrowable(e.getCause());
        }

    }

    private Exception launderThrowable(Throwable cause) {
        return new Exception(cause);
    }

    private List<ImageInfo> scanForImageInfo(CharSequence source) {
        return null;
    }

}
