package States;
import Entities.Entity;

import java.awt.*;

import Game.*;
import Maps.*;
import States.Util.TopBar;
import UI.UIManager;

import static States.Util.Levels.*;
import static States.Util.Levels.levels.*;

public class GameState extends State
{
    private Map map;
    private TopBar topBar;
    public static levels currentLevel = level1, currentLevelDMG = level3;

    public GameState(Handler handler)
    {
        super(handler);
        map = new Map(handler, level1, level3);
        handler.setMap(map);

        this.topBar = new TopBar(handler);
    }

    public void StartNewGame()
    {
        currentLevel = level1;
        currentLevelDMG = level3;

        String path1 = GetLevelWorld(currentLevel);
        String path2 = GetLevelWorldDiamant(currentLevelDMG);

        Entity.points = Entity.POINTS;

        map.setLevel(path1, path2);
    }

    public void startNextLevel()
    {
        currentLevel = nextLevel(currentLevel);
        currentLevelDMG = nextLevelDiamant(currentLevelDMG);

        String path1 = GetLevelWorld(currentLevel);
        String path2 = GetLevelWorldDiamant(currentLevelDMG);

        map.setLevel(path1, path2);
    }

    @Override
    protected UIManager getUiManager() {
        return null;
    }

    @Override
    public void tick()
    {
        if(currentLevel == level1 && handler.getPlayer().getPoints() == 10)
        {
            //handler.getPlayer().getX() == 29*64 && handler.getPlayer().getY() == 8*64
            startNextLevel();
            State.setState(handler.getGame().gameState);
        }
        else if(currentLevel == level2 && handler.getPlayer().getPoints() == 26)
        {
            //handler.getPlayer().getX() == 27*64 && handler.getPlayer().getY() == 12*64
            State.setState(handler.getGame().winState);
        }

        map.tick();
        topBar.tick();
    }

    @Override
    public void render(Graphics g)
    {
        map.render(g);
        topBar.render(g);
    }

}
