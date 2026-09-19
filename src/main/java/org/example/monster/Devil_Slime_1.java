package org.example.monster;

import org.example.GamePanel;
import org.example.Entities.Entity;

public class Devil_Slime extends Entity {

    public Devil_Slime(GamePanel gp) {
        super(gp);

        name = "Devil Slime";
        speed = 1;
        
        // <-- ADICIONAR ESTAS LINHAS NO CONSTRUTOR -->
        maxLife = 4;
        life = maxLife;

        // Suas configurações de HitBox do Slime...
    }
}