package map.entity.mob;

import java.awt.event.KeyEvent;

import map.entity.Entity;
import map.Map;
import gfx.*;
import helper.BoundingBox;
import map.tile.Tile;

public class Player extends Entity {

    // animations
    public static Animation animation;
    public static Bitmap[] DOWN = {TileSheet.TILE_SHEET.subBitmap(0,5), TileSheet.TILE_SHEET.subBitmap(1,5), TileSheet.TILE_SHEET.subBitmap(2,5)};
    public static Bitmap[] LEFT = {TileSheet.TILE_SHEET.subBitmap(0,6), TileSheet.TILE_SHEET.subBitmap(1,6), TileSheet.TILE_SHEET.subBitmap(2,6)};
    public static Bitmap[] RIGHT = {TileSheet.TILE_SHEET.subBitmap(0,7), TileSheet.TILE_SHEET.subBitmap(1,7), TileSheet.TILE_SHEET.subBitmap(2,7)};
    public static Bitmap[] UP = {TileSheet.TILE_SHEET.subBitmap(0,8), TileSheet.TILE_SHEET.subBitmap(1,8), TileSheet.TILE_SHEET.subBitmap(2,8)};

    /**
     * Constructor for the player object.
     * @param map the map that the player belongs to
     */
    public Player(Map map){
        super(map, true);

        animation = new Animation();
        animation.setFrames(Player.DOWN);

        box = new BoundingBox();
    }

    /**
     * Renders the player on the screen at a specified position
     * @param screen the screen that should be rendered onto
     * @param x      the specified x position of the player
     * @param y      the specified y position of the player
     */
    public void render(Screen screen, int x, int y) {
        screen.render(animation.getImage(), x, y);
    }

    /**
     * Updates the position and state of the player.
     */
    public void update(){
        move();
        animation.update();
    }

    /**
     * Updates the bounding box of the player.
     */
    public void updateBoundingBox() {
        box.setBoundary(row * 16, col * 16, 16, 16);
    }

    /**
     * Sets the position of the player.
     * @param row the target row for the player
     * @param col the target col for the player
     */
    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
        this.x = row * Tile.TILE_SIZE;
        this.y = col * Tile.TILE_SIZE;
    }

    /**
     * Changes the players position based on what keys are being pressed.
     * First the position of the players bounding box is changed. Then
     * the bounding box is checked for collision. Last the players position
     * is modified.
     */
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
        updateBoundingBox();
        if(!box.inside(map.box) || map.isColliding(this)){
            row = oldRow;
            col = oldCol;
            updateBoundingBox();
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
            animation.finish();
        }
    }


}
