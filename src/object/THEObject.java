// Klasa abstrakcyjna, tworzona jako blueprint dla klas po niej dziedziczacych
// UWAGA wiem ze klasa nie jest do konca zoptymalizowana, natomiast mielismy w trakcie 
// robienia gry, kilka pomyslow na obiekty oraz na interakcje z nimi, stad te dziwne podzialy, z pozoru nielogiczne (moze tez w istocie nielogiczne)

package object;

// importy javy
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

// importy projektowe
import main.GameBoard;

// cialo klasy
public class THEObject {
    public int worldX;
    public int worldY;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaX = 0;
    public int solidAreaY = 0;

    // meotda rysowania pojazow
    public void drawCar(Graphics2D g2, GameBoard gb){

        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1*gb.finalTileSize;

        // tutaj, jesli jest w zasiegu to rysujemy, ale trzeba dopasowac o dlugosc wozu - czyli x3 przesuniecie
        if(worldX > gb.player.worldX - gb.player.screenX - 3 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 3 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 3* gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 3 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, 3*gb.finalTileSize, gb.finalTileSize, null);
        }
    }

    // metoda do rysowania broni
    public void drawGun(Graphics2D g2, GameBoard gb){
        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1 * gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 1 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 1 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 1 * gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 1 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, 1 * gb.finalTileSize, gb.finalTileSize, null);
        }
    }

    // metoda do rysowania lamp
    public void drawLamp(Graphics2D g2, GameBoard gb){
        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1 * gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 1 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 1 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 2 * gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 2 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, gb.finalTileSize, 3 * gb.finalTileSize, null);
        }
    }

    // metoda do rysowania obiektow 1x1 jak butelki, kosze na smieci, etc.
    public void draw(Graphics2D g2, GameBoard gb){
        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1 * gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 1 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 1 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 1 * gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 1 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, gb.finalTileSize, gb.finalTileSize, null);
        }
    }
}

