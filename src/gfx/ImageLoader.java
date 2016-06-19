package gfx;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageLoader {

	public static BufferedImage loadImage(String path) {
		try {
			return ImageIO.read(ImageLoader.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return null;
	}

    public static Bitmap loadBitmap(String path){
        BufferedImage img =  loadImage(path);
        Bitmap bitmap = new Bitmap(img.getWidth(), img.getHeight());
        bitmap.pixels = img.getRGB(0, 0, img.getWidth(), img.getHeight(), null, 0, img.getWidth());
        return bitmap;
    }
}