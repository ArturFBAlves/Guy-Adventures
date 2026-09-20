package org.example.Entities;

import org.example.GamePanel;

import java.awt.*;
import java.util.Random;

public class Maria extends Entity {
    
    public Maria(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 1;
        getImage();
        spriteWidth = 86;
        spriteHeight = 84;


        solidArea = new Rectangle(spriteWidth/4, spriteHeight/4, (int)spriteWidth/2, spriteHeight/2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Você... você voltou para casa. \nA porta estava aberta, não estava?";
        dialogues[1] = "Ela ouviu o chamado, Jack. Algumas coisas... \ndeveriam permanecer enterradas.";
        dialogues[2] = "Se continuar a procurar... \nvai descobrir que algumas pessoas preferem \nperder alguém do que encarar a verdade.";
        dialogues[3] = "Já é tarde demais para salvá-la. \nVá embora antes que seja tarde para si também.";
    }

    public void getImage() {
        up1 = setup("/npc/MariaC", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/npc/MariaC", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/npc/MariaB", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/npc/MariaB", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/npc/MariaE", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/npc/MariaE", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/npc/MariaD", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/npc/MariaD", gamePanel.tileSize, gamePanel.tileSize);
    }

    @Override
    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

    @Override
    public void speak() {
        super.speak();
    }
}