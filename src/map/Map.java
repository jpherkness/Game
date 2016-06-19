package map;

import game.Game;
import gfx.Font;
import gfx.Screen;
import tile.BoundingBox;
import tile.*;
import entity.Player;
import java.util.Random;
import noise.OpenSimplexNoise;

public class Map {

	public Game game;

	// map
	private Tile[][] tiles;
	private int tileSize = 16;
	private int rows;
	private int cols;
	private int width;
	private int height;

	// camera
	public Screen camera;
	public BoundingBox cameraBox;
	public int cameraOffsetX;
	public int cameraOffsetY;

	// entities
	public Player player;

	// physics
	public BoundingBox box;

	public Map(Game game, int rows, int cols) {
		this.game = game;
		this.cols = cols;
		this.rows = rows;
		this.width = cols * tileSize;
		this.height = rows * tileSize;

		tiles = new Tile[rows][cols];
		player = new Player(this);

		box = new BoundingBox(0, 0, width, height, false);
		cameraBox = new BoundingBox(0, 0, 96, 96, false);
		camera = new Screen(game.WIDTH, game.HEIGHT);

		generateIsland(this);
		placePlayer(player, this);
		updateCamera();
	}

	/**
	 * Draws all entities and tiles to the camera, and then to the screen.
	 * @param screen the screen that the map will be rendered to
     */
	public void render(Screen screen) {

		// Draw each tile
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				// We only render the tiles that overlap with the camera
				if(tiles[i][j].box.isOverlapping(cameraBox)){
					tiles[i][j].render(camera, this, i * Tile.TILE_SIZE + cameraOffsetX, j * Tile.TILE_SIZE + cameraOffsetY);
				}
			}
		}

		// Draw the player
		player.render(camera, player.getX() + cameraOffsetX, player.getY() + cameraOffsetY);

		// Draw the camera onto the screen
		screen.overlay(camera, 0, 0);
	}

	/**
	 * Updates all map objects
	 */
	public void update() {

		// Update each tile
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				tiles[i][j].update();
			}
		}

		// Update the player
		player.update();

		//Update the cameras position
		updateCamera();
	}

	/**
	 *  Updates the offset and bounding box of the camera.
	 */
	public void updateCamera(){
		cameraOffsetX = camera.width / 2 - Math.round(player.getX() + Tile.TILE_SIZE/2);
		cameraOffsetX = Math.min(cameraOffsetX, 0);
		cameraOffsetX = Math.max(cameraOffsetX, camera.width - width);

		cameraOffsetY = camera.height / 2 - Math.round(player.getY() + Tile.TILE_SIZE/2);
		cameraOffsetY = Math.min(cameraOffsetY, 0);
		cameraOffsetY = Math.max(cameraOffsetY, camera.height - height);

		cameraBox.setBoundary(-cameraOffsetX, -cameraOffsetY, camera.width, camera.height);
	}

	/**
	 * Sets the tile at a specified position in the map.
	 * @param tile the tile
	 * @param row  the desired row
	 * @param col  the desired column
     */
	public void setTile(Tile tile, int row, int col){
		tiles[row][col] = tile;
	}

	/**
	 * Returns the tile at a specific position in the map.
	 * @param row the row of the tile
	 * @param col the column of the tile
     * @return    the tile at the specified position
     */
	public Tile getTile(int row, int col){

		if(row >= 0 && row < rows && col >= 0 && col < cols){
			return tiles[row][col];
		}else{
			return new Water(this, row, col);
		}
	}

	/**
	 * Returns a boolean indicating whether or not a bounding box is colliding with any
	 * other bounding boxes in the map.
	 * @param box the bounding box that is being
	 * @return    a boolean indicating whether a collision has occurred
     */
	public boolean isColliding(BoundingBox box){
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				if(box.isColliding(tiles[i][j].box)) return true;
			}
		}
		return false;
	}

	/**
	 * Determines the initial position of the player in the map.
	 * @param player the player being added to the map
	 * @param map    the map
     */
	public void placePlayer(Player player, Map map){
		for(int i = 0; i < map.tiles.length; i++){
			for(int j = 0; j < map.tiles[i].length; j++){
				if(map.tiles[i][j].id == Tile.ID_GRASS){
					player.setPosition(map.tiles[i][j].row, map.tiles[i][j].col);
					return;
				}
			}
		}
	}

	/**
	 * Generates an array of tiles that is shaped like an island.
	 * @param map the map whose wiles will be modified
     */
	public static void generateIsland(Map map){
		Random random = new Random();
		OpenSimplexNoise noise1 = new OpenSimplexNoise(random.nextLong());

		for (int x = 0; x < map.rows; x++) {
			for (int y = 0; y < map.cols; y++) {

				//Gets a height value between 0 and 1
				double value = noise1.eval((double)x / 16, (double)y / 16, 0.0);
				value += Math.abs(noise1.eval((double)x / 8, (double)y / 8, 1000.0)) / 2;
				//value += 0.4;
				double xd = x / (map.rows - 1.0) * 2 - 1;
				double yd = y / (map.cols - 1.0) * 2 - 1;
				value += 1.0 - Math.sqrt(xd * xd  + yd * yd);
				value -= Math.sqrt(xd * xd  + yd * yd);

				if(value > 0.7){
					map.setTile(new Stone(map, x, y), x, y);
				}else if(value > 0.1){
					map.setTile(new Grass(map, x, y), x, y);
				}else if(value >= 0.0){
					map.setTile(new Grass(map, x, y), x, y);
				}else{
					map.setTile(new Water(map, x, y), x, y);
				}
			}
		}
	}

}
