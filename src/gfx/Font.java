package gfx;

import java.awt.*;

public class Font {

	public static final String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + 
									   "0123456789!?.,:;()[]      ";
	/*
	 * Render method that renders black text when no color is supplied
	 */
	public static void render(Screen screen, String msg, int x, int y){
		render(screen, msg, Color.black, x, y);
	}

	public static void render(Screen screen, String msg, Color color, int x, int y){
		msg = msg.toUpperCase();
		for(int i = 0; i < msg.length(); i++){
			int index = chars.indexOf(msg.charAt(i));
			if (index >= 0) {
				Bitmap c = TileSheet.FONT.subBitmap(index % 26, index / 26);
				screen.render(c, x, y);

				//Fix this !!! Make this automatic not static!!!


				switch(msg.charAt(i)){
					case 'M': x += 6; break;
					case 'W': x += 6; break;
					case 'L': x += 4; break;
					case 'T': x += 4; break;
					case 'I': x += 4; break;
					case '(': x += 3; break;
					case ')': x += 3; break;
					default : x += 5; break;
				}
			}
		}
	}
	
	public static int getWidth(String msg){
		return msg.length() * TileSheet.FONT.tileWidth;
	}
	
	public static int getHeight(){
		return TileSheet.FONT.tileHeight;
	}
}
