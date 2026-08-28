package org.example.Entities;

import org.example.GamePanel;
import org.example.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    GamePanel gamePanel;
    KeyHandler keyHandler;

    public int screenX;
    public int screenY;

    public Player(GamePanel gamePanel, KeyHandler keyHandler) {

        this.gamePanel = gamePanel;
        this.keyHandler = keyHandler;

        // Mantém o player no centro da tela
        this.screenX =
                gamePanel.screenWidth / 2
                        - gamePanel.tileSize / 2;

        this.screenY =
                gamePanel.screenHeight / 2
                        - gamePanel.tileSize / 2;

        this.solidArea = new Rectangle(
                8,
                16,
                32,
                32
        );

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {

        worldX = gamePanel.tileSize * 23;
        worldY = gamePanel.tileSize * 23;

        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {

        try {

            down1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_down_1.png"
                    )
            );

            down2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_down_2.png"
                    )
            );

            up1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_up_1.png"
                    )
            );

            up2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_up_2.png"
                    )
            );

            left1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_left_1.png"
                    )
            );

            left2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_left_2.png"
                    )
            );

            right1 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_right_1.png"
                    )
            );

            right2 = ImageIO.read(
                    getClass().getResourceAsStream(
                            "/Slime/slime_right_2.png"
                    )
            );

        } catch (Exception e) {

            e.printStackTrace();
        }
    }

    public void update() {

        // Se nenhuma tecla estiver pressionada,
        // o personagem não se move.
        if (!keyHandler.upPressed
                && !keyHandler.downPressed
                && !keyHandler.leftPressed
                && !keyHandler.rightPressed) {

            return;
        }

        // Define a direção
        if (keyHandler.upPressed) {
            direction = "up";

        } else if (keyHandler.downPressed) {
            direction = "down";

        } else if (keyHandler.leftPressed) {
            direction = "left";

        } else if (keyHandler.rightPressed) {
            direction = "right";
        }

        // Verifica colisão
        collision = false;

        gamePanel.collisionChecker.checkTile(this);

        // Só movimenta se não houver colisão
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
                } else {
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

        if (image != null) {

            g2d.drawImage(
                    image,
                    screenX,
                    screenY,
                    gamePanel.tileSize,
                    gamePanel.tileSize,
                    null
            );
        }
    }
}