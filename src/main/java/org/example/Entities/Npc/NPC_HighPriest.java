package org.example.Entities.Npc;

import org.example.Entities.Entity;
import org.example.GamePanel;
import java.awt.*;

public class NPC_HighPriest extends Entity {
    public NPC_HighPriest(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 0; // Fica estático ou com pouco movimento

        worldX = gamePanel.tileSize * 28;
        worldY = gamePanel.tileSize * 17;

        spriteWidth = 86;
        spriteHeight = 84;

        solidArea = new Rectangle(spriteWidth/4, spriteHeight/4, (int)spriteWidth/2, spriteHeight/2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDialogue();
        getImage();
    }

    public void setDialogue() {
        dialogues[0] = "A penitência não é para os mortos, Jack.\nÉ para os vivos que recusam esquecer.";
        dialogues[1] = "As portas deste templo fecharam-se\npara quem traz o pecado da culpa no peito.";
        dialogues[2] = "Procuras absolvição onde só existe eco.\nVolta para o teu vazio.";
    }

    public void getImage() {
        up1 = setup("/npc/SSUP", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/npc/SSUP", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/npc/SSD", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/npc/SSD", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/npc/SSL", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/npc/SSL", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/npc/SSR", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/npc/SSR", gamePanel.tileSize, gamePanel.tileSize);
    }
}