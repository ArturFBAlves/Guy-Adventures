package org.example.UI;

import org.example.GamePanel;

import java.awt.*;

public class DialogueUI extends BaseUI {
    GamePanel gamePanel;
    public String currentDialogue = "";

    public DialogueUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize / 2;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.screenHeight / 3;

        drawSubWindow(x, y, width, height, g2d);

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
}
