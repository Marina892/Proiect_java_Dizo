package Tiles;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static Tile[] tiles = new Tile[256];

    public static final int TILEWIDTH = 64, TILEHEIGHT = 64;
    protected BufferedImage texture;
    protected final int id;

    public Tile(BufferedImage texture, int id)
    {
        this.texture = texture;
        this.id = id;

        tiles[id] = this;
    }

    public void tick()
    {

    }

    public void render (Graphics g, int x, int y)
    {
        g.drawImage(texture, x, y, TILEWIDTH, TILEHEIGHT, null);
    }

    public boolean isSolid()
    {
        return true;
    }

    public int getId()
    {
        return id;
    }

    public static void init()
    {
        for(int i = 0; i < 28; i++)
            tiles[i] = new TileCollision(i);

        for(int i =  28; i < 36; i++)
            tiles[i] = new BackgroundTile(i);
    }
}
