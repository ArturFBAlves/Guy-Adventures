package org.example.UI;

import org.example.GamePanel;

import java.awt.*;

public class BaseUI {
    GamePanel gamePanel;

    public void drawSubWindow(int x, int y, int width, int height, Graphics2D g2d) {
        Color c = new Color(0, 0, 0, 200);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 10, y + 10, width - 20, height - 20, 25, 25);
    }

    public void draw(Graphics2D g2d) {}

}
