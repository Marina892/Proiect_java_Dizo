package Entities.Statics;

import Entities.Statics.StaticEntity;
import Game.Handler;
import Graphics.Assets;
import Tiles.Tile;

import java.awt.*;

public class Lava extends StaticEntity {
    public Lava(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.health = Integer.MAX_VALUE;
        this.damage = 100;
        //Colision bounds
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.lava, (int) (x), (int) (y), width, height, null);
    }

    @Override
    public void die() {}
}
