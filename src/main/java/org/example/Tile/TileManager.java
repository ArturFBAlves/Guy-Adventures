package org.example.Tile;

import org.example.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
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
        try {
            tiles[0] = new Tile();
            tiles[1] = new Tile();
            tiles[2] = new Tile();
            tiles[3] = new Tile();
            tiles[4] = new Tile();
            tiles[5] = new Tile();
            tiles[6] = new Tile();
            tiles[7] = new Tile();
            tiles[8] = new Tile();
            tiles[9] = new Tile();
            tiles[10] = new Tile();
            tiles[11] = new Tile();
            tiles[0].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass_tile.png"));
            tiles[1].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/brick_tile.png"));
            tiles[2].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/water_tile.png"));
            tiles[3].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass1_tile.png"));
            tiles[4].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass2_tile.png"));
            tiles[5].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass3_tile.png"));
            tiles[6].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/tree1_tile.png"));
            tiles[7].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/straightside_path_tile.png"));
            tiles[8].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/grass0_tile1.png"));
            tiles[9].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand_tile1.png"));
            tiles[10].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sand_tile2.png"));
            tiles[11].image = ImageIO.read(getClass().getResourceAsStream("/Tiles/sandshell_tile3.png"));
            tiles[2].collision = true;
            tiles[0].collision = true;
            tiles[6].collision = true;
        } catch (Exception e) {
            throw new RuntimeException(e);
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

            g2d.drawImage(tiles[tileNum].image, screenX, screenY, gamePanel.tileSize, gamePanel.tileSize, null);
            worldCol++;
            if (worldCol == gamePanel.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }
}
