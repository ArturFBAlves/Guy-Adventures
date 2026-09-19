package org.example;

import org.example.object.OBJ_Heart;
import org.example.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_30;

    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int messageCounter = 0;
    public boolean gameFinished = false;
    public String currentDialogue = "";

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_30 = new Font("Arial", Font.BOLD, 30);

        SuperObject heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
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
            drawPlayerLife();
        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gamePanel.gameState == gamePanel.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
    }

    public void drawPlayerLife() {
        int x = gamePanel.tileSize/2;
        int y = gamePanel.tileSize/2;
        int i = 0;

        while(i < gamePanel.player.maxLife/2) {
            g2d.drawImage(heart_blank, x, y, null);
            i++;
            x += gamePanel.tileSize;
        }

        x = gamePanel.tileSize/2;
        y = gamePanel.tileSize/2;
        i = 0;

        while (i < gamePanel.player.life) {
            g2d.drawImage(heart_half, x, y, null);
            i++;
            if (i < gamePanel.player.life) {
                g2d.drawImage(heart_full, x, y, null);
            }
            i++;
            x += gamePanel.tileSize;
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSADO";
        int x = getXForCenteredText(text), y = gamePanel.screenHeight/2;
        g2d.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        // Janela de Diálogo
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.screenHeight / 3;

        drawSubWindow(x, y, width, height);

        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 28));

        // Se houver texto definido, desenha linha por linha quebrando com \n
        if (currentDialogue != null) {
            for (String line : currentDialogue.split("\n")) {
                g2d.drawString(line, x, y);
                y += 40;
            }
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(0, 0, 0, 200);
        g2d.setColor(c);
        g2d.fillRoundRect(x, y, width, height, 35, 35);

        c = new Color(255, 255, 255);
        g2d.setColor(c);
        g2d.setStroke(new BasicStroke(5));
        g2d.drawRoundRect(x + 10, y + 10, width - 20, height - 20, 25, 25);
    }

    public int getXForCenteredText(String text) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = gamePanel.screenWidth/2 - length/2;
        return x;
    }
}