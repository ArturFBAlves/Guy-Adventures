package org.example.UI;

import org.example.Enums.State;
import org.example.GamePanel;
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
    ArrayList<String> message = new ArrayList<String>();
    ArrayList<Integer> messageCounter = new ArrayList<Integer>();
    CharacterUI characterUI;
    PauseUI  pauseUI;
    BattleUI  battleUI;
    public DialogueUI dialogueUI;

    public UI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        arial_30 = new Font("Arial", Font.BOLD, 30);

        SuperObject heart = new OBJ_Heart(gamePanel);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;
        characterUI = new CharacterUI(gamePanel);
        pauseUI = new PauseUI(gamePanel);
        battleUI = new BattleUI(gamePanel);
        dialogueUI = new DialogueUI(gamePanel);
    }

    public void addMessage(String text) {
        message.add(text);
        messageCounter.add(0);
    }

    public void draw(Graphics2D g2d) {
        g2d.setFont(arial_30);
        g2d.setColor(Color.WHITE);
        drawPlayerLife(g2d);

        if (gamePanel.gameState == State.playState) {
            drawMessage(g2d);
        }
        if (gamePanel.gameState == State.pauseState) {
            pauseUI.draw(g2d);
        }
        if (gamePanel.gameState == State.dialogueState) {
            dialogueUI.draw(g2d);
        }
        if (gamePanel.gameState == State.battleState) {
            battleUI.draw(g2d);
        }
        if (gamePanel.gameState == State.characterState) {
            characterUI.draw(g2d);
        }
    }



    public void drawPlayerLife(Graphics2D g2d) {
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

    public void drawMessage(Graphics2D g2d) {
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
}