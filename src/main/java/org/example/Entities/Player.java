package org.example.Entities;

import org.example.GamePanel;
import org.example.KeyHandler;
import org.example.object.OBJ_Heart;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Player extends Entity {

    KeyHandler keyHandler;
    public int screenX;
    public int screenY;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {
        super(gamePanel);

        this.keyHandler = keyHandler;

        this.screenX = gamePanel.screenWidth / 2 - gamePanel.tileSize / 2;
        this.screenY = gamePanel.screenHeight / 2 - gamePanel.tileSize / 2;


        spriteWidth = 86;
        spriteHeight = 84;

        solidArea = new Rectangle(spriteWidth/3, 32, 28, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        attackArea.width = 36;
        attackArea.height = 36;

        setDefaultValues();
        getPlayerImage();
        getPlayerAttackImage();
        setItems();
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

    public void setItems() {
//        inventory.add(currentWeapon);
//        inventory.add(currentShield);
//        inventory.add(new OBJ_Heart(gamePanel));
//        inventory.add(new OBJ_Heart(gamePanel));
    }

    public void getPlayerImage() {
        up1 = setup("/Jack/Jack4", gamePanel.tileSize, gamePanel.tileSize);
        up2 = setup("/Jack/Jack4", gamePanel.tileSize, gamePanel.tileSize);
        down1 = setup("/Jack/JDown1", gamePanel.tileSize, gamePanel.tileSize);
        down2 = setup("/Jack/JDown2", gamePanel.tileSize, gamePanel.tileSize);
        left1 = setup("/Jack/Jack2", gamePanel.tileSize, gamePanel.tileSize);
        left2 = setup("/Jack/JLeft2", gamePanel.tileSize, gamePanel.tileSize);
        right1 = setup("/Jack/Jack3", gamePanel.tileSize, gamePanel.tileSize);
        right2 = setup("/Jack/JRight2", gamePanel.tileSize, gamePanel.tileSize);
    }

    public void getPlayerAttackImage() {
        attackUp1 = setup("/Jack/JAttackUp", gamePanel.tileSize, gamePanel.tileSize*2);
        attackUp2 = setup("/Jack/JAttackUp", gamePanel.tileSize, gamePanel.tileSize*2);
        attackDown1 = setup("/Jack/JAttackDown", gamePanel.tileSize, gamePanel.tileSize*2);
        attackDown2 = setup("/Jack/JAttackDown", gamePanel.tileSize, gamePanel.tileSize*2);
        attackLeft1 = setup("/Jack/JAttackLeft", gamePanel.tileSize*2, gamePanel.tileSize);
        attackLeft2 = setup("/Jack/JAttackLeft", gamePanel.tileSize*2, gamePanel.tileSize);
        attackRight1 = setup("/Jack/JAttackRight", gamePanel.tileSize*2, gamePanel.tileSize);
        attackRight2 = setup("/Jack/JAttackRight", gamePanel.tileSize*2, gamePanel.tileSize);
    }

    @Override
    public void update() {

        if(attacking == true) {
            attacking();
            return;
        }

        if(keyHandler.upPressed == true ||
                keyHandler.downPressed == true ||
                keyHandler.leftPressed == true ||
                keyHandler.rightPressed == true ||
                keyHandler.fPressed == true
        ) {
            if (keyHandler.upPressed) {
                direction = "up";
            } else if (keyHandler.downPressed) {
                direction = "down";
            } else if (keyHandler.leftPressed) {
                direction = "left";
            } else if (keyHandler.rightPressed) {
                direction = "right";
            }
        }

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

        collision = false;
        gamePanel.collisionChecker.checkTile(this);

        int npcIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.npc);
        interactNPC(npcIndex);

        int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
        contactMonster(monsterIndex);


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

        if (invinsible == true) {
            invinsibleCounter++;
            if (invinsibleCounter > 60) {
                invinsible = false;
                invinsibleCounter = 0;
            }
        }
    }

    public void attacking() {
        spriteCount++;
        if (spriteCount <= 5) {
            spriteNumber = 1;
        }
        if (spriteCount > 5 && spriteNumber <= 25) {
            spriteNumber = 2;
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            switch (direction) {
                case "up": worldY -= attackArea.height; break;
                case "down": worldY += attackArea.height; break;
                case "right": worldX += attackArea.width; break;
                case "left": worldX -= attackArea.width; break;

            }

                solidArea.width = attackArea.width;
                solidArea.height = attackArea.height;

                int monsterIndex = gamePanel.collisionChecker.checkEntity(this, gamePanel.monster);
                damageMonster(monsterIndex);

                worldX = currentWorldX;
                worldY = currentWorldY;
                solidArea.width = solidAreaWidth;
                solidArea.height = solidAreaHeight;

        }
        if (spriteCount > 25) {
            spriteNumber = 1;
            spriteCount = 0;
            attacking = false;
        }
    }

    public void contactMonster(int i) {
        if (i != 999) {
            if (invinsible == false) {
                life-=1;
                invinsible = true;
            }
        }
    }

    public void damageMonster(int i) {
        if (i != 999) {
            if (gamePanel.monster[i].invinsible == false) {
                gamePanel.monster[i].life -= 1;
                gamePanel.monster[i].invinsible = true;
                if(gamePanel.monster[i].life <= 0) {
                    gamePanel.monster[i] = null;
                }
            }
        }
        else {
            System.out.printf("miss");
        }
    }

    public void interactNPC(int index) {
        if (gamePanel.keyHandler.fPressed == true) {
            if (index != 999) {
                gamePanel.gameState = gamePanel.dialogueState;
                gamePanel.npc[index].speak();
                gamePanel.keyHandler.fPressed = false;
            }
        else {
                attacking = true;
            }
        }
    }

    public void checkLevelUp() {
        if (exp >= nextLevelExp) {
            level++;
            exp -= nextLevelExp;
            gamePanel.ui.addMessage("Você passou para o nível " +  level + " parabéns!");
            randomStatusUp();
        }
    }

    public void randomStatusUp() {
        Random random = new Random();
        int statusUp = random.nextInt(1,3);
        switch (statusUp) {
            case 1:
                maxLife++;
                life++;
                break;
            case 2:
                speed++;
        }
    }

    @Override
    public void draw(Graphics2D g2d) {
        BufferedImage image = null;
        int tempScreenX = screenX, tempScreenY = screenY;

        switch (direction) {
            case "up":
                if (attacking == false) {
                    if (spriteNumber == 1) { image = up1; } else { image = up2; }
                }
                if (attacking == true) {
                    tempScreenY = screenY - gamePanel.tileSize; // Desloca apenas no ataque
                    if (spriteNumber == 1) { image = attackUp1; } else { image = attackUp2; }
                }
                break;
            case "down":
                if (attacking == false) { if (spriteNumber == 1) { image = down1; } else { image = down2; } }
                if (attacking == true) { if (spriteNumber == 1) { image = attackDown1; } else { image = attackDown2; } }
                break;
            case "left":
                if (attacking == false) {
                    if (spriteNumber == 1) { image = left1; } else { image = left2; }
                }
                if (attacking == true) {
                    tempScreenX = screenX - gamePanel.tileSize; // Desloca apenas no ataque
                    if (spriteNumber == 1) { image = attackLeft1; } else { image = attackLeft2; }
                }
                break;
            case "right":
                if (attacking == false) { if (spriteNumber == 1) { image = right1; } else { image = right2; } }
                if (attacking == true) { if (spriteNumber == 1) { image = attackRight1; } else { image = attackRight2; } }
                break;
        }


        if (image != null) {
            if (invinsible) { g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); }

            g2d.drawImage(image, screenX, screenY, spriteWidth, spriteHeight, null);
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

            g2d.setColor(Color.RED);
            g2d.drawRect(screenX + solidArea.x, screenY + solidArea.y, solidArea.width, solidArea.height);
        }
    }
}