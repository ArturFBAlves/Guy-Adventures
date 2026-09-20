package org.example;

import org.example.object.OBJ_Heart;
import org.example.object.SuperObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class UI {
    GamePanel gamePanel;
    Graphics2D g2d;
    Font arial_30;

    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();

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

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2d) {
        this.g2d = g2d;
        g2d.setFont(arial_30);
        g2d.setColor(Color.WHITE);

        if (gamePanel.gameState == gamePanel.playState) {
            drawPlayerLife();
            drawMessage();
        }
        if (gamePanel.gameState == gamePanel.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }
        if (gamePanel.gameState == gamePanel.dialogueState) {
            drawPlayerLife();
            drawDialogueScreen();
        }
        if (gamePanel.gameState == gamePanel.battleState) {
            drawPlayerLife();
            drawBattleScreen();
        }
        if (gamePanel.gameState == gamePanel.characterState) {
            drawCharacterScreen();
        }
    }

    public void drawCharacterScreen() {
        final int frameX = gamePanel.tileSize * 2;
        final int frameY  = gamePanel.tileSize;
        final int frameWidth  = gamePanel.tileSize * 5;
        final int frameHeight  = gamePanel.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 48;
        String value;
        int tailX = frameX + frameWidth - 30;

        g2d.drawString("LEVEL", textX, textY);
        value = String.valueOf(gamePanel.player.level);
        g2d.drawString(value, getXForAlignToRigthText(value, tailX), textY);
        textY += lineHeight;

        g2d.drawString("EXP. POINTS", textX, textY);
        value = String.valueOf(gamePanel.player.exp);
        g2d.drawString(value, getXForAlignToRigthText(value, tailX), textY);
        textY += lineHeight;

        g2d.drawString("NEXT LV.", textX, textY);
        value = String.valueOf(gamePanel.player.nextLevelExp - gamePanel.player.exp);
        g2d.drawString(value, getXForAlignToRigthText(value, tailX), textY);
        textY += lineHeight;

        g2d.drawString("LIFE", textX, textY);
        value = gamePanel.player.life + "/" + gamePanel.player.maxLife;
        g2d.drawString(value, getXForAlignToRigthText(value, tailX), textY);
        textY += lineHeight;

        g2d.drawString("SPEED", textX, textY);
        value = String.valueOf(gamePanel.player.speed);
        g2d.drawString(value, getXForAlignToRigthText(value, tailX), textY);
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

    public void drawMessage() {
        int messageX = gamePanel.tileSize;
        int messageY = gamePanel.tileSize * 4;

        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 32F));

        for(int i = 0; i < message.size(); i++) {
            if(message.get(i) != null) {

                g2d.setColor(Color.BLACK);
                g2d.drawString(message.get(i), messageX + 2, messageY + 2);
                g2d.setColor(Color.WHITE);
                g2d.drawString(message.get(i), messageX, messageY);

                int counter = messageCounter.get(i) + 1;
                messageCounter.set(i, counter);
                messageY += 50;

                if (messageCounter.get(i) > 180) {
                    message.remove(i);
                    messageCounter.remove(i);
                }
            }
        }
    }

    public void drawPauseScreen() {
        String text = "PAUSADO";
        int x = getXForCenteredText(text), y = gamePanel.screenHeight/2;
        g2d.drawString(text, x, y);
    }

    public void drawDialogueScreen() {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.screenHeight / 3;

        drawSubWindow(x, y, width, height);

        x += gamePanel.tileSize;
        y += gamePanel.tileSize;
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 28));

        if (currentDialogue != null) {
            for (String line : currentDialogue.split("\n")) {
                g2d.drawString(line, x, y);
                y += 40;
            }
        }
    }

    // TELA DE BATALHA COM O COMBO ATUAL
    public void drawBattleScreen() {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 6;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height);

        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(Font.BOLD, 24));

        x += gamePanel.tileSize;
        y += gamePanel.tileSize;

        g2d.drawString("Batalha contra o Bandido!", x, y);
        
        // Mostra em tempo real as teclas j, h, k que o jogador está a pressionar
        g2d.drawString("Teu Combo: " + gamePanel.playerCombo, x, y + 45);
        
        g2d.setFont(g2d.getFont().deriveFont(Font.PLAIN, 20));
        g2d.drawString("Usa as teclas [J], [H], [K] e prime [ENTER] para atacar.", x, y + 90);
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

    public int getXForAlignToRigthText(String text, int tailX ) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = tailX - length;
        return x;
    }
}