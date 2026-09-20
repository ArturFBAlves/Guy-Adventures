package org.example.Entities.monster;

import org.example.Entities.Entity;
import org.example.GamePanel;

import java.util.Random;

public class Devil_Slime extends Entity {

    public Devil_Slime(GamePanel gamePanel) {
        super(gamePanel);
        name = "Devil Slime";
        direction = "down";
        type = 2;
        speed = 1;
        maxLife = 4;
        life = maxLife;
        exp = 8;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX= solidArea.x;
        solidAreaDefaultY = solidArea.y;
        getImage();
    }

    public void getImage() {
        up1 = setup("/monster/slime_up_1", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/monster/slime_up_2", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/monster/slime_down_1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/monster/slime_down_2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/monster/slime_left_1", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/monster/slime_left_2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/monster/slime_right_1", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/monster/slime_right_2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void setAction() {
        actionLockCounter++;
        if (actionLockCounter == 120) {
            Random random = new Random();
            int i = random.nextInt(100) + 1;
            if (i <= 25) {
                direction = "up";
            } else if (i <= 50) {
                direction = "down";
            } else if (i <= 75) {
                direction = "left";
            } else {
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }

}
