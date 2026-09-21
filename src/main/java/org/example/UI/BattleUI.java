package org.example.UI;

import org.example.GamePanel;

import java.awt.*;

public class BattleUI extends BaseUI {
    GamePanel gamePanel;

    BattleUI(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void draw(Graphics2D g2d) {
        int x = gamePanel.tileSize * 2;
        int y = gamePanel.tileSize * 6;
        int width = gamePanel.screenWidth - (gamePanel.tileSize * 4);
        int height = gamePanel.tileSize * 4;

        drawSubWindow(x, y, width, height, g2d);

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
}
