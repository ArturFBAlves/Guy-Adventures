package org.example.Entities.Npc;

import org.example.Entities.Entity;
import org.example.Enums.State;
import org.example.GamePanel;
import java.awt.*;
import java.util.Random;

public class NPC_Bandit extends Entity {

    // Guarda a posição inicial para ele não se afastar muito
    public int startX;
    public int startY;

    public NPC_Bandit(GamePanel gamePanel) {
        super(gamePanel);

        spriteWidth = 86;
        spriteHeight = 84;
        worldX = gamePanel.tileSize * 22;
        worldY = gamePanel.tileSize * 8;
        direction = "left";
        speed = 1; // Velocidade de movimento lenta para a patrulha

        maxLife = 6;
        life = maxLife;
        exp = 20;

        solidArea = new Rectangle(25, 16, 32, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
    }

    public void getImage() {
        up1 = setup("/NPC/bandit4", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/NPC/bandit4", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/NPC/bandit1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/NPC/bandit2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/NPC/bandit3", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/NPC/bandit3", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/NPC/bandit2", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/NPC/bandit2", gamePanel.tileSize, gamePanel.tileSize);
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
            gamePanel.gameState = State.battleState; // Entra na batalha
            return;
        }
        
        gamePanel.ui.dialogueUI.currentDialogue = dialogues[dialogueIndex];
        dialogueIndex++;

        switch(gamePanel.player.direction) {
            case "up": direction = "down"; break;
            case "down": direction = "up"; break;
            case "left": direction = "right"; break;
            case "right": direction = "left"; break;
        }
    }
}