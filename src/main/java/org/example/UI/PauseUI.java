package org.example.UI;

import org.example.GamePanel;

import java.awt.*;

public class PauseUI extends BaseUI {
    GamePanel gamePanel;

    PauseUI (GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void draw(Graphics2D g2d) {
        String text = "PAUSADO";
        int x = getXForCenteredText(text, g2d), y = gamePanel.screenHeight/2;
        g2d.drawString(text, x, y);
    }

    public int getXForCenteredText(String text, Graphics2D g2d) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }

}
