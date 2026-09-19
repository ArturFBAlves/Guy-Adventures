package org.example.Entities;

import org.example.GamePanel;
import java.awt.*;
import java.util.Random;

public class NPC_Bandit extends Entity {

    // Guarda a posição inicial para ele não se afastar muito
    public int startX;
    public int startY;

    public NPC_Bandit(GamePanel gamePanel) {
        super(gamePanel);

        direction = "left";
        speed = 1; // Velocidade de movimento lenta para a patrulha

        maxLife = 6;
        life = maxLife;

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/NPC/bandit_up_1");
        up2 = setup("/NPC/bandit_up_2");
        down1 = setup("/NPC/bandit_down_1");
        down2 = setup("/NPC/bandit_down_2");
        left1 = setup("/NPC/bandit_left_1");
        left2 = setup("/NPC/bandit_left_2");
        right1 = setup("/NPC/bandit_right_1");
        right2 = setup("/NPC/bandit_right_2");
    }

    public void setDialogue() {
        dialogues[0] = "Parado aí, aventureiro!\nEsta ponte está sob o meu domínio.";
        dialogues[1] = "Se queres passar, terás de me derrotar!";
    }

    @Override
    public void setAction() {
        // Se a posição inicial ainda não foi guardada, guarda agora
        if (startX == 0 && startY == 0) {
            startX = worldX;
            startY = worldY;
        }

        actionLockCounter++;
        
        // A cada 90 frames (~1.5 segundos), muda ou mantém a lógica de movimento
        if (actionLockCounter == 90) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;

            if (i <= 50) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }

        // Limita o espaço de patrulha para ele andar apenas num curto espaço (ex: 3 tiles para cada lado)
        int patrolLimit = gamePanel.tileSize * 3;
        if (worldX < startX - patrolLimit) {
            direction = "right";
        }
        if (worldX > startX + patrolLimit) {
            direction = "left";
        }
    }

    @Override
    public void speak() {
        if (dialogues[dialogueIndex] == null) {
            dialogueIndex = 0;
            gamePanel.gameState = gamePanel.battleState; // Entra na batalha
            return;
        }
        
        gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gamePanel.player.direction) {
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }
}