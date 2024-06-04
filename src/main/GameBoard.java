package main;

import background.TileHandler;
import entity.Entity;
import entity.Player;
import object.ObjectCar;
import object.THEObject;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.awt.geom.AffineTransform;


public class GameBoard extends JPanel implements Runnable {

    // Ustawienia ekranu i skalowanie 16-bitowej grafiki
    final int tileSize = 16;
    final int scale = 3; //

    // Wymiary ekranu
    public final int finalTileSize = scale * tileSize;

    // zeby ladnie pasowalo do full screen ratio, nalezy zmienic stosunek na 16:9
    // tutaj tego nie robie bo gra docelowo ma byc grana w malej wersji ale chce
    // pokazac opcje tego pelnego ekranu :)
    public final int screenColumns = 48; // 16 (was 48 before UI changes)
    public final int screenRows = 36; // 12 (was 36 before UI changes)

    // Przeskalowane wymiary ekranu 576p x 768p
    public final int screenHeight = tileSize * screenRows;
    public final int screenWidth = tileSize * screenColumns;

    public final int maxWorldCol = 50; // was 34
    public final int maxWorldRow = 50; // was 24

    // public final int worldWidth = finalTileSize * maxWorldCol;
    // public final int worldHeight = finalTileSize * maxWorldRow;

    // PELNY EKRAN zmienne
    int screenWidth2 = screenWidth;
    int screenHeight2 = screenHeight;
    BufferedImage tempScreen;
    Graphics2D g2;
    public boolean fullScreenState = false;

    // FPS
    int FPS = 60;

    TileHandler th = new TileHandler(this);
    KeyHandler kh = new KeyHandler(this);
    Sound sound = new Sound();

    // WĄTKI:
    // wątek gry:
    Thread gameThread;
    // wątek muzyki:

    public CollisionHandler collisionHandler = new CollisionHandler(this);
    // wywołanie konstruktora obiektu od zarządzania obiektami
    public ObjectHandler objectHandler = new ObjectHandler(this);
    // wywołanie konstruktora postaci
    public Player player = new Player(this, kh);
    // wywołanie konstruktora objektu max 50 objektow wyświetlanych w jednym
    // momencie
    public THEObject obj[] = new THEObject[50];
    // wywołanie konstruktora od powtworow
    public Entity monster[] = new Entity[2];
    // wywołanie konstruktora od User Interface
    public UI ui = new UI(this);
    // wywołanie konstruktora od Event Handlera
    public EventHandler eh = new EventHandler(this);

    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int creditsState = 5;
    public final int endState = 4;
    public final int menuKBState = 6;
    public final int maybeQuitState = 7;

    private final MousePosition mousePosition;
    private BufferedImage crosshair,crosshairHit, magnum;
    private MouseHandler mouseHandler;

    Color mylightorange = new Color(243, 174, 109);
    Color mylightyellow = new Color(247, 214, 169);
    Color myantiquewhite = new Color(251, 237, 217);

    // konstruktor klasy
    public GameBoard() {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(kh);
        this.setFocusable(true);

        this.mousePosition = new MousePosition();
        try {
            magnum = ImageIO.read(new File("res\\weapons\\magnum.png"));
            crosshair = ImageIO.read(new File("res\\misc\\crosshair.png")); // Load the crosshair image
            crosshairHit = ImageIO.read(new File("res\\misc\\crosshair_hit.png")); // Load the crosshair image
        } catch (IOException e) {
            //e.printStackTrace();
        }

        
    }

    public void gameSetup() {
        objectHandler.setObject();
        objectHandler.setMonster();
        playMusic();
        // tu zatrzymana jest muzyka i dlatego jesli zastanawiasz
        // sie czmu nie dziala to zakomentuj ta linijke nizej
        stopMusic();
        gameState = titleState;

        // tempScreen = new BufferedImage(screenWidth, screenHeight,
        // BufferedImage.TYPE_INT_ARGB);
        // g2 = (Graphics2D)tempScreen.getGraphics();
        // gameState = playState;
        // setFullScreen();
    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
        //mouse handler
        mouseHandler = new MouseHandler(this, this.mousePosition);
        new Thread(mouseHandler).start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long drawCount = 0;
        boolean wrongKeyMessagePrinted = false;

        while (gameThread != null) {
            currentTime = System.nanoTime();

            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if (delta >= 1) {
                update();
                repaint();

                // zapisanie rysowania do bufora
                // drawTempScreen();
                // z bufora narysowanie na ekranie
                // drawScreen();
                delta--;
            }

            // if (timer > 1000000000) {
            // System.out.println;
            // }

            // if(keyHandler.elsePress == true){
            // System.out.println("nacisnieto zly klawisz");
            // }
            if (kh.elsePress == true) {
                if (!wrongKeyMessagePrinted) {

                    System.out.println("Rostek ssie pałe");
                    wrongKeyMessagePrinted = true; // Set the flag to true after printing the message
                }
            } else {
                wrongKeyMessagePrinted = false; // Reset the flag if the correct key is pressed
            }

        }

    }

