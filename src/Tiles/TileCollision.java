package Tiles;

import Graphics.Assets;

import java.awt.image.BufferedImage;

public class TileCollision extends Tile
{
    public TileCollision(int id)
    {
        super(getAssets(id), id);
    }

    @Override
    public boolean isSolid(){
        return true;
    }

    private static BufferedImage getAssets(int id)
    {
        int [] temp = new int [28];
        int t = 0;

        for (int i = 0; i < 28; i++)
        {
            temp[t++] = i;
        }

        int val = 0;
        for(int i = 0; i < 4; i++)
            for(int j = 0; j < 7; j++)
                if(temp[val++] == id)
                    return Assets.tc[i][j];

        return Assets.tc[2][1];
    }
}
