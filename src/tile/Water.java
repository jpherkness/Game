package tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Water extends Tile {

    public Water(Map map, int row, int col){
        super(map, row, col, Tile.ID_WATER);

        box.setShouldCollide(true);
    }

    @Override
    public void render(Screen screen, Map map, int x, int y) {

        boolean u = map.getTile(row, col-1).id == Tile.ID_GRASS;
        boolean r = map.getTile(row + 1, col).id == Tile.ID_GRASS;
        boolean d = map.getTile(row, col + 1).id == Tile.ID_GRASS;
        boolean l = map.getTile(row - 1, col).id == Tile.ID_GRASS;
        boolean ur = map.getTile(row + 1, col - 1).id == Tile.ID_GRASS;
        boolean dr = map.getTile(row + 1, col + 1).id == Tile.ID_GRASS;
        boolean dl = map.getTile(row - 1, col + 1).id == Tile.ID_GRASS;
        boolean ul = map.getTile(row - 1, col - 1).id == Tile.ID_GRASS;

        if(u && !r && !d && !l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(1, 1), x, y);
        }else if(u && r && !d && !l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(2, 1), x, y);
        }else if(!u && r && !d && !l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(2, 2), x, y);
        }else if(!u && r && d && !l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(2, 3), x, y);
        }else if(!u && !r && d && !l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(1, 3), x, y);
        }else if(!u && !r && d && l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(0, 3), x, y);
        }else if(!u && !r && !d && l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(0, 2), x, y);
        }else if(u && !r && !d && l){
            screen.render(TileSheet.TILE_SHEET.subBitmap(0, 1), x, y);
        }else if(ur){
            screen.render(TileSheet.TILE_SHEET.subBitmap(4, 2), x, y);
        }else if(dr){
            screen.render(TileSheet.TILE_SHEET.subBitmap(4, 1), x, y); //Corner down right
        }else if(ul){
            screen.render(TileSheet.TILE_SHEET.subBitmap(5, 2), x, y); //Corner up left
        }else if(dl){
            screen.render(TileSheet.TILE_SHEET.subBitmap(5, 1), x, y); //Corner down left
        }else{
            screen.render(TileSheet.WATER, x, y); //Corner down left

        }
    }

    public void update() {
        super.update();
    }
}
