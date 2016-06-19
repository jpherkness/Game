package tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Stone extends Tile {

    public Stone(Map map, int row, int col){
        super(map, row, col, Tile.ID_STONE);

        box.setShouldCollide(true);
    }

    public void render(Screen screen, Map map, int x, int y) {

        // Determine the image that should be used
        boolean above = map.getTile(row, col - 1).id == Tile.ID_STONE;
        boolean below = map.getTile(row, col + 1).id == Tile.ID_STONE;
        boolean left  = map.getTile(row - 1, col).id == Tile.ID_STONE;
        boolean right = map.getTile(row + 1, col).id == Tile.ID_STONE;
        int index = calculateTileIndex(above, below, left, right);

        // Draw the tile
        screen.render(TileSheet.TILE_SHEET.subBitmap(index, 0), x, y);
    }

    public void update() {
        super.update();
    }

    public int calculateTileIndex(boolean above, boolean below, boolean left, boolean right) {
        int sum = 0;
        if (above) sum += 1;
        if (left)  sum += 2;
        if (below) sum += 4;
        if (right) sum += 8;
        return sum;
    }
}
