package Entities;

import Game.Handler;

import java.awt.*;

public abstract class Entity {

    protected Handler handler;
    protected float x, y;   //float pt ca sa fie mai fluid
    protected int width, height;    //pt inaltimea/latimea jucatorului sau pt a pune imagini de diferite marini
    protected Rectangle bounds;     //pt limite
    protected boolean active = true;

    public static final int DEFAULT_HEALTH = 10;
    protected int health;

    public static int POINTS = 0;
    public static int points = POINTS;

    protected static int damage;

    public Entity(Handler handler, float x, float y, int width, int height)
    {
        this.handler = handler;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        health = DEFAULT_HEALTH;

        bounds = new Rectangle(0, 0, width, height);
    }

    public static int getPoints() {
        return points;
    }

    public abstract void tick();

    public abstract void render(Graphics g);

    public abstract void die();

    public void hurt(int amount)
    {
        health -= amount;
        if(health <= 0)
        {
            active = false;
            die();
        }
    }

    public boolean checkEntityCollision(float xOffset, float yOffset)
    {
        for(Entity e : handler.getMap().getEntityManager().getEntities())
        {
            if(e.equals(this))
                continue;
            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
                return true;
        }
        return false;
    }

    public Rectangle getCollisionBounds(float xOffset, float yOffset)
    {
        return new Rectangle((int) (x + bounds.x + xOffset), (int) (y + bounds.y + yOffset), bounds.width, bounds.height);
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public static void addPoints()
    {
        points = points + 1;
    }
}
