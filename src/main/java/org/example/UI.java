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
    public String currentDialogue = "";

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
        if (gamePanel.gameState == gamePanel.dialogueState) {
            drawDialogueScreen();
        }

    }

    public void drawPauseScreen() {
        String text = "PAUSADO";
        int x = getXForCenteredText(text), y = gamePanel.screenHeight/2;
        g2d.drawString(text, x, y);
    }


    public void drawDialogueScreen() {
        int x = gamePanel.tileSize*2,
                y = gamePanel.tileSize/2,
                width = gamePanel.screenWidth - (gamePanel.tileSize*4),
                height= gamePanel.screenHeight/3;

        drawSubWindow(x, y, width, height);

        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2d.drawString(currentDialogue, x, y);

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0,0,0, 180);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(4));
        g2d.drawRoundRect(x+10, y+10, width-20, height-20, 20, 20);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }

}
