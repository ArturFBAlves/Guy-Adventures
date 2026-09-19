package org.example;

import org.example.Entities.Entity;
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

        // 3. DIALOGUE STATE
        else if (gamePanel.gameState == gamePanel.dialogueState) {
            if (code == KeyEvent.VK_F || code == KeyEvent.VK_ENTER) {
                int npcIndex = gamePanel.collisionChecker.checkEntity(gamePanel.player, gamePanel.npc);
                if (npcIndex != 999) {
                    gamePanel.npc[npcIndex].speak();
                } else {
                    gamePanel.gameState = gamePanel.playState;
                }
            }
        }

        // 4. BATTLE STATE (Sistema de Combos por Sequência)
        else if (gamePanel.gameState == gamePanel.battleState) {
            
            // Regista os comandos de combate ('j', 'h', 'k')
            if (code == KeyEvent.VK_J) {
                gamePanel.playerCombo += "j";
            }
            else if (code == KeyEvent.VK_H) {
                gamePanel.playerCombo += "h";
            }
            else if (code == KeyEvent.VK_K) {
                gamePanel.playerCombo += "k";
            }

            // Quando pressiona F ou ENTER, submete e valida o combo acumulado
            if (code == KeyEvent.VK_F || code == KeyEvent.VK_ENTER) {
                
                Entity activeBandit = null;
                for (Entity n : gamePanel.npc) {
                    if (n instanceof org.example.Entities.NPC_Bandit) {
                        activeBandit = n;
                        break;
                    }
                }

                if (activeBandit != null) {
                    String correctCombo = "jhhjhk"; // Sequência correta escondida no baú para o Bandido

                    // Se acertar o combo exato
                    if (gamePanel.playerCombo.equals(correctCombo)) {
                        gamePanel.storyProgress = 1; // Avança para o Ato 2
                        gamePanel.gameState = gamePanel.playState;
                        
                        // Remove o bandido do mapa
                        activeBandit.worldX = 0;
                        activeBandit.worldY = 0;
                    } 
                    else {
                        // Se errar o combo, o jogador sofre 1 de dano de contra-ataque
                        gamePanel.player.life -= 1;

                        // Verifica se o jogador morreu
                        if (gamePanel.player.life <= 0) {
                            gamePanel.player.life = gamePanel.player.maxLife; 
                            gamePanel.storyProgress = 0; 
                            gamePanel.gameState = gamePanel.playState;
                            
                            gamePanel.player.worldX = gamePanel.tileSize * 23;
                            gamePanel.player.worldY = gamePanel.tileSize * 21;
                        }
                    }

                    // Limpa o combo digitado para a próxima tentativa
                    gamePanel.playerCombo = "";
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