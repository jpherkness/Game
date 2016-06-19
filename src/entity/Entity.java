package entity;

import gfx.Screen;
import map.Map;
import tile.BoundingBox;

/**
 *  The Entity class represents any movable object.
 */
public class Entity {

    // map
    public Map map;

    // position
    public int x;
    public int y;
    public int row;
    public int col;

    // movement
    public boolean moving;

    // physics
    public BoundingBox box;

    public Entity(Map map){
        this.map = map;
    }

    public void render(Screen screen, int x, int y){}
    public void update(){}

    public int getX(){ return x; }
    public int getY(){ return y; }
    public int getRow(){ return row; }
    public int getCol(){ return col; }
}
