package Entities;

import java.awt.*;

import Game.Handler;
import Object.*;
import Graphics.*;

public class Character extends Creatures{

    private Animation animStand;

    public Character(Handler handler, float x, float y) {
        super(handler, x, y, DEFAULT_CREATURE_WIDTH, DEFAULT_CREATURE_HEIGHT);

        bounds.x = 16;
        bounds.y = 16;
        bounds.width = 48;
        bounds.height = 48;

        animStand = new Animation(100, Assets.character_stand_left);
    }

    @Override
    public void addDiamonds(Diamond diamond) {}

    @Override
    public void tick() {
        animStand.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(animStand.getCurrentFrame(), (int) x, (int) y, width, height, null);
    }

    @Override
    public void die() {}
}
