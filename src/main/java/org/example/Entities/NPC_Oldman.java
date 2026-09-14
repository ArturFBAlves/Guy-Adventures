package org.example.Entities;

import org.example.GamePanel;

import java.awt.*;
import java.util.Random;


public class NPC_Oldman extends Entity{
    public NPC_Oldman(GamePanel gamePanel) {
        super(gamePanel);
        direction = "down";
        speed = 1;
        getImage();

        solidArea = new Rectangle(
                12,
                22,
                36,
                36
        );
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        setDialogue();
    }

    public void setDialogue() {
        dialogues[0] = "Olá viajante";
        dialogues[1] = "Este é um novo dialogo";
        dialogues[2] = "testetesteteste";
        dialogues[3] = "blablabla";
    }

    public void getImage() {
        up1 = setup("/npc/oldman_up_1");
        up2 = setup("/npc/oldman_up_2");
        down1 = setup("/npc/oldman_down_1");
        down2 = setup("/npc/oldman_down_2");
        left1 = setup("/npc/oldman_left_1");
        left2 = setup("/npc/oldman_left_2");
        right1 = setup("/npc/oldman_right_1");
        right2 = setup("/npc/oldman_right_2");
    }

    public void setAction() {
        actionLockCounter++;
        if(actionLockCounter == 120){
            Random random = new Random();
            int i = random.nextInt(100)+1;
            if (i<=25){
                direction = "up";
            }
            if (i>25 && i<= 50){
                direction = "down";
            }
            if (i>50 && i<= 75){
                direction = "left";
            }
            if (i>75){
                direction = "right";
            }

            actionLockCounter = 0;
        }
    }

    @Override
    public void speak() {
        if (dialogues[dialogueIndex] == null ){
            dialogueIndex = 0;
        }
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;
    }

}
