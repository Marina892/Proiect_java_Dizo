package UI;

import java.awt.*;

public class UIButton extends UIObject{

    private ClickListener clicker;
    private String text;
    private Rectangle rectangle;

    public UIButton(float x, float y, int width, int height, ClickListener clicker, String text) {
        super(x, y, width, height);
        this.clicker = clicker;
        this.text = text;
    }

    @Override
    public void tick() {

    }

    @Override
    public void render(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;

        g.setFont((new Font("Arial", Font.BOLD, 60)));
        if(hovering)
        {
            graphics2D.draw(bounds);
            g.setColor(Color.BLACK);
            g.drawString(text, bounds.x + 15, bounds.y + 50);
        }else{
            g.setColor(Color.WHITE);
            g.drawString(text, bounds.x + 15, bounds.y + 50);
            graphics2D.draw(bounds);
        }
    }

    @Override
    public void onClick() {
        clicker.onClick();
    }
}
