package tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Tree extends Tile{
    public Tree(Map map, int row, int col){
        super(map, row, col, Tile.ID_TREE);
    }

    public void render(Screen screen, Map map, int x, int y) {
        super.render(screen, map, x, y);
        screen.render(TileSheet.TREE, x, y);
    }

    public void update() {
        super.update();
    }
}
