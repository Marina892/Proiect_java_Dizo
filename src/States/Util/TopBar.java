package States.Util;

import Game.Handler;
import States.State;
import Graphics.*;
import UI.UIManager;

import java.awt.*;

public class TopBar extends State {

    protected int points;

    public TopBar(Handler handler) {
        super(handler);
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    @Override
    public void tick() {
        this.points = handler.getMap().getEntityManager().getPlayer().getPoints();
    }

    @Override
    public void render(Graphics g) {

        g.drawImage(Assets.heart, 0, 0, 64, 64, null);

        g.drawImage(Assets.diamond[0], 0, 50, 64, 64, null);
        g.setFont(new Font("Arial", Font.BOLD, 20));
        g.setColor(new Color(255, 200, 0));
        g.drawString(Integer.toString(points), 64, 97);
    }
}
