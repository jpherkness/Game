package gfx;

import java.util.Arrays;

//This class defines a Bitmap (or 1-Dimensional collection of pixels).
public class Bitmap {

	public int width, height; //Width and Height of Bitmap (in pixels)
	public int[] pixels; //1-D array of integer colors
	
	public Bitmap(int width, int height){
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
	}

	public void render(Bitmap bitmap, int x, int y) {
        int x0 = x;
        int x1 = x + bitmap.width;
        int y0 = y;
        int y1 = y + bitmap.height;
        if (x0 < 0) x0 = 0;
        if (y0 < 0) y0 = 0;
        if (x1 > width) x1 = width;
        if (y1 > height) y1 = height;
        int ww = x1 - x0;

        for (int yy = y0; yy < y1; yy++) {
            int tp = yy * width + x0;
            int sp = (yy - y) * bitmap.width + (x0 - x);
            tp -= sp;
            for (int xx = sp; xx < sp + ww; xx++) {
                int col = bitmap.pixels[xx];
                if (col < 0) pixels[tp + xx] = col;
            }
        }
    }
    
	// This function fills the bitmap with a single color
	public void fill(int color) {
		Arrays.fill(pixels, color);
	}
	
	public void setColor(int color, int x, int y){
		//Check to make sure x actual and y actual are valid locations
		//If it is not valid, then exit this function (return)
		if(x >= this.width || x < 0) return;
        if(y >= this.height || y < 0) return;
        
        //Change the color
		this.pixels[x + y * this.width] = color;
	}
}
