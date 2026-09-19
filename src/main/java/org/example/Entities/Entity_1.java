package org.example.Entities;

public class Entity {
    // Teus atributos existentes (x, y, speed, etc.)
    
    // <-- ADICIONAR ESTAS VARIÁVEIS NA CLASSE -->
    public int maxLife;
    public int life;
    public boolean invincible = false;
    public int invincibleCounter = 0;

    public void update() {
        // Lógica de movimento da entidade...

        // <-- ADICIONAR ESTE BLOCO NO UPDATE DA ENTITY -->
        if (invincible) {
            invincibleCounter++;
            if (invincibleCounter > 40) { // 40 frames de imunidade após levar hit
                invincible = false;
                invincibleCounter = 0;
            }
        }
    }
}