package org.example.Entities;

import org.example.GamePanel;
import org.example.monster.Devil_Slime;

public class AssetSetter {
    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setNPC() {
        gamePanel.npc[0] = new Maria(gamePanel);
        gamePanel.npc[0].worldX = gamePanel.tileSize*24;
        gamePanel.npc[0].worldY = gamePanel.tileSize*24;
        
        // Sumo Sacerdote
        gamePanel.npc[1] = new NPC_HighPriest(gamePanel);
        gamePanel.npc[1].worldX = gamePanel.tileSize * 28;
        gamePanel.npc[1].worldY = gamePanel.tileSize * 17;

        // Bandido
        gamePanel.npc[2] = new NPC_Bandit(gamePanel);
        gamePanel.npc[2].worldX = gamePanel.tileSize * 22;
        gamePanel.npc[2].worldY = gamePanel.tileSize * 8;

        // Arcanjo
        gamePanel.npc[3] = new NPC_Archangel(gamePanel);
        gamePanel.npc[3].worldX = gamePanel.tileSize * 30;
        gamePanel.npc[3].worldY = gamePanel.tileSize * 10;
    }

    public void setMonster() {
        gamePanel.monster[0] = new Devil_Slime(gamePanel);
        gamePanel.monster[0].worldX = gamePanel.tileSize * 26;
        gamePanel.monster[0].worldY = gamePanel.tileSize * 28;

        gamePanel.monster[1] = new Devil_Slime(gamePanel);
        gamePanel.monster[1].worldX = gamePanel.tileSize * 29;
        gamePanel.monster[1].worldY = gamePanel.tileSize * 17;

        gamePanel.monster[2] = new Devil_Slime(gamePanel);
        gamePanel.monster[2].worldX = gamePanel.tileSize * 22;
        gamePanel.monster[2].worldY = gamePanel.tileSize * 13;
    }
}
