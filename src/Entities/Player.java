package Entities;

import java.awt.*;
import java.awt.image.BufferedImage;

import Entities.Statics.Lava;
import Graphics.*;
import Game.*;
import States.State;
import Tiles.Tile;
import Object.Diamond;

public class Player extends  Creatures
{
    //Animation
    private Animation animRight, animLeft, animStandRight, animJumpRight, animHitRight, animDieRight, animStandLeft;

    //Attack timer
    private long lastAttackTimer, attackCooldown = 400, attackTimer = attackCooldown;

    private CurrentDir currentDir;
    boolean isLeft, isRight;

    public Player(Handler handler, float x, float y)
    {
        super(handler, x, y, Creatures.DEFAULT_CREATURE_WIDTH, Creatures.DEFAULT_CREATURE_HEIGHT);

        this.health = 100;
        bounds.x = 12;
        bounds.y = 19;
        bounds.width = 35;
        bounds.height = 40;

        //Animation
        animRight = new Animation(100, Assets.player_right);
        animLeft = new Animation(100, Assets.player_left);
        animStandRight = new Animation(100, Assets.player_stand_right);
        animStandLeft = new Animation(100, Assets.player_stand_left);
        animJumpRight = new Animation(100, Assets.player_jump_right);
        animHitRight = new Animation(100, Assets.player_hit_right);
        animDieRight = new Animation(100, Assets.player_die_right);

        currentDir = CurrentDir.right;
    }

    @Override
    public void tick()
    {
        //Animation
        animRight.tick();
        animLeft.tick();
        animStandRight.tick();
        animStandLeft.tick();
        animJumpRight.tick();
        animHitRight.tick();
        animDieRight.tick();

        //Movement
        getInput();
        move();

        //Atack
        checkAttacks();
    }

    private void checkAttacks(){
        attackTimer += System.currentTimeMillis() - lastAttackTimer;
        lastAttackTimer = System.currentTimeMillis();
        if(attackTimer < attackCooldown)
            return;

        Rectangle cb = getCollisionBounds(0, 0);   //collision bounds

        Rectangle ar = new Rectangle();      //attack rectangle
        int arSize = 10;

        ar.width = arSize;
        ar.height = arSize;

        if(handler.getKeyManager().hit)     //pentru atac in dreapta
        {
            ar.x = cb.x + cb.width;
            ar.y = cb.y + cb.height / 2 - arSize / 2;
        }else
            return;

        attackTimer = 0;

        for(Entity e : handler.getMap().getEntityManager().getEntities())
        {
            if(e.equals(this))
                continue;

            if(e.getCollisionBounds(0, 0).intersects(ar))
            {
                e.hurt(1);
                return;
            }
        }
    }

    private void getInput()
    {
        xMove = 0;
        yMove = 0;

        if(handler.getKeyManager().jump)
            jumpStrength = 5.75f;

        yMove -= jumpStrength;
        jumpStrength -= Weight;
        if(yMove > 6)
            yMove = 5;

        if(handler.getKeyManager().left) {
            xMove = -speed;
            currentDir = CurrentDir.left;
            isLeft = true;
            isRight = false;
        }
        if(handler.getKeyManager().right) {
            xMove = speed;
            currentDir = CurrentDir.right;
            isRight = true;
            isLeft = false;
        }
    }

    @Override
    public void render(Graphics g)
    {
        g.drawImage(getCurrentAnimationFrame(), (int) x, (int) y, width, height, null);
    }

    @Override
    public void die() {
        System.out.println("You lose!");
        State.setState(handler.getGame().loseState);
        handler.getMap().getEntityManager().removePlayer(handler.getPlayer());
    }

    private BufferedImage getCurrentAnimationFrame()
    {
        if(xMove < 0)
        {
            return animLeft.getCurrentFrame();
        }
        else if(xMove > 0)
        {
            return animRight.getCurrentFrame();
        }else if(handler.getKeyManager().jump)
        {
            return animJumpRight.getCurrentFrame();
        }/*else if(!collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT) &&
                !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, (int) (y + yMove + bounds.y) / Tile.TILEHEIGHT) &&
                !collisionWithTile((int) (x + bounds.x) / Tile.TILEWIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT) &&
                !collisionWithTile((int) (x + bounds.x + bounds.width) / Tile.TILEWIDTH, (int) (y + yMove + bounds.y + bounds.height) / Tile.TILEHEIGHT)){
            return animFallRight.getCurrentFrame();
        }*/
        else if(handler.getKeyManager().hit)
        {
            return animHitRight.getCurrentFrame();
        }
        if(isLeft)
            return animStandLeft.getCurrentFrame();
        else
            return animStandRight.getCurrentFrame();    }

    @Override
    public boolean checkEntityCollision(float xOffset, float yOffset)
    {
        for(Entity e : handler.getMap().getEntityManager().getEntities()) {
            if (e.equals(this))
                continue;

            if(e.getCollisionBounds(0f, 0f).intersects(getCollisionBounds(xOffset, yOffset)))
            {
                hurt(damage);
                return true;
            }
        }
        return false;
    }

    public void setPoints(int Point){
        points = Point;
    }

    @Override
    public void addDiamonds(Diamond diamond) {
        addPoints();
    }
}
