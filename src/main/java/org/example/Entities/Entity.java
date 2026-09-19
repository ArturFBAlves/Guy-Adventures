package org.example.Entities;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public int actionLockCounter = 0;
    
    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;

    public int maxLife, life;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void setAction() {}

    public void speak() {
    if (dialogues[dialogueIndex] == null) {
        dialogueIndex = 0;
        gamePanel.gameState = gamePanel.playState; // Fecha a caixa e volta ao jogo
        return;
    }
    gamePanel.ui.currentDialogue = dialogues[dialogueIndex];
    dialogueIndex++;

    // Faz o NPC olhar para o jogador
    switch(gamePanel.player.direction) {
        case "up": direction = "down"; break;
        case "down": direction = "up"; break;
        case "left": direction = "right"; break;
        case "right": direction = "left"; break;
    }
}

    public void update() {
        setAction();
        collision = false;
        gamePanel.collisionChecker.checkTile(this);
        //object
        gamePanel.collisionChecker.checkPlayer(this);

        if (!collision) {
            switch (direction) {
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed;
                    break;
                case "left":
                    worldX -= speed;
                    break;
                case "right":
                    worldX += speed;
                    break;
            }
        }

        // Animação
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

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        int ScreenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int ScreenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
            worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
            worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
            worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY
            ) {
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
                g2d.drawImage(image, ScreenX, ScreenY, gamePanel.tileSize, gamePanel.tileSize, null);
                g2d.setColor(Color.RED);
                g2d.drawRect(
                    ScreenX + solidArea.x,
                    ScreenY + solidArea.y,
                    solidArea.width,
                    solidArea.height
                );
        }
    }

    public BufferedImage setup(String imagePath) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            java.io.InputStream is = getClass().getResourceAsStream(imagePath + ".png");
            if (is != null) {
                scaledImage = ImageIO.read(is);
                scaledImage = uTool.scaledImage(scaledImage, gamePanel.tileSize, gamePanel.tileSize);
            } else {
                System.out.println("Erro: Não foi possível encontrar a imagem: " + imagePath + ".png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }
}