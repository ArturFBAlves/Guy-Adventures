package org.example.Entities;

import org.example.GamePanel;
import java.awt.*;

public class NPC_Archangel extends Entity {
    public NPC_Archangel(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 0;
        getImage();

        spriteWidth = 86;
        spriteHeight = 84;

        solidArea = new Rectangle(12, 22, 36, 36);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "A tua dor é insignificante perante a ordem deste reino... \nJack.";
        dialogues[1] = "As memórias desvanecem-se por uma razão:\npara que o ciclo não sangre outra vez.";
        dialogues[2] = "Passa por mim se tiveres coragem de enfrentar\no vazio absoluto que criaste.";
    }

    public void getImage() {
        up1 = setup("/npc/arcanjo2", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/npc/arcanjo2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/npc/arcanjo1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/npc/arcanjo1", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/npc/arcanjo4", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/npc/arcanjo4", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/npc/arcanjo3", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/npc/arcanjo3", gamePanel.tileSize, gamePanel.tileSize);
    }
}