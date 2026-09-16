package org.example.object;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;

public class OBJ_Heart extends SuperObject {
    GamePanel gamePanel;
    public OBJ_Heart(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        name = "Heart";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objects/heart1.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objects/heart2.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objects/heart3.png"));
            image = uTool.scaledImage(image, gamePanel.tileSize, gamePanel.tileSize);
            image2 = uTool.scaledImage(image2, gamePanel.tileSize, gamePanel.tileSize);
            image3 = uTool.scaledImage(image3, gamePanel.tileSize, gamePanel.tileSize);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
