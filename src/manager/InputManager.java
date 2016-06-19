package manager;
/**
 * Created by Joseph on 6/15/16.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import game.Game;

public class InputManager implements KeyListener {

    public boolean keys[] = new boolean[65536];

    public InputManager(Game game){
        game.addKeyListener(this);
    }
    public void keyPressed(KeyEvent e) {
        int keycode = e.getKeyCode();
        keys[keycode] = true;
    }

    public void keyReleased(KeyEvent e) {
        int keycode = e.getKeyCode();
        keys[keycode] = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }
}