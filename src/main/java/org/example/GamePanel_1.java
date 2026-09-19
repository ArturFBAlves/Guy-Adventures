public GamePanel() {
    this.setPreferredSize(new Dimension(screenWidth, screenHeight));
    this.setBackground(Color.black);
    this.setDoubleBuffered(true);
    
    this.addKeyListener(keyH);
    this.addMouseListener(keyH); // <-- ADICIONAR ESTA LINHA AQUI!
    
    this.setFocusable(true);
}