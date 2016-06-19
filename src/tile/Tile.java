package tile;

import gfx.Screen;
import map.Map;

/**
 *  The Tile class represents any immovable object.
 */
public class Tile {

    // Map
    public Map map;

    public int id;
    public int col;
    public int row;
    public BoundingBox box;

    // Tile IDs
    public static final int ID_GRASS = 0;
    public static final int ID_WATER = 1;
    public static final int ID_STONE = 2;
    public static final int ID_TREE = 3;
    public static final int TILE_SIZE = 16;

    public Tile(Map map, int row, int col, int id){
        this.map = map;
        this.row = row;
        this.col = col;
        this.id = id;

        // Create an empty bounding box
        box = new BoundingBox(row * Tile.TILE_SIZE, col * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, false);
    }

    public void update(){}
    public void render(Screen screen, Map map, int x, int y){ }

}
