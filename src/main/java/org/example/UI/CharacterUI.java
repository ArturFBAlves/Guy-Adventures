package org.example.UI;

import org.example.GamePanel;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

public class CharacterUI extends BaseUI {
    GamePanel gamePanel;
    HashMap<String, String> characterStatus = new HashMap<>();

    CharacterUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        characterStatus.put("LEVEL",String.valueOf(gamePanel.player.level));
        characterStatus.put("EXP. POINTS", String.valueOf(gamePanel.player.exp));
        characterStatus.put("NEXT LV.", String.valueOf(gamePanel.player.nextLevelExp - gamePanel.player.exp));
        characterStatus.put("LIFE",gamePanel.player.life + "/" + gamePanel.player.maxLife);
        characterStatus.put("SPEED", String.valueOf(gamePanel.player.speed));
    }

    @Override
    public void draw(Graphics2D g2d) {
        final int frameX = gamePanel.tileSize * 2;
        final int frameY  = gamePanel.tileSize;
        final int frameWidth  = gamePanel.tileSize * 5;
        final int frameHeight  = gamePanel.tileSize * 5;
        drawSubWindow(frameX, frameY, frameWidth, frameHeight, g2d);

        g2d.setColor(Color.WHITE);
        g2d.setFont(g2d.getFont().deriveFont(32F));

        int textX = frameX + 20;
        int textY = frameY + gamePanel.tileSize;
        final int lineHeight = 48;
        int tailX = frameX + frameWidth - 30;

        for (var entry : characterStatus.entrySet()) {

            String status = entry.getKey();
            String valor = entry.getValue();

            g2d.drawString(status, textX, textY);
            g2d.drawString(
                    valor,
                    getXForAlignToRigthText(valor, tailX, g2d),
                    textY
            );

            textY += lineHeight;
        }
    }


    public int getXForAlignToRigthText(String text, int tailX, Graphics2D g2d ) {
        int length = (int)g2d.getFontMetrics().getStringBounds(text, g2d).getWidth();
        int x = tailX - length;
        return x;
    }
}
