package org.example;

import org.example.Entities.AssetSetter;
import org.example.Entities.Entity;
import org.example.Entities.Player;
import org.example.Tile.TileManager;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    final int originalTileSize = 16;
    final int scale = 4;

    public int tileSize = originalTileSize * scale;
    public int maxScreenCol = 16;
    public int maxScreenRow = 12;

    public int screenWidth = maxScreenCol * tileSize;
    public int screenHeight = maxScreenRow * tileSize;

    Thread gameThread;
    KeyHandler keyHandler =  new KeyHandler();

    //World Map
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int maxWorldWidth = maxWorldCol * tileSize;
    public final int maxWorldHeight = maxWorldRow * tileSize;
    int FPS = 60;

    public Player player = new Player(this, keyHandler);
    public Entity[] npc = new Entity[10];

    public TileManager tileManager =  new TileManager(this);
    public CollisionChecker collisionChecker = new  CollisionChecker(this);
    public AssetSetter assetSetter = new AssetSetter(this);

    public GamePanel() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);

    }

    public void setupGame() {
        assetSetter.setNPC();
    }


    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {
        while (gameThread != null) {
            double drawInterval = 1000000000 / FPS;
            double nextDrawTime = System.nanoTime() +  drawInterval;
            long currentTime = System.nanoTime();

            update();
            repaint();


            try {
                double remainingTime = nextDrawTime - currentTime;
                remainingTime = remainingTime/1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void update() {
        player.update();
        for (int i = 0; i < npc.length; i++) {
            if (npc[i] != null) {
                npc[i].update();
            }
        }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        tileManager.draw(g2d);

        // Junta todas as entidades desenháveis numa lista
        java.util.List<Entity> entityList = new java.util.ArrayList<>();
        entityList.add(player);
        for (Entity n : npc) {
            if (n != null) {
                entityList.add(n);
            }
        }

        // Ordena por worldY (quem está mais acima na tela desenha primeiro)
        entityList.sort((e1, e2) -> Integer.compare(e1.worldY, e2.worldY));

        for (Entity e : entityList) {
            e.draw(g2d);
        }

        g2d.dispose();
    }
}
