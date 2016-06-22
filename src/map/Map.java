package map;

import game.Game;
import gfx.Screen;
import helper.BoundingBox;
import map.entity.Mob;
import map.entity.Skeleton;
import map.tile.*;
import map.entity.Player;
import map.entity.Entity;

import java.util.ArrayList;
import java.util.List;
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
	public BoundingBox cameraBox;

	// entities
	public List<Entity> entities = new ArrayList<Entity>();

	// physics
	public BoundingBox box;
	protected Random random = new Random();

	/**
	 * Constructor for the map object.
	 * @param game the game that the map belongs to
	 * @param rows the number of rows in the map
	 * @param cols the number of columns in the map
	 */
	public Map(Game game, int rows, int cols) {
		this.game = game;
		this.cols = cols;
		this.rows = rows;
		this.width = cols * tileSize;
		this.height = rows * tileSize;

		box = new BoundingBox(0, 0, width, height);
		cameraBox = new BoundingBox(0, 0, 96, 96);

		tiles = new Tile[rows][cols];

		this.add(new Player(this));
		this.add(new Skeleton(this));
		for(int i = 0; i < 3; i++){
			this.add(new Skeleton(this));
		}
		generateIsland();
	}

	/**
	 * Draws all entities and tiles to the camera, and then to the screen.
	 * @param screen the screen that the map will be rendered to
	 */
	public void render(Screen screen) {

		// Determine the offset for the screen
		int xOffset = Math.round(getPlayer().getX() + Tile.TILE_SIZE/2) - screen.width / 2;
		xOffset = Math.max(xOffset, 0);
		xOffset = Math.min(xOffset, width - screen.width);

		int yOffset = Math.round(getPlayer().getY() + Tile.TILE_SIZE/2) - screen.height / 2;
		yOffset = Math.max(yOffset, 0);
		yOffset = Math.min(yOffset, height - screen.height);

		screen.setOffset(xOffset, yOffset);
		cameraBox.setBoundary(xOffset, yOffset, screen.width, screen.height);

		// Draw each map.tile
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				Tile t = tiles[i][j];

				// We only render the tiles that overlap with the camera
				if(t.box.overlaps(cameraBox)){
					t.render(screen, this, i * Tile.TILE_SIZE, j * Tile.TILE_SIZE);
				}
			}
		}

		// Draw the entities
		for(int i = 0; i < entities.size(); i++){
			Entity e = entities.get(i);

			// We only render the entities that overlap with the camera
			if(e.box.overlaps(cameraBox)){
				e.render(screen);
			}
		}

	}

	/**
	 * Updates all map objects
	 */
	public void update() {

		// Update each map.tile
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				tiles[i][j].update();
			}
		}

		// Update the player
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update();
		}
	}

	/**
	 * Sets the map.tile at a specified position in the map.
	 * @param tile the map.tile
	 * @param row  the desired row
	 * @param col  the desired column
	 */
	public void setTile(Tile tile, int row, int col){
		tiles[row][col] = tile;
	}

	/**
	 * Returns the map.tile at a specific position in the map.
	 * @param row the row of the map.tile
	 * @param col the column of the map.tile
     * @return    the map.tile at the specified position
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
	 * @param entity the map.entity that is being checked for collisions
	 * @return    	 a boolean indicating whether a collision has occurred
	 */
	public boolean isColliding(Entity entity){
		for(int i = 0; i < tiles.length; i++){
			for(int j = 0; j < tiles[i].length; j++){
				Tile tile = tiles[i][j];
				if(tile.solid() && entity.solid() && entity.box.overlaps(tiles[i][j].box)) return true;
			}
		}
		for(int i = 0; i < entities.size(); i++){
			if(entity == entities.get(i)) continue;
			if(entity.solid() && entities.get(i).solid() && entity.box.overlaps(entities.get(i).box)) return true;
		}
		return false;
	}

	/**
	 * Determines the initial position of each enitity in the map.
	 * @param entities the list of entities being added to the map
	 */
	public void placeEntities(List<Entity> entities){

		for(int e = 0; e < entities.size(); e++){
			boolean placed = false;
			while(!placed){
				int x = random.nextInt(rows);
				int y = random.nextInt(cols);
				if(!tiles[x][y].solid()){
					entities.get(e).setPosition(x, y);
					placed = true;
				}
			}
		}
	}

	public void add(Entity e){
		if (e instanceof Mob) {
			entities.add(e);
		}
	}

	public Player getPlayer(){
		for(int i = 0; i < entities.size(); i++){
			if(entities.get(i) instanceof Player){
				return (Player) entities.get(i);
			}
		}
		return null;
	}

	/**
	 * Generates an array of tiles that is shaped like an island.
	 * Then places the entities in the map.
	 */
	public void generateIsland(){
		Random random = new Random();
		OpenSimplexNoise noise1 = new OpenSimplexNoise(random.nextLong());

		for (int x = 0; x < rows; x++) {
			for (int y = 0; y < cols; y++) {

				//Gets a height value between 0 and 1
				double value = noise1.eval((double)x / 16, (double)y / 16, 0.0);
				value += Math.abs(noise1.eval((double)x / 8, (double)y / 8, 1000.0)) / 2;
				//value += 0.4;
				double xd = x / (rows - 1.0) * 2 - 1;
				double yd = y / (cols - 1.0) * 2 - 1;
				value += 1.0 - Math.sqrt(xd * xd  + yd * yd);
				value -= Math.sqrt(xd * xd  + yd * yd);

				if(value > 0.7){
					setTile(new Stone(this, x, y), x, y);
				}else if(value > 0.1){
					setTile(new Grass(this, x, y), x, y);
				}else if(value >= 0.0){
					setTile(new Grass(this, x, y), x, y);
				}else{
					setTile(new Water(this, x, y), x, y);
				}
			}
		}

		placeEntities(entities);
	}

}
