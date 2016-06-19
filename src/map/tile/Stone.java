package map.tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Stone extends Tile {

    /**
     * Constructor for the Stone map.tile.
     * @param map the map that the map.tile belongs to
     * @param row the row of the map.tile
     * @param col the column of the map.tile
	 */
    public Stone(Map map, int row, int col){
        super(map, row, col, Tile.ID_STONE);
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

        // Determine the image that should be used
        boolean above = map.getTile(row, col - 1).id == Tile.ID_STONE;
        boolean below = map.getTile(row, col + 1).id == Tile.ID_STONE;
        boolean left  = map.getTile(row - 1, col).id == Tile.ID_STONE;
        boolean right = map.getTile(row + 1, col).id == Tile.ID_STONE;
        int index = calculateTileIndex(above, below, left, right);

        // Draw the map.tile
        screen.render(TileSheet.TILE_SHEET.subBitmap(index, 0), x, y);
    }

    /**
     * Determines the index for the map.tile image.
     * @param above a boolean indicating if the map.tile above is stone
     * @param below a boolean indicating if the map.tile below is stone
     * @param left  a boolean indicating if the map.tile left is stone
     * @param right a boolean indicating if the map.tile right is stone
     * @return
	 */
    public int calculateTileIndex(boolean above, boolean below, boolean left, boolean right) {
        int sum = 0;
        if (above) sum += 1;
        if (left)  sum += 2;
        if (below) sum += 4;
        if (right) sum += 8;
        return sum;
    }

    /**
     * Returns a boolean indicating whether or not the map.tile is solid.
     * If a map.tile is solid it cannot collide with other objects.
     * @return a boolean indicating whether or not the map.tile is solid
	 */
    public boolean solid(){ return true; }
}
