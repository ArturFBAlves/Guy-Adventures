package org.example.Entities;

import org.example.Entities.Npc.NPC_Archangel;
import org.example.Entities.Npc.NPC_Bandit;
import org.example.Entities.Npc.NPC_HighPriest;
import org.example.Entities.Npc.NPC_Maria;
import org.example.GamePanel;
import org.example.Entities.monster.Devil_Slime;

import java.util.Random;

public class AssetSetter {
    GamePanel gamePanel;

    public  AssetSetter(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setNPC() {

        // Maria
        gamePanel.npc[0] = new NPC_Maria(gamePanel);
        // Sumo Sacerdote
        gamePanel.npc[1] = new NPC_HighPriest(gamePanel);
        // Bandido
        gamePanel.npc[2] = new NPC_Bandit(gamePanel);
        // Arcanjo
        gamePanel.npc[3] = new NPC_Archangel(gamePanel);
    }

    public void setMonster() {
        Random rand = new Random();
        for (int i = 0; i < 3; i++) {
            int posX = rand.nextInt(22, 30);
            int posY = rand.nextInt(17, 30);
            gamePanel.monster[i] = new Devil_Slime(gamePanel);
            gamePanel.monster[i].worldX = gamePanel.tileSize * posX;
            gamePanel.monster[i].worldY = gamePanel.tileSize * posY;
        }
    }
}
