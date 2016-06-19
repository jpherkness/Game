package gfx;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen extends Bitmap{
	
	public BufferedImage image;
	public int xOffset;
	public int yOffset;

	public Screen(int width, int height) {
		super(width, height);
		
		image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

		xOffset = 0;
		yOffset = 0;
	}

	public void overlay(Screen screen, int xa, int ya) {
		int i = 0;
		for (int y = 0; y < screen.height; y++) {
			for (int x = 0; x < screen.width; x++) {
				int xx = x + xa;
				int yy = y + ya;
				pixels[xx + yy * width] = screen.pixels[i];
				i++;
			}

		}
	}

	public void setOffset(int xOffset, int yOffset){
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}

	@Override
	public void render(Bitmap bitmap, int x, int y) {
		super.render(bitmap, x - xOffset, y - yOffset);
	}
}
