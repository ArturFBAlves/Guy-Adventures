package org.example.Entities;

import org.example.GamePanel;
import java.awt.*;

public class NPC_HighPriest extends Entity {
    public NPC_HighPriest(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 0; // Fica estático ou com pouco movimento
        getImage();

        solidArea = new Rectangle(12, 22, 36, 36);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "A penitência não é para os mortos, Jack.\nÉ para os vivos que recusam esquecer.";
        dialogues[1] = "As portas deste templo fecharam-se\npara quem traz o pecado da culpa no peito.";
        dialogues[2] = "Procuras absolvição onde só existe eco.\nVolta para o teu vazio.";
    }

    public void getImage() {
        up1 = setup("/npc/priest_up_1");
        up2 = setup("/npc/priest_up_2");
        down1 = setup("/npc/priest_down_1");
        down2 = setup("/npc/priest_down_2");
        left1 = setup("/npc/priest_left_1");
        left2 = setup("/npc/priest_left_2");
        right1 = setup("/npc/priest_right_1");
        right2 = setup("/npc/priest_right_2");
    }
}