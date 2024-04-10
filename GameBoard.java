package Projekt;

import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GameBoard extends JPanel implements Runnable {
    

    // Ustawienia ekranu i skalowanie 16-bitowej grafiki
    final int tileSize = 16;
    final int scale = 12;   //

    // Wymiary ekranu 
    final int finalTileSize = scale * tileSize;
    final int screenColumns = 48;   // 16
    final int screenRows = 36;      // 12

    // Przeskalowane wymiary ekranu 576p x 768p
    final int screenHeight = tileSize * screenRows;
    final int screenWidth = tileSize * screenColumns;

    // FPS
    int FPS = 60;

    KeyboardInput keyHandler = new KeyboardInput();
    Thread gameThread;

    int playerPositionX = 100;
    int playerPositionY = 100;
    int playerMove = 4;

    // konstruktor klasy
    public GameBoard(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run(){

        while(gameThread != null) {
            
            // long currTime = System.nanoTime();
            // long currTime2 = System.currentTimeMillis();

            update();        
            repaint();

        }
    }

    public void update(){

        if(keyHandler.upPress == true){
            playerPositionY -= playerMove;
        }
        else if(keyHandler.downPress == true){
            playerPositionY += playerMove;
        }
        else if(keyHandler.leftPress == true){
            playerPositionX -= playerMove;
        }
        else if(keyHandler.rightPress == true){
            playerPositionX += playerMove;
        }

    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.white);

        g2.fillRect(playerPositionX, playerPositionY, tileSize, tileSize);

        g2.dispose();
    }
}
