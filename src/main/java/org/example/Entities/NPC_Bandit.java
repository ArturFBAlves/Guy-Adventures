package org.example.Entities;

import org.example.GamePanel;

import java.awt.*;
import java.util.Random;

public class NPC_Bandit extends Entity {

    public NPC_Bandit(GamePanel gamePanel) {
        super(gamePanel);

        direction = "down";
        speed = 1; // Velocidade baixa ou 0 se ele ficar estático na ponte

        solidArea = new Rectangle(8, 16, 32, 32);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        setDialogue();
    }

    // 1. CARREGA AS IMAGENS DO BANDIDO
    public void getImage() {
        // Certifique-se de colocar as imagens na pasta correta dentro de res (ex: /res/NPC/bandit_up_1.png)
        up1 = setup("/NPC/bandit_up_1");
        up2 = setup("/NPC/bandit_up_2");
        down1 = setup("/NPC/bandit_down_1");
        down2 = setup("/NPC/bandit_down_2");
        left1 = setup("/NPC/bandit_left_1");
        left2 = setup("/NPC/bandit_left_2");
        right1 = setup("/NPC/bandit_right_1");
        right2 = setup("/NPC/bandit_right_2");
    }

    // 2. DEFINE AS FALAS DO BANDIDO ANTES DA LUTA
    public void setDialogue() {
        dialogues[0] = "Parado aí, aventureiro!\nEsta ponte pertence ao meu bando.";
        dialogues[1] = "Se queres passar para o outro lado,\nterás de me enfrentar primeiro!";
    }

    // 3. COMPORTAMENTO DE MOVIMENTO (Opcional: se ele fica parado na ponte, pode deixar vazio)
    public void setAction() {
        // Se quiser que ele fique fixo a bloquear a ponte, não coloque nada aqui.
        // Se quiser que ele ande aleatoriamente antes de falar, pode usar a lógica padrão de IA.
    }

    // 4. AÇÃO EXECUTADA QUANDO O JOGADOR FALA COM ELE
    public void speak() {
        super.speak(); // Chama o método padrão da Entity que avança as linhas do diálogo
        
        // Aqui, quando o diálogo chegar ao fim, podes acionar o gatilho da luta contra o slime!
    }
}