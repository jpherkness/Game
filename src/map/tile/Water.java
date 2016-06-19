package map.tile;

import gfx.Screen;
import gfx.TileSheet;
import map.Map;

public class Water extends Tile {

    /**
     * Constructor for the Water map.tile.
     * @param map the map that the map.tile belongs to
     * @param row the row of the map.tile
     * @param col the column of the map.tile
     */
    public Water(Map map, int row, int col){
        super(map, row, col, Tile.ID_WATER);
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

    /**
     * Returns a boolean indicating whether or not the map.tile is solid.
     * If a map.tile is solid it cannot collide with other objects.
     * @return a boolean indicating whether or not the map.tile is solid
     */
    public boolean solid(){ return true; }
}
