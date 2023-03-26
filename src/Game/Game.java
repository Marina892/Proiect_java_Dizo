package Game;

import java.awt.*;
import java.awt.image.*;
import Graphics.*;
import Input.KeyManager;
import Input.MouseManager;
import States.*;
import Tiles.*;


public class Game implements Runnable   //Pentru a defini o clasă care este conformă cu o interfață anume folosim cuvantul cheie implements.
        // Această relație este asemănătoare cu moștenirea, cu diferența că nu se
        //moștenește comportamentul, ci doar "interfața";
{

    private Display display;
    public String Title;
    private int width, height;
    private Thread thread;
    private boolean run = false;
    private BufferStrategy bs;
    private Graphics gr;
    //private int fps;

    //States
    public State gameState;
    public State menuState;
    public State loseState;
    public State winState;

    //Input
    private KeyManager keyManager;
    private MouseManager mouseManager;

    //Handler
    private Handler handler;

    //private BufferedImage backgroung;

    public Game(String Title, int width, int height)
    {
        this.Title = Title;
        this.width = width;
        this.height = height;
        keyManager = new KeyManager();
        mouseManager = new MouseManager();
    }

    private void init()
    {
        display = Display.getInstance(Title, width, height);
        display.getFrame().addKeyListener(keyManager);
        display.getFrame().addMouseListener(mouseManager);
        display.getFrame().addMouseMotionListener(mouseManager);
        display.getCanvas().addMouseListener(mouseManager);
        display.getCanvas().addMouseMotionListener(mouseManager);

        //backgroung = ImageLoader.loadImage("/textures/fundal_nivel_1.png");

        Assets.init();

        handler = new Handler(this);

        gameState = new GameState(handler);
        menuState = new MenuState(handler);
        loseState = new LoseState(handler);
        winState = new WinState(handler);

        State.setState(menuState);
    }

    private void tick()
    {
        keyManager.tick();
        if(State.getState() != null)
        {
            State.getState().tick();
        }
    }

    private void render()
    {
        bs = display.getCanvas().getBufferStrategy();
        if(bs == null)
        {
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        gr = bs.getDrawGraphics();
        //Clear Screan
        gr.clearRect(0, 0, width, height);

        //gr.drawImage(backgroung, 0, 0, null);   //observer - anunta modificarile = null - nu anunta

        if(State.getState() != null)
        {
            State.getState().render(gr);
        }

        bs.show();
        gr.dispose();
    }

    public void run()
    {
        init();
        Tile.init();

        int fps = 60;   //frame per second
        double tpt = 1000000000 / fps;  //time per tick
        double delta = 0;
        long now;
        long lastTime = System.nanoTime();
        long timer = 0;
        int ticks = 0;

        while(run)
        {
            now = System.nanoTime();
            delta += (now - lastTime) / tpt;
            timer += now - lastTime;
            lastTime = now;

            if(delta >= 1)
            {
                tick();
                render();
                ticks++;
                delta--;
            }

            if(timer >= 1000000000)
            {
                System.out.println("Ticks and Frames: " + ticks);
                //this.fps = ticks;
                ticks = 0;
                timer = 0;
            }
        }
        stop();
    }

    public KeyManager getKeyManager()
    {
        return keyManager;
    }

    public MouseManager getMouseManager() { return mouseManager;}

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public synchronized void start()    //synchronized folosit cu thread
    {
        if(run)
            return;
        run = true;
        thread = new Thread(this);
        thread.start();
    }

    public synchronized void stop()
    {
        if(!run)
            return;
        run = false;
        try
        {
            thread.join();
        }catch (InterruptedException e)
        {
            e.printStackTrace();
        }
    }
}
