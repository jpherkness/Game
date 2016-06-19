package map.entity;

import gfx.Screen;
import map.Map;
import helper.BoundingBox;

public abstract class Entity {

    // position
    public Map map;
    protected int x;
    protected int y;
    protected int row;
    protected int col;

    // movement
    public boolean moving;

    // physics
    public BoundingBox box;
    public boolean shouldCollide;

    /**
     * Constructor for the map.entity object.
     * @param map the map that the map.entity belongs to
     */
    public Entity(Map map, boolean shouldCollide){
        this.map = map;
        this.shouldCollide = shouldCollide;
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

    public abstract void render(Screen screen, int x, int y);
    public abstract void update();
    public abstract void updateBoundingBox();
}
