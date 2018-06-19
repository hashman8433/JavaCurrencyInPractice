package chapter6;

import com.sun.scenario.effect.ImageData;

public class AbstractReaderer {

    protected void renderImage(ImageData data) {

    }



    protected void renderText(CharSequence source) {

    }

    protected class ImageInfo extends AbstractReaderer {
        public ImageData downloadImage() {
            return null;
        }
    }
}
