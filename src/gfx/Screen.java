package gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap{
	
	public BufferedImage image;
	
	public Screen(int width, int height) {
		super(width, height);
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
	}

	public void overlay(Screen screen2, int xa, int ya) {
		int[] oPixels = screen2.pixels;
		int i = 0;
		for (int y = 0; y < screen2.height; y++) {
			for (int x = 0; x < screen2.width; x++) {
				int xx = x + xa;
				int yy = y + ya;
				pixels[xx + yy * width] = screen2.pixels[i];
				i++;
			}

		}
	}

}
