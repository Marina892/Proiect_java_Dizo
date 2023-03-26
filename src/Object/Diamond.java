package Object;

import Game.Handler;
import Graphics.Assets;
import Tiles.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Diamond {

    public static Diamond[] diamonds = new Diamond[256];
    public static Diamond diamondv = new Diamond(Assets.diamond[0], 0);
    public static Diamond diamondr = new Diamond(Assets.diamond[1], 1);
    public static Diamond diamondg = new Diamond(Assets.diamond[2], 2);
    public static Diamond diamondrs = new Diamond(Assets.diamond[3], 3);
    public static Diamond diamondm = new Diamond(Assets.diamond[4], 4);

    protected Handler handler;
    protected BufferedImage texture;
    protected final int id;
    protected int x;
    protected int y;
    protected int count;
    boolean pickedUp = false;
    protected Rectangle bounds;

    public Diamond(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;
        count = 1;
        bounds = new Rectangle(x, y, 64, 64);
        diamonds[id] = this;
    }

    public Handler getHandler() {return handler;}

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public synchronized void tick()
    {
        if(handler.getMap().getEntityManager().getPlayer().getCollisionBounds(0f, 0f).intersects(bounds))
        {
            pickedUp = true;
            handler.getMap().getEntityManager().getPlayer().addDiamonds(this);
        }
    }

    public void render(Graphics g)
    {
        if(handler == null)
            return;

        render(g, (int) x, (int) y);
    }

    public void render(Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, 64, 64, null);
    }

    public Diamond createNew(int x, int y)
    {
        Diamond d = new Diamond(texture, id);
        d.setPosition(x,y);
        return d;
    }

    public void setPosition(int x, int y)
    {
        this.x = x + Tile.TILEWIDTH/2 - bounds.width/2;
        this.y = y + Tile.TILEHEIGHT/2 - bounds.height/2;
        bounds.x = this.x;
        bounds.y = this.y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public boolean isPickedUp() {
        return pickedUp;
    }

    public void setPickedUp(boolean pickedUp) {
        this.pickedUp = pickedUp;
    }

    public BufferedImage getTexture() {
        return texture;
    }

    public void setTexture(BufferedImage texture) {
        this.texture = texture;
    }
}
