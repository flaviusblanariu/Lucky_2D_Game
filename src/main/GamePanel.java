package main;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import entity.Player;
import tile.ManagerTile;

public class GamePanel extends JPanel implements Runnable{
    //SETARI DE AFISARE
    final int originalTileSize = 16; //dala 16x16
    final int scale = 3;
    public final int tileSize = originalTileSize*scale; // dala 48x48
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 16;
    public final int screenWidth = tileSize*maxScreenCol; // 768 pixeli
    public final int screenHeight = tileSize*maxScreenRow; // 576 pixeli

    //WORLD SETTINGS
    public final int maxWorldCol = 50;
    public final int maxWorldRow = 50;
    public final int worldWidth = tileSize*maxWorldCol;
    public final int worldHeight = tileSize*maxWorldRow;
    //FPS
    int FPS = 60;

    ManagerTile tileM = new ManagerTile(this);
    KeyHandler keyH = new KeyHandler();
    Thread gameThread;
    public CheckCollision collisionCheck = new CheckCollision(this);
    public Player player = new Player(this,keyH);


    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        gameThread.setPriority(Thread.MAX_PRIORITY);
    }

    @Override
    public void run() {
        //Metoda "sleep"
        /*double drawInterval = 1000000000 / FPS; //0.01666 secunde
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {
            //System.out.println("Jocul functioneaza!");
            // 1. UPDATE: Actualizarea informatiilor precum pozitia personajului
            update();

            // 2. DRAW: Desenarea pe ecran a informatiilor actualizate
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }*/
        //Metoda "delta"
        double drawInterval = 1000000000 / FPS; //0.01666 secunde
        double delta =0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer=0;
        int drawCount=0;

        while (gameThread != null) {
            currentTime=System.nanoTime();

            delta+=(currentTime-lastTime)/drawInterval;
            timer+=(currentTime-lastTime);
            lastTime=currentTime;

            if(delta>=1){
                update();
                repaint();
                delta--;
                drawCount++;
            }
            if (timer>=1000000000){
                System.out.println("FPS:"+drawCount);
                drawCount=0;
                timer=0;
            }
        }
    }
    public void update(){
        player.update();
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;

        tileM.draw(g2);

        player.draw(g2);
        g2.dispose();
    }

}




