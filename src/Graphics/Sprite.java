package Graphics;

import java.awt.image.*;

public class Sprite {

    private BufferedImage sheet;

    public Sprite(BufferedImage sheet)
    {
        this.sheet = sheet;
    }

    public BufferedImage crop(int x, int y , int width, int height)
    {
        return sheet.getSubimage(x, y, width, height);
    }
}
