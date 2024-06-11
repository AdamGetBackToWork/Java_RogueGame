// Podklasa Entity, jedna z jej realizacji 
// Stworzona do reprezentacji postaci gracza

package entity;

// importy javy
import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// importy projektowe
import main.GameBoard;
import main.KeyHandler;

public class Player extends Entity{
    
    // w celu przyjmowania inputu z klawiatury (do zmiany sprite'a gracza)
    KeyHandler kh; 

    // niezmienne rozmiary ekranu
    public final int screenX;
    public final int screenY;

    // flagi statusu postaci gracza
    int hasHeart = 0;
    int hasGun = 0;

    // czesto zmieniany bufor obrazu gracza
    private volatile BufferedImage lastDrawnSprite; 

    // konstruktor klasy
    public Player(GameBoard gb, KeyHandler kh){
        
        super(gb);
        this.kh = kh; 

        screenX = gb.screenWidth/2 - (gb.finalTileSize/2);
        screenY = gb.screenHeight/2 - (gb.finalTileSize/2);

        solidRectangle = new Rectangle(8, 16, gb.finalTileSize/2, gb.finalTileSize/2);

        solidAreaX = solidRectangle.x;
        solidAreaY = solidRectangle.y;

        // ustawienia bazowe - pobranie grafiki
        setDefault();
        getPlayerGraphic();

        // od czegos trzeba zaczac (od jakiegos obrazka ;). )
        lastDrawnSprite = down1;
    }

