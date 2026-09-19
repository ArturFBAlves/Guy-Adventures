package org.example.Entities;

import org.example.GamePanel;

public class AssetSetter {
    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setNPC() {
        gamePanel.npc[0] = new Maria(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize*21;
        gamePanel.npc[0].worldY = gamePanel.tileSize*21;
        
        // Sumo Sacerdote
        gamePanel.npc[1] = new NPC_HighPriest(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 25;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 15;

        // Bandido
        gamePanel.npc[2] = new NPC_Bandit(gamePanel);
        gamePanel.npc[2].worldX = gamePanel.tileSize * 18;
        gamePanel.npc[2].worldY = gamePanel.tileSize * 28;

        // Arcanjo
        gamePanel.npc[3] = new NPC_Archangel(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 30;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 10;
    }
}
