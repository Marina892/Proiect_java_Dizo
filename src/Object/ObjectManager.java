package Object;

import Game.Handler;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

public class ObjectManager {

    private Handler handler;
    private ArrayList<Diamond> diamonds;

    public ObjectManager(Handler handler)
    {
        this.handler = handler;
        this.diamonds = new ArrayList<Diamond>();
    }

    public void tick()
    {
        Iterator<Diamond> iterator = diamonds.iterator();
        while(iterator.hasNext())
        {
            Diamond i = iterator.next();
            i.tick();
            if(i.isPickedUp())
                iterator.remove();
        }
    }

    public void render(Graphics g)
    {
        for(Diamond d : diamonds)
        {
            d.render(g);
        }
    }

    public void addItem(Diamond d)
    {
        d.setHandler(handler);
        diamonds.add(d);
    }

    public Handler getHandler() {
        return handler;
    }

    public void setHandler(Handler handler) {
        this.handler = handler;
    }

    public ArrayList<Diamond> getDiamonds() {
        return diamonds;
    }

    public void setDiamonds(ArrayList<Diamond> diamonds) {
        this.diamonds = diamonds;
    }
}
