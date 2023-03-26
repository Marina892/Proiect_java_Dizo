package Entities.Statics;

import Game.Handler;
import Tiles.Tile;
import Graphics.Assets;

import java.awt.*;

public class Rock extends StaticEntity{

    public Rock(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.health = 5;
        this.damage = 0;
        //Colision bounds
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = width;
        bounds.height = height;
    }

    @Override
    public void tick(){}

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.rock, (int) (x), (int) (y), width, height, null);
    }

    @Override
    public void die() {}

}
