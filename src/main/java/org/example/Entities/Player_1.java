package org.example.Entities;

public class Player extends Entity {

    // <-- ADICIONAR ESTAS VARIÁVEIS -->
    public boolean attacking = false;
    public int attackCounter = 0;

    // ... Construtor e outros métodos ...

    @Override
    public void update() {
        // <-- ADICIONAR ESTA VERIFICAÇÃO NO INÍCIO DO UPDATE -->
        if (attacking) {
            attacking();
        } else {
            // Verifica se o rato foi clicado para iniciar o ataque
            if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || 
                keyH.rightPressed || keyH.mousePressed || keyH.enterPressed) {

                if (keyH.mousePressed || keyH.enterPressed) {
                    attacking = true;
                }

                // --- TEU CÓDIGO NORMAL DE MOVIMENTO E COLISÃO DO JOGADOR FICA AQUI DENTRO ---
            }
        }
    }

    // <-- ADICIONAR ESTE NOVO MÉTODO -->
    public void attacking() {
        attackCounter++;

        if (attackCounter <= 5) {
            spriteNum = 1;
        }

        if (attackCounter > 5 && attackCounter <= 25) {
            spriteNum = 2;

            // Salva posição e caixa de colisão original
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            // Área de alcance da espada
            int attackAreaWidth = 36;
            int attackAreaHeight = 36;

            switch (direction) {
                case "up": worldY -= attackAreaHeight; break;
                case "down": worldY += gp.tileSize; break;
                case "left": worldX -= attackAreaWidth; break;
                case "right": worldX += gp.tileSize; break;
            }

            // Atualiza hitbox para o alcance da espada
            solidArea.width = attackAreaWidth;
            solidArea.height = attackAreaHeight;

            // Verifica colisão do ataque com os monstros
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            // Restaura área original do jogador
            worldX = currentWorldX;
            worldY = currentWorldY;
            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }

        if (attackCounter > 25) {
            spriteNum = 1;
            attackCounter = 0;
            attacking = false;
        }
    }

    // <-- ADICIONAR ESTE NOVO MÉTODO -->
    public void damageMonster(int i) {
        if (i != 999) {
            if (!gp.monster[i].invincible) {
                gp.monster[i].life -= 1;
                gp.monster[i].invincible = true;

                System.out.println("Acertou no monstro! Vida restante: " + gp.monster[i].life);

                if (gp.monster[i].life <= 0) {
                    gp.monster[i] = null; // Monstro morre e desaparece do mapa
                }
            }
        }
    }
}