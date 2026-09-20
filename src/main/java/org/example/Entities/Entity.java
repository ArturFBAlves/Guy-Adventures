package org.example.Entities;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Entity {
    public GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;
    public int spriteWidth;
    public int spriteHeight;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public BufferedImage attackUp1, attackUp2, attackDown1, attackDown2, attackLeft1, attackLeft2, attackRight1, attackRight2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public Rectangle attackArea = new Rectangle(0,0,0,0);
    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collision = false;
    public int actionLockCounter = 0;
    public boolean attacking = false;
    public boolean  invinsible = false;
    public int  invinsibleCounter = 0;
    public int type; // 0 = player | 1 = npc | 2 = monster

    public String dialogues[] = new String[20];
    public int dialogueIndex = 0;

    public int maxLife, life;
    public String name;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        spriteWidth = gamePanel.tileSize;
        spriteHeight = gamePanel.tileSize;
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
        gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        boolean contactPlayer = gamePanel.collisionChecker.checkPlayer(this);

        if (this.type == 2 && contactPlayer == true) {
            if (gamePanel.player.invinsible == false) {
                gamePanel.player.life -=1;
                gamePanel.player.invinsible = true;
            }
        }


        if (!collision) {
            switch (direction) {
                case "up": worldY -= speed; break;
                case "down": worldY += speed; break;
                case "left": worldX -= speed; break;
                case "right": worldX += speed; break;
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

        if (invinsible == true) {
            invinsibleCounter++;
            if (invinsibleCounter > 35) {
                invinsible = false;
                invinsibleCounter = 0;
            }
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
                    case "up": if (spriteNumber == 1) { image = up1; } else { image = up2; } break;
                    case "down": if (spriteNumber == 1) { image = down1; } else { image = down2; } break;
                    case "left": if (spriteNumber == 1) { image = left1; } else { image = left2; } break;
                    case "right": if (spriteNumber == 1) { image = right1; } else { image = right2; } break;
                }


                if (invinsible) { g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); }

                g2d.drawImage(
                        image,
                        ScreenX,
                        ScreenY,
                        spriteWidth,
                        spriteHeight,
                        null
                );
                g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

                g2d.setColor(Color.RED);
                g2d.drawRect(
                        ScreenX + solidArea.x,
                        ScreenY + solidArea.y,
                        solidArea.width,
                        solidArea.height
                );
        }
    }

    public BufferedImage setup(String imagePath, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage scaledImage = null;
        try {
            java.io.InputStream is = getClass().getResourceAsStream(imagePath + ".png");
            if (is != null) {
                scaledImage = ImageIO.read(is);
                scaledImage = uTool.scaledImage(scaledImage, width, height);
            } else {
                System.out.println("Erro: Não foi possível encontrar a imagem: " + imagePath + ".png");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scaledImage;
    }
}