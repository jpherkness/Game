package map.tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Grass extends Tile{

    /**
     * Constructor for the Grass map.tile.
     * @param map the map that the map.tile belongs to
     * @param row the row of the map.tile
     * @param col the column of the map.tile
	 */
    public Grass(Map map, int row, int col){
        super(map, row, col, Tile.ID_GRASS);
    }

    /**
     * Updates the map.tile.
	 */
    public void update() {
        super.update();
    }

    /**
     * Renders the map.tile in the screen at a specified position.
     * @param screen the screen which will be rendered onto
     * @param map    the map that the map.tile belong to
     * @param x      the x position of the map.tile
     * @param y      the y position of the map.tile
	 */
    public void render(Screen screen, Map map, int x, int y) {
        super.render(screen, map, x, y);
        screen.render(TileSheet.GRASS, x, y);
    }

}
