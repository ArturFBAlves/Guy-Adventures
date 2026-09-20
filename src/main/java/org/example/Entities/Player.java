package org.example.Entities;

import org.example.GamePanel;
import org.example.KeyHandler;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    KeyHandler keyHandler;
    public int screenX;
    public int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        this.screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        this.screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;


        spriteWidth = 86;
        spriteHeight = 84;

        solidArea = new Rectangle(spriteWidth/3, 18, 32, 68);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 23;
        speed = 4;
        direction = "down";
        maxLife = 6;
        life = maxLife;
        level = 1;
        strengh = 1;
        inteligence = 1;
        defense = 1;
        exp = 0;
        nextLevelExp = 15;
    }

    public void getPlayerImage() {
        up1 = setup("/Jack/Jack4");
        up2 = setup("/Jack/Jack4");
        down1 = setup("/Jack/Jack1");
        down2 = setup("/Jack/Jack1");
        left1 = setup("/Jack/Jack2");
        left2 = setup("/Jack/Jack2");
        right1 = setup("/Jack/Jack3");
        right2 = setup("/Jack/Jack3");
    }

    @Override
    public void update() {

        // Se estiver em diálogo, o player fica totalmente travado (não anda nem anima)
        if (gamePanel.gameState == gamePanel.dialogueState) {
            return; 
        }

        if (!keyHandler.upPressed
                && !keyHandler.downPressed
                && !keyHandler.leftPressed
                && !keyHandler.rightPressed) {

            int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
            interactNPC(npcIndex);
            return;
        }

        if (keyHandler.upPressed) {
            direction = "up";
        } else if (keyHandler.downPressed) {
            direction = "down";
        } else if (keyHandler.leftPressed) {
            direction = "left";
        } else if (keyHandler.rightPressed) {
            direction = "right";
        }

        collision = false;
        gamePanel.collisionChecker.checkTile(this);

        int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        interactNPC(npcIndex);

        gamePanel.eHandler.checkEvent();

        if (!collision) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
            }
        }

        spriteCount++;
        if (spriteCount > 15) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            } else {
                spriteNumber = 1;
            }
            spriteCount = 0;
        }
    }

    public void interactNPC(int index) {
        if (index != 999) {
            if (gamePanel.keyHandler.fPressed == true) {
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();
                gamePanel.keyHandler.fPressed = false; 
            }
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) { image = up1; } else { image = up2; }
                break;
            case "down":
                if (spriteNumber == 1) { image = down1; } else { image = down2; }
                break;
            case "left":
                if (spriteNumber == 1) { image = left1; } else { image = left2; }
                break;
            case "right":
                if (spriteNumber == 1) { image = right1; } else { image = right2; }
                break;
        }

        if (image != null) {
            g2d.drawImage(
                    image,
                    screenX,
                    screenY,
                    spriteWidth,
                    spriteHeight,
                    null
            );
            g2d.setColor(Color.RED);
            g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}