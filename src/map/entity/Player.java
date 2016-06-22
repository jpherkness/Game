package map.entity;

import java.awt.event.KeyEvent;

import map.Map;
import gfx.*;
import helper.BoundingBox;

public class Player extends Mob {

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
        super(map);

        animation = new Animation();
        animation.setFrames(Player.DOWN);

        box = new BoundingBox();

        dir = 2;
    }

    /**
     * Updates the position and state of the player.
     */
    public void update(){
        move();

        // Update animation
        if(moving) animation.start();
        else animation.finish();
        animation.update();
    }

    /**
     * Renders the player on the screen at a specified position
     * @param screen the screen that should be rendered onto
     */
    public void render(Screen screen) {
        screen.render(animation.getImage(), x, y);
    }

    /**
     * Updates the bounding box of the player.
     */
    public void updateBoundingBox() {
        box.setBoundary(row * 16, col * 16, 16, 16);
    }

    /**
     * Changes the players position based on what keys are being pressed.
     * First the position of the players bounding box is changed. Then
     * the bounding box is checked for collision. Last the players position
     * is modified.
     */
    public void move(){

        // Change the target position
        int deltaRow = 0;
        int deltaCol = 0;
        if (map.game.input.keys[KeyEvent.VK_UP]) deltaCol--;
        else if (map.game.input.keys[KeyEvent.VK_DOWN]) deltaCol++;
        else if (map.game.input.keys[KeyEvent.VK_RIGHT]) deltaRow++;
        else if (map.game.input.keys[KeyEvent.VK_LEFT]) deltaRow--;
        else if (map.game.input.keys[KeyEvent.VK_SPACE]) map.generateIsland();
        move(deltaRow, deltaCol);

        // Sets the frames for the animation
        if (dir == 0) animation.setFrames(Player.UP);
        else if (dir == 1) animation.setFrames(Player.RIGHT);
        else if (dir == 2) animation.setFrames(Player.DOWN);
        else if (dir == 3) animation.setFrames(Player.LEFT);


    }

    public boolean solid() {
        return true;
    }
}
