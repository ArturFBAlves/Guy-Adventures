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
    public int mapTileNum[][];

    public TileManager(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        tiles = new Tile[20];
        mapTileNum = new int[gamePanel.maxWorldCol][gamePanel.maxWorldRow];
        getTileImage();
        loadMap();
    }

    public void getTileImage() {
        setup(0, "grass_tile", true);
        setup(1, "brick_tile", false);
        setup(2, "water_tile", true);
        setup(3, "grass1_tile", false);
        setup(4, "grass2_tile", false);
        setup(5, "grass3_tile", false);
        setup(6, "tree1_tile", true);
        setup(7, "straightside_path_tile", false);
        setup(8, "grass0_tile1", false);
        setup(9, "sand_tile1", false);
        setup(10, "sand_tile2", false);
        setup(11, "sandshell_tile3", false);
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
                    String numbers[] = line.split(" ");

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

        while (worldCol < gamePanel.maxWorldCol &&  worldRow < gamePanel.maxWorldRow) {

            int tileNum = mapTileNum[worldCol][worldRow];

            int worldX = worldCol * gamePanel.tileSize;
            int worldY = worldRow * gamePanel.tileSize;
            int screenX = worldX - gamePanel.player.worldX + gamePanel.player.screenX;
            int screenY = worldY - gamePanel.player.worldY + gamePanel.player.screenY;

            g2d.drawImage(tiles[tileNum].image, screenX, screenY,null);
            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