    // metoda do pobierania grafiki gracza
    public void getPlayerGraphic(){
        
        // chodzenie do gory - plecki gracza
        File file01 = new File("res\\player\\back\\back1.png");
        File file02 = new File("res\\player\\back\\back2.png");
        File file03 = new File("res\\player\\back\\back1.png");
        File file04 = new File("res\\player\\back\\back4.png");

        // chodzenie do dolu - front gracza
        File file11 = new File("res\\player\\front\\front1.png");
        File file12 = new File("res\\player\\front\\front2.png");
        File file13 = new File("res\\player\\front\\front1.png");
        File file14 = new File("res\\player\\front\\front4.png");

        // chodzenie w lewo - lewa strona gracza
        File file21 = new File("res\\player\\left\\left1.png");
        File file22 = new File("res\\player\\left\\left2.png");
        File file23 = new File("res\\player\\left\\left1.png");
        File file24 = new File("res\\player\\left\\left4.png");

        // chodzenie w prawo - prawa strona gracza
        File file31 = new File("res\\player\\right\\right1.png");
        File file32 = new File("res\\player\\right\\right2.png");
        File file33 = new File("res\\player\\right\\right1.png");
        File file34 = new File("res\\player\\right\\right4.png");

        // try-catch do wczytywania obrazow z projektu
        try {

            up1 = ImageIO.read(file01); 
            up2 = ImageIO.read(file02);
            up3 = ImageIO.read(file03);
            up4 = ImageIO.read(file04);

            down1 = ImageIO.read(file11);
            down2 = ImageIO.read(file12);
            down3 = ImageIO.read(file13);
            down4 = ImageIO.read(file14);

            left1 = ImageIO.read(file21);
            left2 = ImageIO.read(file22);
            left3 = ImageIO.read(file23);
            left4 = ImageIO.read(file24);

            right1 = ImageIO.read(file31);
            right2 = ImageIO.read(file32);
            right3 = ImageIO.read(file33);
            right4 = ImageIO.read(file34);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metoda do przywracania bazowych ustawien gracza
    public void setDefault(){
        worldX = gb.finalTileSize * 10;
        worldY = gb.finalTileSize * 15;
        moveSpeed = 4;
        direction = "down";
        maxHP = 10;
        HP = maxHP;
    }

    // metoda do aktualizacji gracza, w tym:
    //      - kolizji
    //      - grafiki
    //      - wystapienia zdarzenia
    public void update(){

        // input z klawaiatury przerzucony na kierunkowosc
        if (kh.upPress == true || kh.downPress == true || kh.leftPress == true || kh.rightPress == true){
            if(kh.upPress == true){
                direction = "up";
            }
            else if(kh.downPress == true){
                direction = "down";
            }
            else if(kh.leftPress == true){
                direction = "left";
            }
            else if(kh.rightPress == true){
                direction = "right";
            }

            // inkrementacja grafiki - sprite'a gracza
            spriteCount++;

            // bazowe ustawienie stanu kolizji
            collisionState = false;

            // sprawdzanie kolizji z blokami
            gb.collisionHandler.checkTile(this);

            // interakcja z potworami - wywolanie sprawdzenia czy nie weszlismy z nimi w interakcje
            int monIndex = gb.collisionHandler.checkEntity(this, gb.monster);
            interactMonster(monIndex);

            //
            int objIndex = gb.collisionHandler.checkObject(this, true);
    
            // sprawdzanie wystapienia eventu - nie wiem jakiego, cos mialem na mysli juz zapomnialem...
            gb.eh.checkEvent();
    
            // jesli nie dotykamy nic (nie wystepuje kolizja) to zmiana pozycji swiata wzgledem postaci
            if(collisionState == false){
                switch(direction){
                    case "up":
                        worldY -= moveSpeed;
                        break;
                    case "down":
                        worldY += moveSpeed;
                        break;
                    case "left":
                        worldX -= moveSpeed;
                        break;
                    case "right":
                        worldX += moveSpeed;
                        break;
                    default:
                        break;
                }
            }
        }

        // przemiatanie roznych grafik w celu animacji ruchu
        if(spriteCount > 8){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 3;
            }
            else if(spriteNum == 3){
                spriteNum = 4;
            }
            else if(spriteNum == 4){
                spriteNum = 1;
            }
            //System.out.println(spriteNum);
            spriteCount = 0;
        }

        // obsluga nietykalnosci
        if(immune == true){
            immuneCount++;
            if(immuneCount > 60){
                immune = false;
                immuneCount = 0;
            }
        }
    }

    // metoda na przyszlosc haha, do podnoszenia broni i dodania zycia :)
    public void takeObject(int i){

        if(i != 100){
            String objectName = gb.obj[i].name;

            switch(objectName){
                case "Heart":
                    hasHeart++;
                    gb.obj[i] = null;
                break;
                case "Gun":
                    hasGun++;
                    gb.obj[i] = null;
                break;
            }
            gb.obj[i] = null;
        }
    }

    // metoda do wywolania sprawdzenia kolizji z potworami - w celu zastosowania nietykalnosci
    public void interactMonster(int monIndex){

        if(monIndex != 100){
            if(immune == false){
                gb.hittaken.play();
                HP -= 1;
                immune = true;
            }
        }

    }

    // metoda do rysowania grafiki gracza, w zaleznosci od szybko zmiennej spriteNum,
    // zmieniamy sprite'a by zasymulowac animacje chodzenia 
    public void draw(Graphics2D g2){

        BufferedImage image = null;

        switch(direction){
        case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            if(spriteNum == 3){
                image = up3;
            }
            if(spriteNum == 4){
                image = up4;
            }
            break;
        case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            if(spriteNum == 3){
                image = down3;
            }
            if(spriteNum == 4){
                image = down4;
            }
            break;
        case "left":
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            if(spriteNum == 3){
                image = left3;
            }
            if(spriteNum == 4){
                image = left4;
            }
            break;
        case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            if(spriteNum == 3){
                image = right3;
            }
            if(spriteNum == 4){
                image = right4;
            }
            break;
        default:
            // dodane, by nie wracac do "down1" po puszczeniu klawisza, nie wiem czy to super optymalne ale dziala :)
            image = lastDrawnSprite;
            break;                
        }

        // naniesienie przezroczystosci na gracza
        if(immune == true){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
        }

        // No i rysowanie
        g2.drawImage(image,screenX,screenY - 1*gb.finalTileSize,gb.finalTileSize,gb.finalTileSize,null);
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER,1f));
        lastDrawnSprite = image;
    }
}
