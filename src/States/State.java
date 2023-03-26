package States;

import java.awt.*;
import Game.*;
import UI.UIManager;

public abstract class State {

    private static State currentState = null;

    public  static  void setState(State state)
    {
        state.SetUIManagerForMouseManager(state.getUiManager());
        currentState = state;
    }

    public static  State getState()
    {
        return currentState;
    }
    //Class

    protected Handler handler;

    public State(Handler handler)
    {
        this.handler = handler;
    }

    public void SetUIManagerForMouseManager(UIManager uiManager){
        handler.getMouseManager().setUIManager(uiManager);
    }

    protected abstract UIManager getUiManager();

    public abstract void tick();

    public abstract void render(Graphics g);

    public void DrawText(String string, int x, int y, Graphics g, int size)
    {
        g.setFont(new Font("Arial", Font.BOLD, size));

        g.setColor(Color.WHITE);
        g.drawString(string, x + 3, y + 3);
        g.setColor(Color.BLACK);
        g.drawString(string, x, y);
    }
}
