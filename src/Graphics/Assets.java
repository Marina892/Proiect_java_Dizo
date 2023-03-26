package Graphics;

import java.awt.image.*;

public class Assets {

    private static final int width = 64, height = 64;
    public static BufferedImage backgroung_image;
    public static BufferedImage backgroung_menu;
    public static BufferedImage backgroung_win;
    public static BufferedImage backgroung_lose;
    public static BufferedImage backgroung_level2;

    //animation
    public static BufferedImage[] player_right = new BufferedImage[6];
    public static BufferedImage[] player_left = new BufferedImage[6];
    public static BufferedImage[] player_stand_right = new BufferedImage[4];
    public static BufferedImage[] player_stand_left = new BufferedImage[4];
    public static BufferedImage[] player_jump_right = new BufferedImage[9];
    public static BufferedImage[] player_fall = new BufferedImage[8];
    public static BufferedImage[] player_hit_right = new BufferedImage[4];
    public static BufferedImage[] player_die_right = new BufferedImage[10];

    //animation second character
    public static BufferedImage[] character_stand_left = new BufferedImage[4];

    //tiles
    public static BufferedImage [][] tc = new BufferedImage[4][7];
    public static BufferedImage [] backgound = new BufferedImage[8];
    public static BufferedImage [] diamond = new BufferedImage[5];
    public static BufferedImage rock;
    public static BufferedImage lava;

    public static BufferedImage heart;

    public static void init()
    {
        backgroung_image = ImageLoader.loadImage("/textures/fundal_nivel_1.png");
        backgroung_menu = ImageLoader.loadImage("/textures/ImagineMeniu.png");
        backgroung_win = ImageLoader.loadImage("/textures/WinBackground.png");
        backgroung_lose = ImageLoader.loadImage("/textures/LoseBackground.jpg");
        backgroung_level2 = ImageLoader.loadImage("/textures/backgroundLevel2.png");

        //animation
        Sprite sheet = new Sprite(ImageLoader.loadImage("/textures/DinoV_1.png"));
        for(int i =0; i < player_right.length; i++)
            player_right[i] = sheet.crop(width * i, 128, width, height);

        for(int i =0; i < player_left.length; i++)
            player_left[i] = sheet.crop(width * i, 576, width, height);

        for(int i =0; i < player_stand_right.length; i++)
            player_stand_right[i] = sheet.crop(width * i, 0, width, height);

        for(int i =0; i < player_stand_left.length; i++)
            player_stand_left[i] = sheet.crop(width * i, 448, width, height);

        for(int i =0; i < player_jump_right.length; i++)
            player_jump_right[i] = sheet.crop(width * i, 320, width, height);

        for(int i =0; i < player_fall.length; i++)
            player_fall[i] = sheet.crop(width * i, 384, width, height);

        for(int i =0; i < player_hit_right.length; i++)
            player_hit_right[i] = sheet.crop(width * i, 256, width, height);

        for(int i =0; i < player_die_right.length; i++)
            player_die_right[i] = sheet.crop(width * i, 64, width, height);

        //second character
        Sprite sheet5 = new Sprite(ImageLoader.loadImage("/textures/DinoG.png"));
        for(int i =0; i < character_stand_left.length; i++)
            character_stand_left[i] = sheet5.crop(width * i, 64, width, height);

        //tiles
        Sprite sheet1 = new Sprite(ImageLoader.loadImage("/textures/ColisionTile.png"));
        for(int i = 0; i < tc.length; i++)
            for(int j = 0; j < tc[0].length; j++)
                tc[i][j] = sheet1.crop(width * j, height * i, width, height);

        Sprite sheet2 = new Sprite(ImageLoader.loadImage("/textures/GameTileset.png"));
        for(int i = 0; i < backgound.length; i++)
            backgound[i] = sheet2.crop(width * i, 0, width, height);

        for(int i = 0; i < diamond.length; i++)
            diamond[i] = sheet2.crop(width * i, height, width, height);

        rock = sheet2.crop(width * 5, height, width, height);
        lava = sheet2.crop(width * 7, height, width, height);

        heart = sheet2.crop(width * 6, height, width, height);

    }
}
