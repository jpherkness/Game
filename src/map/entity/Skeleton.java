package map.entity;

import gfx.Animation;
import gfx.Bitmap;
import gfx.TileSheet;
import helper.BoundingBox;
import map.Map;
import gfx.Screen;

public class Skeleton extends Mob {

    // Animations
    public Animation animation;
    public static Bitmap[] DOWN = {TileSheet.TILE_SHEET.subBitmap(3,5), TileSheet.TILE_SHEET.subBitmap(4,5), TileSheet.TILE_SHEET.subBitmap(5,5)};
    public static Bitmap[] LEFT = {TileSheet.TILE_SHEET.subBitmap(3,6), TileSheet.TILE_SHEET.subBitmap(4,6), TileSheet.TILE_SHEET.subBitmap(5,6)};
    public static Bitmap[] RIGHT = {TileSheet.TILE_SHEET.subBitmap(3,7), TileSheet.TILE_SHEET.subBitmap(4,7), TileSheet.TILE_SHEET.subBitmap(5,7)};
    public static Bitmap[] UP = {TileSheet.TILE_SHEET.subBitmap(3,8), TileSheet.TILE_SHEET.subBitmap(4,8), TileSheet.TILE_SHEET.subBitmap(5,8)};

    public Skeleton(Map map){
        super(map);
        animation = new Animation();
        animation.setFrames(Skeleton.DOWN);
        box = new BoundingBox();
    }

    public void update(){
        ticks++;
        move();

        if(moving) animation.start();
        else animation.finish();
        animation.update();
    }

    public void render(Screen screen){
        screen.render(animation.getImage(), x, y);
    }

    public void move(){

        int xa = 0;
        int ya = 0;
        if(ticks % 60 == 0) {
            int d = random.nextInt(4);
            if (d == 0) ya = -1;
            if (d == 1) xa = 1;
            if (d == 2) ya = 1;
            if (d == 3) xa = -1;
        }
        move(xa, ya);

        if (dir == 0) animation.setFrames(Skeleton.UP);
        else if (dir == 1) animation.setFrames(Skeleton.RIGHT);
        else if (dir == 2) animation.setFrames(Skeleton.DOWN);
        else if (dir == 3) animation.setFrames(Skeleton.LEFT);

    }

    public boolean solid() {
        return true;
    }

}
