package chapter6;

import com.sun.scenario.effect.ImageData;

import java.util.ArrayList;
import java.util.List;

public class SingleThreadReaderer extends AbstractReaderer {


    void renderPage(CharSequence source) {
        // display Text
        renderText(source);
        List<ImageData> imageData = new ArrayList<ImageData>();
        // download img
        for (ImageInfo imageInfo : scanForImageInfo(source))
            imageData.add(imageInfo.downloadImage());
        // display img
        for (ImageData data: imageData)
            renderImage(data);
    }
    protected ImageInfo[] scanForImageInfo(CharSequence source) {
        return null;
    }

}
