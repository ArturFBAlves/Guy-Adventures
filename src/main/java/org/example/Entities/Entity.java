package org.example.Entities;

import org.example.GamePanel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    GamePanel gamePanel;
    public int worldX, worldY;
    public int speed;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    public String direction;
    public int spriteCount = 0;
    public int spriteNumber = 1;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public boolean collision = false;

    public Entity(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }
}
