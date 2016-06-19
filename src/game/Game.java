package game;

import gfx.Screen;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

import manager.InputManager;
import map.Map;

public class Game extends Canvas implements Runnable {

    public static final String NAME = "Game";

    // dimensions
    public static final int WIDTH = 128;
    public static final int HEIGHT = 128;
    public static final int SCALE = 3;

    // game loop stuff
	private boolean running;

    // rendering
	private Screen screen;
	private Map map;

    // managers
	public InputManager input;

	public Game() {
		screen = new Screen(WIDTH, HEIGHT);
        input = new InputManager(this);
		map = new Map(this, 20, 20);
	}

    // Run the game
    @Override
	public void run() {

		long then = System.nanoTime();
		long lastTime2 = System.nanoTime();
		double nsPerTick = 1000000000.0 / 60.0;
		double unprocessed = 0; // Keeps track of how many frames we need to update
		int ticks = 0;
		int frames = 0;

		while (running) {
			boolean shouldRender = false;
			long now = System.nanoTime();
			unprocessed += (now - then) / nsPerTick;

			while (unprocessed >= 1.0) {
				// Updates the game objects
				ticks++;
				update();
				shouldRender = true;
				unprocessed--;
			}

			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			// Draws the game objects
			if (shouldRender) {
				frames++;
				render();
			}

			// FPS counter report
			if (now - lastTime2 > 1000000000) {
				lastTime2 += 1000000000;
				System.out.println(ticks + " ticks, " + frames + " fps");
				frames = 0;
				ticks = 0;
			}

			then = now;
		}
	}

	// Update the games logic
	public void update() {
        map.update();
    }

	// Render the games graphics
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}

		Graphics g = getBufferStrategy().getDrawGraphics();

        // Draw to the screen
		map.render(screen);

		// Done with rendering
		g.drawImage(screen.image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g.dispose();
		bs.show();
	}

    // Start the game on a new thread
	public void start() {
		running = true;
		new Thread(this).start();
	}

    // Stop the game
	public void stop() {
		running = false;
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setMinimumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setMaximumSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		game.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        game.setFocusable(true);
        game.requestFocus();

		JFrame frame = new JFrame(Game.NAME);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(game, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);

		game.start();
	}
}