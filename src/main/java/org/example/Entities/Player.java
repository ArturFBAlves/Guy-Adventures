package org.example.Entities;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity{
    GamePanel gamePanel;
    KeyHandler keyHandler;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            down1 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_down_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_down_2.png"));
            up1 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_up_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_up_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_left_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_left_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_right_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/Slime/slime_right_2.png"));
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyHandler.upPressed) {
            y -= speed;
            direction = "up";
        }
        if(keyHandler.downPressed) {
            direction = "down";
            y += speed;
        }
        if(keyHandler.rightPressed) {
            direction = "right";
            x += speed;
        }
        if(keyHandler.leftPressed) {
            direction = "left";
            x -= speed;
        }
        spriteCount++;
        if(spriteCount > 15) {
            if (spriteNumber == 1) {
                spriteNumber = 2;
            }else if (spriteNumber == 2) {
                spriteNumber = 1;
            }
            spriteCount = 0;
        }

    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        switch (direction) {
            case "up":
                if (spriteNumber == 1) {
                    image = up1;
                } else {
                    image = up2;
                }

                break;
            case "down":
                if (spriteNumber == 1) {
                    image = down1;
                }  else {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNumber == 1) {
                    image = left1;
                } else {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNumber == 1) {
                    image = right1;
                } else {
                    image = right2;
                }
                break;
        }

        g2d.drawImage(image, x, y, gamePanel.tileSize, gamePanel.tileSize, null);
    }
}
