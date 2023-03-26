package Entities.Statics;

import Entities.Statics.StaticEntity;
import Game.Handler;
import Tiles.Tile;
import Graphics.*;

import java.awt.*;

public class DeathLine extends StaticEntity {
    public DeathLine(Handler handler, float x, float y) {
        super(handler, x, y, Tile.TILEWIDTH, Tile.TILEHEIGHT);

        this.health = Integer.MAX_VALUE;
        this.damage = 100;
        bounds.x = 0;
        bounds.y = 0;
        bounds.width = 64;
        bounds.height = 64;
    }

    @Override
    public void tick() {}

    @Override
    public void render(Graphics g) {}

    @Override
    public void die() {}
}
