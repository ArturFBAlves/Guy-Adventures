package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GamePanel gamePanel;
    public boolean upPressed, downPressed, leftPressed, rightPressed, fPressed;

    public KeyHandler(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyTyped(KeyEvent e) {}

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        // 1. PLAY STATE
        if (gamePanel.gameState == gamePanel.playState) {
            if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { upPressed = true; }
            if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { downPressed = true; }
            if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { leftPressed = true; }
            if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { rightPressed = true; }
            if (code == KeyEvent.VK_F || code == KeyEvent.VK_ENTER) { fPressed = true; }
            if (code == KeyEvent.VK_P) { gamePanel.gameState = gamePanel.pauseState; }
        }

        // 2. PAUSE STATE
        else if (gamePanel.gameState == gamePanel.pauseState) {
            if (code == KeyEvent.VK_P) { gamePanel.gameState = gamePanel.playState; }
        }

        // 3. DIALOGUE STATE (Avança a fala ou fecha ao pressionar F / Enter)
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            if (code == KeyEvent.VK_F || code == KeyEvent.VK_ENTER) {
                // Procura qual NPC está ativo no mapa para continuar o diálogo
                int npcIndex = gamePanel.collisionChecker.checkEntity(gamePanel.player, gamePanel.npc);
                if (npcIndex != 999) {
                    gamePanel.npc[npcIndex].speak();
                } else {
                    // Se por algum motivo perder o NPC de vista, fecha o diálogo para não trancar
                    gamePanel.gameState = gamePanel.playState;
                }
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W || code == KeyEvent.VK_UP) { upPressed = false; }
        if (code == KeyEvent.VK_S || code == KeyEvent.VK_DOWN) { downPressed = false; }
        if (code == KeyEvent.VK_A || code == KeyEvent.VK_LEFT) { leftPressed = false; }
        if (code == KeyEvent.VK_D || code == KeyEvent.VK_RIGHT) { rightPressed = false; }
        if (code == KeyEvent.VK_F || code == KeyEvent.VK_ENTER) { fPressed = false; }
    }
}