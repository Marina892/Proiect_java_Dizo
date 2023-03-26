package States;

import Graphics.*;
import java.awt.*;
import Game.Handler;
import UI.ClickListener;
import UI.UIButton;
import UI.UIImageButton;
import UI.UIManager;
import States.GameState.*;

public class MenuState extends State{

    private UIManager uiManager;
    private int ajutor = 0;

    public MenuState(Handler handler)
    {
        super(handler);
        uiManager = new UIManager(handler);
        handler.getMouseManager().setUiManager(uiManager);

        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 90, 450, 170, 55, () -> {
            //handler.getMouseManager().setUiManager(null);;
            State.setState(handler.getGame().gameState);

        }, "Start"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 105, 530, 200, 55, () -> {
            ajutor = 1;
        },"Ajutor"));
        uiManager.addObject(new UIButton(handler.getGame().getWidth()/2 - 100, 615, 180, 55, () -> {
            System.exit(0);
        }, "Iesire"));
    }

    @Override
    protected UIManager getUiManager() {
        return uiManager;
    }

    @Override
    public void tick()
    {
        uiManager.tick();
    }
    
    @Override
    public void render(Graphics g)
    {
        g.drawImage(Assets.backgroung_menu, 0, 0, null);
        g.setFont(new Font("Arial", Font.BOLD | Font.ITALIC, 40));
        if(ajutor == 1) {
            DrawText("Controale: ", 30, 450, g, 50);
            DrawText("- W : Saritura: ", 30, 500, g, 50);
            DrawText("- A/D : Mers spre dreapta/stanga ", 30, 550, g, 50);
            DrawText("- SPACE : Atac cu piciorul", 30, 600, g, 50);

        }
        uiManager.render(g);

    }
}
