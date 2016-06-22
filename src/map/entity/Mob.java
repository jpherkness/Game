package map.entity;

import gfx.TileSheet;
import helper.BoundingBox;
import map.Map;
import gfx.Screen;
import map.tile.Tile;

public class Mob extends Entity{

    protected int health;
    protected int maxHealth;
    protected int dir;

    public Mob(Map map){
        super(map);
    }

    public void update(){

    }

    public void render(Screen screen){

    }

    /**
     * Checks to see if the position is valid.
     * @param x
     * @param y
     */
    public void canMove(int x, int y){

    }
    /**
     * Moves the mob based on the tile offset specified
     * @param xa x offset
     * @param ya y offset
     */
    public void move(int xa, int ya) {

        // Determine the direction of the mob

        if(!moving){

            if (ya < 0) dir = 0;
            if (xa > 0) dir = 1;
            if (ya > 0) dir = 2;
            if (xa < 0) dir = 3;

            // Move the bounding box where we want to go and check for collisions
            BoundingBox oldBox = getBoundingBox(row * Tile.TILE_SIZE, col * Tile.TILE_SIZE);
            box = oldBox;
            box.move(xa * Tile.TILE_SIZE, ya * Tile.TILE_SIZE);
            if (!box.inside(map.box) || map.isColliding(this)) {
                box = oldBox;
            }else{
                row += xa;
                col += ya;
            }
        }

        // Update the actual position of the mob
        if(x != row * TileSheet.TILE_SHEET.tileWidth || y != col * TileSheet.TILE_SHEET.tileHeight ){
            moving = true;
            if(x < row * TileSheet.TILE_SHEET.tileWidth) x++;
            if(x > row * TileSheet.TILE_SHEET.tileWidth) x--;
            if(y < col * TileSheet.TILE_SHEET.tileHeight) y++;
            if(y > col * TileSheet.TILE_SHEET.tileHeight) y--;
        }else{
            moving = false;
        }
    }

    /**
     * Returns the current health of the mob
     * @return the current health of the mob
     */
    public int getHealth(){
        return health;
    }

    /**
     * Sets the position of the mob.
     * @param row the target row for the mob
     * @param col the target col for the mob
     */
    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
        this.x = row * Tile.TILE_SIZE;
        this.y = col * Tile.TILE_SIZE;
    }

    public BoundingBox getBoundingBox(int x, int y){
        return new BoundingBox(x, y, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

}
