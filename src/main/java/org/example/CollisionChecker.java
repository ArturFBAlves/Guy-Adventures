package org.example;

import org.example.Entities.Entity;

public class CollisionChecker {

    public GamePanel gamePanel;

    public CollisionChecker(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public void checkTile(Entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
        int entityRightWorldX = entity.worldX + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldY + entity.solidArea.y;
        int entityBottomWorldY = entity.worldY + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX / gamePanel.tileSize;
        int entityRightCol = entityRightWorldX / gamePanel.tileSize;
        int entityTopRow = entityTopWorldY / gamePanel.tileSize;
        int entityBottomRow = entityBottomWorldY / gamePanel.tileSize;

        int tileNum1;
        int tileNum2;

        switch (entity.direction) {

            case "up":

                entityTopRow =
                        (entityTopWorldY - entity.speed) / gamePanel.tileSize;

                if (entityTopWorldY - entity.speed < 0) {
                    entity.collision = true;
                    return;
                }

                tileNum1 =
                        gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];

                tileNum2 =
                        gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

                if (gamePanel.tileManager.tiles[tileNum1].collision ||
                        gamePanel.tileManager.tiles[tileNum2].collision) {

                    entity.collision = true;
                }

                break;


            case "down":

                entityBottomRow =
                        (entityBottomWorldY + entity.speed) / gamePanel.tileSize;

                if (entityBottomWorldY + entity.speed >= gamePanel.maxWorldRow * gamePanel.tileSize) {
                    entity.collision = true;
                    return;
                }

                tileNum1 =
                        gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

                tileNum2 =
                        gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (gamePanel.tileManager.tiles[tileNum1].collision ||
                        gamePanel.tileManager.tiles[tileNum2].collision) {

                    entity.collision = true;
                }

                break;


            case "left":

                entityLeftCol =
                        (entityLeftWorldX - entity.speed) / gamePanel.tileSize;

                if (entityLeftWorldX - entity.speed < 0) {
                    entity.collision = true;
                    return;
                }

                tileNum1 =
                        gamePanel.tileManager.mapTileNum[entityLeftCol][entityTopRow];

                tileNum2 =
                        gamePanel.tileManager.mapTileNum[entityLeftCol][entityBottomRow];

                if (gamePanel.tileManager.tiles[tileNum1].collision ||
                        gamePanel.tileManager.tiles[tileNum2].collision) {

                    entity.collision = true;
                }

                break;


            case "right":

                entityRightCol =
                        (entityRightWorldX + entity.speed) / gamePanel.tileSize;

                if (entityRightWorldX + entity.speed >= gamePanel.maxWorldCol * gamePanel.tileSize) {
                    entity.collision = true;
                    return;
                }

                tileNum1 =
                        gamePanel.tileManager.mapTileNum[entityRightCol][entityTopRow];

                tileNum2 =
                        gamePanel.tileManager.mapTileNum[entityRightCol][entityBottomRow];

                if (gamePanel.tileManager.tiles[tileNum1].collision ||
                        gamePanel.tileManager.tiles[tileNum2].collision) {

                    entity.collision = true;
                }

                break;
        }
    }


    public int checkEntity(Entity entity, Entity[] target) {
        int index = 999;

        for (int i = 0; i < target.length; i++) {
            if (target[i] != null && target[i] != entity) {

                entity.solidArea.x = entity.worldX + entity.solidArea.x;
                entity.solidArea.y = entity.worldY + entity.solidArea.y;

                target[i].solidArea.x = target[i].worldX + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldY + target[i].solidArea.y;

                switch (entity.direction) {
                    case "up": entity.solidArea.y -= entity.speed; break;
                    case "down": entity.solidArea.y += entity.speed; break;
                    case "left": entity.solidArea.x -= entity.speed; break;
                    case "right": entity.solidArea.x += entity.speed; break;
                }

                if (entity.solidArea.intersects(target[i].solidArea)) {
                    if (target[i] != entity) {
                        entity.collision = true;
                        index = i;
                    }
                }

                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }
        }
        return index;
    }

    public boolean checkPlayer(Entity entity) {

        boolean contactPlayer = false;

        entity.solidArea.x = entity.worldX + entity.solidArea.x;
        entity.solidArea.y = entity.worldY + entity.solidArea.y;

        gamePanel.player.solidArea.x = gamePanel.player.worldX + gamePanel.player.solidArea.x;
        gamePanel.player.solidArea.y = gamePanel.player.worldY + gamePanel.player.solidArea.y;

        switch (entity.direction) {
            case "up": entity.solidArea.y -= entity.speed; break;
            case "down": entity.solidArea.y += entity.speed; break;
            case "left": entity.solidArea.x -= entity.speed; break;
            case "right": entity.solidArea.x += entity.speed; break;
        }
        if (entity.solidArea.intersects(gamePanel.player.solidArea)) {
            entity.collision = true;
            contactPlayer = true;
        }

        entity.solidArea.x = entity.solidAreaDefaultX;
        entity.solidArea.y = entity.solidAreaDefaultY;
        gamePanel.player.solidArea.x = gamePanel.player.solidAreaDefaultX;
        gamePanel.player.solidArea.y = gamePanel.player.solidAreaDefaultY;

        return contactPlayer;
    }
}