    public void update() {

        if (gameState == playState) {
            player.update();

            // aktualizowanie stanu potworow:
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].update();
                    if(monster[i].HP<=0){
                        monster[i].deleteInstance(monster, i);
                    }
                }
            }
        }
        if (gameState == pauseState) {
            // NIC NIE ROBIMY
        }

    }

    // public void drawTempScreen(){

    // if(gameState == titleState){
    // ui.draw(g2);
    // } else {
    // th.draw(g2);
    // for(int i = 0; i < obj.length; i++){
    // if(obj[i] != null){
    // obj[i].drawCar(g2,this);
    // }
    // }
    // player.draw(g2);
    // ui.draw(g2);
    // }
    // }

    // public void drawScreen(){

    // Graphics g = getGraphics();
    // g.drawImage(tempScreen, 0, 0, screenWidth2, screenHeight2,null);
    // g.dispose();

    // }

    // public void setFullScreen(){

    // // identyfikacja lokalnego ekranu
    // GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
    // GraphicsDevice gd = ge.getDefaultScreenDevice();
    // gd.setFullScreenWindow(Main.window);

    // //pobranie rozmiarow ekranu
    // screenWidth2 = Main.window.getWidth();
    // screenHeight2 = Main.window.getHeight();

    // }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        

        if (gameState == titleState) {
            ui.draw(g2);
        } else {
            th.draw(g2);
            player.draw(g2);
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] != null) {
                    switch (obj[i].getClass().getSimpleName()) {
                        case "ObjectCar":
                            obj[i].drawCar(g2, this);
                            break;
                        case "ObjectLamp":
                            obj[i].drawLamp(g2, this);
                            break;
                        default:
                            obj[i].draw(g2, this);
                            break;
                    }

                }
            }
            for (int i = 0; i < monster.length; i++) {
                if (monster[i] != null && monster[i].HP>0) {
                    monster[i].draw(g2);
                }
            }
            drawWeapons(g2);
            ui.draw(g2);
        }
        //g.setColor(Color.RED);
        //g.drawOval(mousePosition.getX()-25, mousePosition.getY()-25, 50, 50);

        g2.dispose();
    }
    private void drawWeapons(Graphics2D g2){
        g2.drawImage(crosshair, mousePosition.getX()-24, mousePosition.getY()-24,48,48,null);
        
        //obrot broni itp
        double angle = Math.atan2(mousePosition.getY() - player.screenY, mousePosition.getX() - player.screenX);
        int weaponX = (int) (player.screenX+25 * Math.cos(angle))+24;
        int weaponY = (int) (player.screenY+25 * Math.sin(angle))-24;
        AffineTransform transform = new AffineTransform();
        transform.translate(weaponX, weaponY); // Przesunięcie do środka ekranu
        transform.rotate(angle);

        // Ustawienie skali w osi Y w zależności od położenia myszki
        int wscale = (mousePosition.getX()  < player.screenX) ? -scale : scale;
        transform.scale(scale, wscale); // Skalowanie x4 w poziomie, x4 w pionie
        g2.drawImage(magnum, transform, null);
        //g2.drawImage(magnum, player.screenX, player.screenY-48, 48,48,null);
        int strokewidth=20;
        g2.setColor(Color.RED);
        g2.drawOval(weaponX, weaponY, 5,5);
        if(mouseHandler.getClickedStatus()){
            for (int i = 0; i < 20; i++) {
                g2.setColor(myantiquewhite);
                g2.setStroke(new BasicStroke(strokewidth));
                float alpha = (float) i / 20f; // Calculate opacity
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
                
                double x1 = player.screenX+24 + 72 * Math.cos(angle);
                double y1 = player.screenY + 72 * Math.sin(angle);
                g2.drawLine((int)x1,(int)y1,mousePosition.getX(), mousePosition.getY());
                g2.drawOval((int)x1-15, (int)y1-15, 30, 30);
                strokewidth-=1;
        }
        //g2.setColor(Color.RED);
        g2.drawImage(crosshairHit,mousePosition.getX()-24, mousePosition.getY()-24, 48,48,null);
    }
}

    public void playMusic(/* int i */) {
        sound.setFile(/* i */);
        sound.play();
        sound.loop();
    }

    public void stopMusic() {
        sound.stop();
    }

    public void playSE(/* int i */) {
        sound.setFile(/* i */);
        sound.play();
    }

}
