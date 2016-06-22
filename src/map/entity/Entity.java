package map.entity;

import gfx.Screen;
import map.Map;
import helper.BoundingBox;
import java.util.Random;
public abstract class Entity {

    protected final Random random = new Random();

    // position
    public Map map;
    protected int x;
    protected int y;
    protected int row;
    protected int col;
    protected int ticks;

    // movement
    public boolean moving;

    // physics
    public BoundingBox box;

    /**
     * Constructor for the map.entity object.
     * @param map the map that the map.entity belongs to
     */
    public Entity(Map map){
        this.map = map;
    }

    /**
     * Returns the current x position of the map.entity.
     * @return the x position of the map.entity
     */
    public int getX(){
        return x;
    }

    /**
     * Returns the current y position of the map.entity.
     * @return the y position of the map.entity
     */
    public int getY(){
        return y;
    }

    /**
     * Returns the current row of the map.entity.
     * @return the row of the map.entity
     */
    public int getRow(){
        return row;
    }

    /**
     * Returns the current col of the map.entity.
     * @return the col of the map.entity
     */
    public int getCol(){
        return col;
    }

    /**
     * Renders the entity on the screen at a specified position
     * @param screen the screen that the entity should be rendered on
     */
    public void render(Screen screen){ }

    /**
     * Update the entity
     */
    public void update(){ }

    /**
     * Updates the bounding box of the entity
     */
    public void updateBoundingBox(){ }

    /**
     * Returns a boolean indicating whether or not the entity is solid.
     * If a entity is solid, it cannot collide with other objects.
     * @return a boolean indicating whether or not the tile is solid
     */
    public boolean solid(){
        return false;
    }

    public void setPosition(int row, int col){
        this.row = row;
        this.col = col;
    }
}
