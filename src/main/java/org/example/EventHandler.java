package org.example;

import org.example.Enums.State;

import java.awt.*;

public class EventHandler {
    GamePanel gamePanel;
    Rectangle eventRect;
    int eventRectDefaultX, eventRectDefaultY;

    int previousEventX, previousEventY;
    boolean canTouchEvent = true;

    public EventHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;

        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }

    public void checkEvent() {
        int xDistance = Math.abs(gamePanel.player.worldX - previousEventX);
        int yDistance = Math.abs(gamePanel.player.worldY - previousEventY);
        int distance = Math.max(xDistance, yDistance);

        if (distance > gamePanel.tileSize) {
            canTouchEvent = true;
        }
        if (canTouchEvent) {
            if (hit(26, 15, "any")) {
                damagePit(State.dialogueState);
            } else if (hit(27, 15, "any")) {
                healingPool(State.dialogueState);
            } else if (hit(28, 15, "any")) {
                teleport(State.dialogueState);
            }
        }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {
        boolean hit = false;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        eventRect.x = eventCol * gamePanel.tileSize + eventRectDefaultX;
        eventRect.y = eventRow * gamePanel.tileSize + eventRectDefaultY;


        if (gamePanel.player.solidArea.intersects(eventRect)) {
            if (gamePanel.player.direction.equals(reqDirection) || reqDirection.contains("any")) {
                hit = true;
                previousEventX = gamePanel.player.worldX;
                previousEventY = gamePanel.player.worldY;
            }
        }

        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;
    }

    public void damagePit(State gameState) {
        gamePanel.gameState = gameState;
        gamePanel.ui.dialogueUI.currentDialogue = "Você caiu em um buraco e levou dano!";
        gamePanel.player.life -= 1;

        // Bloqueia novos disparos até o jogador se mover para fora da área
        canTouchEvent = false;
    }

    public void healingPool(State gameState) {
        if (gamePanel.keyHandler.fPressed) {
            gamePanel.gameState = gameState;
            gamePanel.ui.dialogueUI.currentDialogue = "Você bebeu a água e regenerou a vida!";
            gamePanel.player.life = gamePanel.player.maxLife;
        }
    }

    public void teleport(State gameState) {
        if(gamePanel.keyHandler.fPressed){
            gamePanel.gameState = gameState;
            gamePanel.ui.dialogueUI.currentDialogue = "Teleport!";
            gamePanel.player.worldX = gamePanel.tileSize*37;
            gamePanel.player.worldY = gamePanel.tileSize*37;
        }
    }
}