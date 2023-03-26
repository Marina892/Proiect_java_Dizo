package Game;

import Entities.Player;
import Input.KeyManager;
import Input.MouseManager;
import Maps.*;

public class Handler {

    private Game game;
    private Map map;
    private Player player;

    public Player getPlayer() {
        return player;
    }

    public Handler(Game game)
    {
        this.game = game;
    }

    public KeyManager getKeyManager()
    {
        return game.getKeyManager();
    }

    public MouseManager getMouseManager()
    {
        return game.getMouseManager();
    }

    public int getWidth()
    {
        return game.getWidth();
    }

    public int getHeight()
    {
        return game.getHeight();
    }

    public Game getGame() {
        return game;
    }

    public Map getMap() {
        return map;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public void setMap(Map map) {
        this.map = map;
    }

}
