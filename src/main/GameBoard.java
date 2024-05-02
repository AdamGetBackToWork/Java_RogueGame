package Projekt.src.main;

import javax.swing.JPanel;

import Projekt.src.bgr.TileHandler;
import Projekt.src.entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameBoard extends JPanel implements Runnable {
    

    // Ustawienia ekranu i skalowanie 16-bitowej grafiki
    final int tileSize = 16;
    final int scale = 3;   //

    // Wymiary ekranu 
    public final int finalTileSize = scale * tileSize;
    public final int screenColumns = 48;   // 16
    public final int screenRows = 36;      // 12

    // Przeskalowane wymiary ekranu 576p x 768p
    public final int screenHeight = tileSize * screenRows;
    public final int screenWidth = tileSize * screenColumns;

    public final int maxWorldCol = 34;
    public final int maxWorldRow = 24;
    public final int worldWidth = finalTileSize * maxWorldCol;
    public final int worldHeight = finalTileSize * maxWorldRow;
    


    // FPS
    int FPS = 60;

    TileHandler th = new TileHandler(this);
    KeyHandler kh = new KeyHandler();
    Thread gameThread;
    public Player player = new Player(this,kh);

    // konstruktor klasy
    public GameBoard(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        double drawInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        boolean wrongKeyMessagePrinted = false;
        
        while(gameThread != null){
            currentTime =  System.nanoTime();
            
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1){
                update();
                repaint();
                delta--;
            }

            // if (timer > 1000000000) {
            //     System.out.println;
            // }

            // if(keyHandler.elsePress == true){
            //     System.out.println("nacisnieto zly klawisz");
            // }
            if(kh.elsePress == true){
                if (!wrongKeyMessagePrinted) {
                    
                    System.out.println("Rostek ssie pałe");
                    wrongKeyMessagePrinted = true; // Set the flag to true after printing the message
                }
            } else {
                wrongKeyMessagePrinted = false; // Reset the flag if the correct key is pressed
            }
        
        }
    
    }

    public void update(){

        player.update();

    }

    public void paintComponent(Graphics g){
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        th.draw(g2);
        player.draw(g2);

        g2.dispose();
    }
}
