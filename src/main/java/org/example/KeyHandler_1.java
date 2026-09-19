package org.example;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;      // <-- ADICIONAR ESTA LINHA
import java.awt.event.MouseListener;  // <-- ADICIONAR ESTA LINHA

public class KeyHandler implements KeyListener, MouseListener { // <-- ADICIONAR "implements MouseListener"

    public boolean upPressed, downPressed, leftPressed, rightPressed;
    public boolean enterPressed;
    public boolean mousePressed; // <-- ADICIONAR ESTA LINHA

    // --- MANTÉM OS TEUS MÉTODOS keyPressed E keyReleased COMO ESTÃO ---

    // --- ADICIONAR ESTES MÉTODOS DO RATO NO FINAL DA CLASSE ---
    @Override
    public void mousePressed(MouseEvent e) {
        if (e.getButton() == MouseEvent.MouseEvent.BUTTON1) { // Botão esquerdo do rato
            mousePressed = true;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            mousePressed = false;
        }
    }

    @Override public void mouseClicked(MouseEvent e) {}
    @Override public void mouseEntered(MouseEvent e) {}
    @Override public void mouseExited(MouseEvent e) {}
}
