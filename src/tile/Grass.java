package tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Grass extends Tile{

    public Grass(Map map, int row, int col){
        super(map, row, col, Tile.ID_GRASS);
    }

    public void render(Screen screen, Map map, int x, int y) {
        super.render(screen, map, x, y);
        screen.render(TileSheet.GRASS, x, y);
    }

    public void update() {
        super.update();
    }
}
