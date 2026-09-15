package org.example.object;

import org.example.GamePanel;
import org.example.UtilityTool;

import java.awt.*;
import java.awt.image.BufferedImage;

public class SuperObject {
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int SolidAreaDefaultX = 0, SolidAreaDefaultY = 0;
    UtilityTool uTool = new UtilityTool();

    public void drawImage(Graphics g2d, GamePanel gamePanel) {
        int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
        int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

        if (    worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                worldX + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                worldX - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)
        {
            g2d.drawImage(image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
        }
    }
}
