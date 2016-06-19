package map.tile;

import gfx.Screen;
import helper.BoundingBox;
import map.Map;

/**
 *  The Tile class represents any immovable object.
 */
public abstract class Tile {

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

    /**
     * Constructor for the Tile object.
     * @param map the map that the map.tile belongs to
     * @param row the row of the map.tile
     * @param col the column of the map.tile
     * @param id  the id of the map.tile
	 */
    public Tile(Map map, int row, int col, int id){
        this.map = map;
        this.row = row;
        this.col = col;
        this.id = id;

        // Create an empty bounding box
        box = new BoundingBox(row * Tile.TILE_SIZE, col * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    /**
     * Updates the map.tile.
	 */
    public void update(){}

    /**
     * Renders the map.tile in the screen at a specified position.
     * @param screen the screen which will be rendered onto
     * @param map    the map that the map.tile belong to
     * @param x      the x position of the map.tile
     * @param y      the y position of the map.tile
	 */
    public void render(Screen screen, Map map, int x, int y){}

    /**
     * Returns a boolean indicating whether or not the map.tile is solid.
     * If a map.tile is solid it cannot collide with other objects.
     * @return a boolean indicating whether or not the map.tile is solid
	 */
    public boolean solid(){
        return false;
    }

}
