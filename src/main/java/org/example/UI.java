package org.example;

import java.awt.*;
import java.text.DecimalFormat;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_30;

    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;

    double playTime;
    DecimalFormat df = new DecimalFormat("#0.00");

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_30 = new Font("Arial", Font.BOLD, 30);
    }

    public void showMessage(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(arial_30);
        g2d.setColor(Color.WHITE);

        if (gamePanel.gameState == gamePanel.playState) {

        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPauseScreen();
        }

    }

    public void drawPauseScreen() {
        String text = "PAUSADO";
        int x = getXForCenteredText(text), y = gamePanel.screenHeight/2;
        g2d.drawString(text, x, y);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }

}
