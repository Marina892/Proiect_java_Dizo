package Game;

import javax.swing.*;
import java.awt.*;

public class Display {

    private JFrame frame;
    private Canvas canvas;

    private String Title;
    private int width, height;

    private static volatile Display instance;

    public Display(String Title, int width, int height)
    {
        this.Title = Title;
        this.width = width;
        this.height = height;

        createDisplay();
    }


    public static Display getInstance(String title, int width, int height) {
        if(instance == null)
        {
            synchronized (Display.class)
            {
                if (instance == null)
                {
                    instance = new Display(title, width, height);
                }
            }
        }
        return instance;
    }


    private  void createDisplay()
        {
            frame = new JFrame(Title);
            frame.setSize(width, height);   //seteaza dimensiunea ecranului
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    //pentru inchiderea jocului, fara el se poate inchide doar ecranul, iar jocul inca sa ruleze
            frame.setVisible(true);     //pentru ca ecranul sa fie vizibil, JFrame face ecranul initial "invizibil"
            frame.setResizable(false);   //pentru a nu putea modifica marimea ecranului
            frame.setLocationRelativeTo(null);  //pentru a aparea in mijlocul ecranului

            canvas = new Canvas();
            canvas.setPreferredSize(new Dimension(width, height));
            canvas.setMaximumSize(new Dimension(width, height));
            canvas.setMinimumSize(new Dimension(width, height));
            canvas.setFocusable(false);

            frame.add(canvas);  //imi adauga imaginea la ecranul meu
            frame.pack();   //fara el e posibil sa nu incapa tot in ecran

        }

        public Canvas getCanvas()
        {
            return canvas;
        }

        public JFrame getFrame()
        {
            return frame;
        }

}
