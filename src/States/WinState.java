package States;

import Game.Handler;
import UI.UIButton;
import UI.UIManager;

import java.awt.*;

import Graphics.*;


public class WinState extends State{

    private UIManager uiManager;

    public WinState(Handler handler) {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 100, 600, 200, 55, () -> {
            //isUIManagerActive = false;
            //handler.getGame().menuState.setUIManagerActive(true);
            State.setState(handler.getGame().menuState);
        }, "Meniu"));
    }

    @Override
    protected UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick() {
        uiManager.tick();
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(Assets.backgroung_win, 0, 0, null);

        uiManager.render(g);

        DrawText("Ai castigat!", handler.getWidth()/2 - 350, handler.getHeight()/2 - 50, g, 150);
    }
}
