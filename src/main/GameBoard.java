package main;

import background.TileHandler;
import entity.Player;
import object.THEObject;

import javax.swing.JPanel;
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
    public final int screenRows = 41;      // 12

    // Przeskalowane wymiary ekranu 576p x 768p
    public final int screenHeight = tileSize * screenRows;
    public final int screenWidth = tileSize * screenColumns;

    public final int maxWorldCol = 34;
    public final int maxWorldRow = 24;
    // public final int worldWidth = finalTileSize * maxWorldCol;
    // public final int worldHeight = finalTileSize * maxWorldRow;
    
    // FPS
    int FPS = 60;

    TileHandler th = new TileHandler(this);
    KeyHandler kh = new KeyHandler();
    Sound sound = new Sound();
    // WĄTKI:
    // wątek gry:
    Thread gameThread;
    // wątek muzyki:
    

    public CollisionHandler collisionHandler = new CollisionHandler(this);
    // wywołanie konstruktora obiektu od zarządzania obiektami
    public ObjectHandler objectHandler = new ObjectHandler(this);
    // wywołanie konstruktora postaci
    public Player player = new Player(this,kh);
    // wywołanie konstruktora objektu max 10 objektow wyświetlanych w jednym momencie
    public THEObject obj[] = new THEObject[10];


    // konstruktor klasy
    public GameBoard(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);
    }

    public void gameSetup(){
        objectHandler.setObject();

        playMusic();
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

        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null){
                obj[i].drawCar(g2,this);
            }
        }

        player.draw(g2);

        g2.dispose();
    }

    public void playMusic(/*int i*/){
        sound.setFile(/*i*/);
        sound.play();
        sound.loop();
    }

    public void stopMusic(){
        sound.stop();
    }

    public void playSE(/*int i*/){
        sound.setFile(/*i*/);
        sound.play();
    }

}
