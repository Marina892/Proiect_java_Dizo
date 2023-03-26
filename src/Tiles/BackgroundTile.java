package Tiles;

import Graphics.Assets;

import java.awt.image.BufferedImage;

public class BackgroundTile extends Tile{
    public BackgroundTile(int id)
    {
        super(getAssets(id), id);
    }

    @Override
    public boolean isSolid()
    {
        return false;
    }

    private static BufferedImage getAssets(int id)
    {
        int [] temp = new int [8];
        int t = 0;
        for (int i = 28; i < 36; i++)
        {
            temp[t++] = i;
        }

        int val = 0;
        for(int i =0; i < 8; i++) {
            if (temp[val++] == id)
                return Assets.backgound[i];
        }

        return Assets.backgound[1];
    }

}
