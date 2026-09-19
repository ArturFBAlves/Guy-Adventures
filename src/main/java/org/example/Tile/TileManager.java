package org.example.Tile;

import org.example.GamePanel;
import org.example.UtilityTool;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePanel gamePanel;
    public Tile[] tiles;
    public int[][] mapTileNum;

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[50];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
//        setup(0, "grass_tile", true);
//        setup(1, "brick_tile", false);
//        setup(2, "water_tile", true);
//        setup(3, "grass1_tile", false);
//        setup(4, "grass2_tile", false);
//        setup(5, "grass3_tile", false);
        setup(18, "tree2_tile", true);
//        setup(7, "straightside_path_tile", false);
//        setup(8, "grass0_tile1", false);
//        setup(9, "sand_tile1", false);
//        setup(10, "sand_tile2", false);
//        setup(11, "sandshell_tile3", false);

        setup(0, "grass1", false);
        setup(1, "grass2", false);
        setup(2, "grass3", false);
        setup(3, "grass4", false);
        setup(4, "grass5", false);
        setup(5, "grass6", false);
        setup(6, "grass7", false);
        setup(7, "grass8", false);
        setup(8, "grass9", false);

        setup(9, "water1", true);
        setup(10, "water2", true);
        setup(11, "water3", true);
        setup(12, "water4", true);
        setup(13, "water5", true);
        setup(14, "water6", true);
        setup(15, "water7", true);
        setup(16, "water8", true);
        setup(17, "water9", true);

        setup(19, "house1", true);
        setup(20, "house2", true);
        setup(21, "house3", true);
        setup(22, "house4", true);
        setup(23, "house5", true);
        setup(24, "house6", true);
        setup(25, "house7", true);
        setup(26, "house8", true);
        setup(27, "house9", true);


    }

    public void setup(int index, String imagePath, boolean collision) {
        UtilityTool uTool = new UtilityTool();
        try {
            tiles[index] = new Tile();
            tiles[index].image = ImageIO.read(getClass().getResourceAsStream("/tiles/"+imagePath+".png"));
            tiles[index].image = uTool.scaledImage(tiles[index].image, gamePanel.tileSize, gamePanel.tileSize);
            tiles[index].collision =  collision;

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void loadMap() {
        try {
            InputStream stream = getClass().getResourceAsStream("/Maps/World.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(stream));

            int col = 0, row = 0;

            while (col < gamePanel.maxWorldCol && row < gamePanel.maxWorldRow) {
                String line = br.readLine();
                while (col < gamePanel.maxWorldCol) {
                    String[] numbers = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }
                if (col == gamePanel.maxWorldCol) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch (Exception e) {

        }
    }

    public void draw(Graphics2D g2d) {
        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gamePanel.maxWorldCol && worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            if (worldX + gamePanel.tileSize > gamePanel.player.worldX - gamePanel.player.screenX &&
                    worldX - gamePanel.tileSize < gamePanel.player.worldX + gamePanel.player.screenX &&
                    worldY + gamePanel.tileSize > gamePanel.player.worldY - gamePanel.player.screenY &&
                    worldY - gamePanel.tileSize < gamePanel.player.worldY + gamePanel.player.screenY)
            {
                g2d.drawImage(tiles[tileNum].image, screenX, screenY, null);
            }
            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
