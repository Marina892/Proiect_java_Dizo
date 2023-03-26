package Maps;

import java.awt.*;

import Entities.Character;
import Entities.EntityFactory;
import Entities.EntityManager;
import Entities.Player;
import Exceptions.UnknowTileException;
import States.GameState;
import States.Util.Levels;
import Tiles.*;
import Utils.Utils;
import Game.*;
import Graphics.*;
import Object.*;

import static States.Util.Levels.*;

public class Map {
    private Handler handler;
    private int width, height;  //size of map
    private int spawnX, spawnY;
    private int[][] tiles;
    private int[][] tilesL2;
    //Entities
    private EntityManager entityManager;

    private ObjectManager objectManager;

    private EntityFactory entityFactory;

    public Map(Handler handler, Levels.levels path, Levels.levels path1)
    {
        this.handler = handler;
        entityManager = new EntityManager(handler, new Player(handler, 128, 640));

        if(GameState.currentLevel == levels.level2)
        {
            entityManager.addEntity(new Character(handler, 27*64, 12*64));
        }

        objectManager = new ObjectManager(handler);
        entityFactory = new EntityFactory(handler);

        loadMap(GetLevelWorld(path));
        loadLayer2(GetLevelWorldDiamant(path1));

        //entityManager.getPlayer().setX(spawnX);
        //entityManager.getPlayer().setY(spawnY);
    }

    public void tick()
    {
        entityManager.tick();
        objectManager.tick();
    }

    public void render(Graphics g)
    {
        if(GameState.currentLevel == levels.level1)
            g.drawImage(Assets.backgroung_image, 0, 0, null);
        else if(GameState.currentLevel == levels.level2)
            g.drawImage(Assets.backgroung_level2, 0, 0, null);

        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
                getTile(x, y).render(g, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT);

        //Entities
        entityManager.render(g);

        objectManager.render(g);

    }

    public Tile getTile(int x, int y)
    {
        if(x < 0 || y < 0 || x >= width || y >= height)
            return Tile.tiles[33];

        Tile t = Tile.tiles[tiles[x][y]];
        if(t == null)
            return Tile.tiles[33];
        return t;
    }

    private void loadMap(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);
        spawnX = Utils.parseInt(tokens[2]);
        spawnY = Utils.parseInt(tokens[3]);

        tiles = new int[width][height];
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                try{
                    if(Utils.parseInt(tokens[(x + y*width) + 4]) > 40)
                        throw new UnknowTileException();
                    else
                        tiles[x][y] = Utils.parseInt(tokens[(x + y * width) + 4]);
                }catch (UnknowTileException e){
                    System.err.println(e.getMessage());
                }
            }
    }

    public void loadLayer2(String path)
    {
        String file = Utils.loadFileAsString(path);
        String[] tokens = file.split("\\s+");
        width = Utils.parseInt(tokens[0]);
        height = Utils.parseInt(tokens[1]);

        tilesL2 = new int[width][height];
        for(int y = 0; y < height; y++)
            for(int x = 0; x < width; x++)
            {
                int temp = Utils.parseInt(tokens[(x + y * width) + 2]);
                switch (temp) {
                    case 0:
                        objectManager.addItem(Diamond.diamondv.createNew(x * Tile.TILEHEIGHT, y * Tile.TILEHEIGHT));
                        break;

                    case 1:
                        objectManager.addItem(Diamond.diamondr.createNew(x * Tile.TILEHEIGHT, y * Tile.TILEHEIGHT));
                        break;

                    case 2:
                        objectManager.addItem(Diamond.diamondg.createNew(x * Tile.TILEHEIGHT, y * Tile.TILEHEIGHT));
                        break;

                    case 3:
                        objectManager.addItem(Diamond.diamondrs.createNew(x * Tile.TILEHEIGHT, y * Tile.TILEHEIGHT));
                        break;

                    case 4:
                        objectManager.addItem(Diamond.diamondm.createNew(x * Tile.TILEHEIGHT, y * Tile.TILEHEIGHT));
                        break;
                }
                entityManager.addEntity(entityFactory.Produce(temp, x * Tile.TILEWIDTH, y * Tile.TILEHEIGHT));
                tilesL2[x][y] = temp;
            }
    }

    public void setLevel(String path1, String path2) {
        entityManager = new EntityManager(handler, new Player(handler, 128, 640));
        objectManager = new ObjectManager(handler);
        entityFactory = new EntityFactory(handler);

        if(GameState.currentLevel == levels.level2)
        {
            entityManager.addEntity(new Character(handler, 28*64, 12*64));
        }

        loadMap(path1);
        loadLayer2(path2);

        //entityManager.addEntity(new Player(handler, 100, 100));

        entityManager.getPlayer().setX(spawnX);
        entityManager.getPlayer().setY(spawnY);
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

}
