package Entities.Statics;

import Game.Handler;
import Tiles.Tile;

import java.awt.*;
import Graphics.Assets;


public class Nothing extends StaticEntity{

    public Nothing(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.health = 0;
        this.damage = 0;
        //Colision bounds
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 0;
        bounds.height = 0;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {}

    @Override
    public void die() {}
}
