package Input;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener
{
    private boolean[] keys;
    public boolean jump, left, right, hit;

    public KeyManager()
    {
        keys = new boolean[256];
    }

    public void tick()
    {
        jump = keys[KeyEvent.VK_W];
        left = keys[KeyEvent.VK_A];
        right = keys[KeyEvent.VK_D];
        hit = keys[KeyEvent.VK_SPACE];
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
       keys[e.getKeyCode()] = true;
       System.out.println("Pressed!");
    }

    @Override
    public void keyReleased(KeyEvent e)
    {
        keys[e.getKeyCode()] = false;
        System.out.println("Released!");
    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
