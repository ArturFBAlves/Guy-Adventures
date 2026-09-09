package org.example.Entities;

import org.example.GamePanel;

public class AssetSetter {
    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setNPC() {
        gamePanel.npc[0] = new NPC_Oldman(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize*21;
        gamePanel.npc[0].worldY = gamePanel.tileSize*21;
    }
}
