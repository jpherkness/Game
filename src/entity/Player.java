package entity;

import java.awt.event.KeyEvent;

import map.Map;
import gfx.*;
import tile.BoundingBox;
import tile.Tile;

public class Player extends Entity{

    // Animations
    public static Animation animation;
    public static Bitmap[] DOWN = {TileSheet.TILE_SHEET.subBitmap(0,5), TileSheet.TILE_SHEET.subBitmap(1,5), TileSheet.TILE_SHEET.subBitmap(2,5)};
    public static Bitmap[] LEFT = {TileSheet.TILE_SHEET.subBitmap(0,6), TileSheet.TILE_SHEET.subBitmap(1,6), TileSheet.TILE_SHEET.subBitmap(2,6)};
    public static Bitmap[] RIGHT = {TileSheet.TILE_SHEET.subBitmap(0,7), TileSheet.TILE_SHEET.subBitmap(1,7), TileSheet.TILE_SHEET.subBitmap(2,7)};
    public static Bitmap[] UP = {TileSheet.TILE_SHEET.subBitmap(0,8), TileSheet.TILE_SHEET.subBitmap(1,8), TileSheet.TILE_SHEET.subBitmap(2,8)};

    public Player(Map map){
        super(map);

        animation = new Animation();
        animation.setFrames(Player.DOWN);

        box = new BoundingBox(true);
    }

    public void render(Screen screen, int x, int y) {
        super.render(screen, x, y);
        screen.render(animation.getImage(), x, y);
    }

    public void update(){
        move();
        animation.update();
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
        this.x = row * Tile.TILE_SIZE;
        this.y = col * Tile.TILE_SIZE;
    }

    public void move(){

        // Change the target position
        int oldRow = row;
        int oldCol = col;

        if(map.game.input.keys[KeyEvent.VK_UP] && !moving){
            col--;
            if(animation.frames != Player.UP){ animation.setFrames(Player.UP); }
        }else if(map.game.input.keys[KeyEvent.VK_DOWN] && !moving) {
            col++;
            if(animation.frames != Player.DOWN){ animation.setFrames(Player.DOWN); }
        }else if(map.game.input.keys[KeyEvent.VK_LEFT] && !moving){
            row--;
            if(animation.frames != Player.LEFT){ animation.setFrames(Player.LEFT); }
        }else if(map.game.input.keys[KeyEvent.VK_RIGHT] && !moving) {
            row++;
            if (animation.frames != Player.RIGHT) { animation.setFrames(Player.RIGHT); }
        }else if(map.game.input.keys[KeyEvent.VK_SPACE] && !moving){
            map.generateIsland(map);
            map.placePlayer(this, map);
        }

        // Check for collisions in the players target position
        box.setBoundary(row * 16, col * 16, 16, 16);
        if(!box.isContained(map.box) || map.isColliding(box)){
            row = oldRow;
            col = oldCol;
        }

        // Change the actual position
        if(x != row * TileSheet.TILE_SHEET.tileWidth || y != col * TileSheet.TILE_SHEET.tileHeight ){
            moving = true;
            animation.start();

            if(x < row * TileSheet.TILE_SHEET.tileWidth){
                x++;
            }else if(x > row * TileSheet.TILE_SHEET.tileWidth){
                x--;
            }else if(y < col * TileSheet.TILE_SHEET.tileHeight){
                y++;
            }else if(y > col * TileSheet.TILE_SHEET.tileHeight){
                y--;
            }

        }else{
            moving = false;
            animation.stop();
        }
    }


}
