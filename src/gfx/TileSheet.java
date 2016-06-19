package gfx;

import java.awt.image.BufferedImage;

public class TileSheet extends Bitmap{
	
	public static final TileSheet TILE_SHEET = new TileSheet(ImageLoader.loadImage("/images/tilesheet.png"), 16, 16);
	public static final TileSheet FONT = new TileSheet(ImageLoader.loadImage("/images/fontsmall.png"), 5, 6);

	public static final Bitmap GRASS = TILE_SHEET.subBitmap(0, 4);
	public static final Bitmap WATER = TILE_SHEET.subBitmap(3, 1);
	public static final Bitmap TREE = TILE_SHEET.subBitmap(4, 3);

	public int tileWidth;
	public int tileHeight;
	
	public TileSheet(BufferedImage image, int tileWidth, int tileHeight) {
		super(image.getWidth(), image.getHeight());
		pixels = image.getRGB(0, 0, width, height, null, 0, width);
		this.tileWidth = tileWidth;
		this.tileHeight = tileHeight;
	}
	
	public Bitmap subBitmap(int xo, int yo){
		Bitmap bitmap = new Bitmap(tileWidth, tileHeight);
		for(int x = 0; x < tileWidth; x++){
			for(int y = 0; y < tileHeight; y++){
				int xa = x + xo * tileWidth;
				int ya = y + yo * tileHeight;
				
				bitmap.pixels[x + y * bitmap.width] = pixels[xa + ya * width];
			}
		}
		return bitmap;
	}
	
}