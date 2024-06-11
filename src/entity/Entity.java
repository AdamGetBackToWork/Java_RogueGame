// Klasa Entity, jako parent klas wszelkich podmiotow. 
// Okresla ona framework do playera i monsterow, jest klasa abstrakcyjna

// package
package entity;

// importy javy
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// importy projektowe
import main.GameBoard;
import monster.MonsterWatcher;

public class Entity {

    GameBoard gb;

    // Deklaracje zmiennych
    // zmienne do pozycjonowania na planszy i w oknie gry
    public int worldX, worldY;
    public int screenX, screenY;

    // predkosc poruszania sie, kazda klasa dziedziczaca ma inna
    public int moveSpeed;

    // przygotowane bufory dla obrazow podklas
    // dla gracza
    public BufferedImage up1, up2, up3, up4, down1, down2, down3, down4, left1, left2, left3, left4, right1, right2,
            right3, right4;
    // dla potwora
    public BufferedImage imageMonster;

    // zmienne dla animacji chodzenia
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;

    // zmienne dla kolizji i pola "ciala" podmiotu (zeby okreslic hitboxy)
    public Rectangle solidRectangle;
    public int solidAreaX, solidAreaY;
    public boolean collisionState = false;

    // nazwa i zycie podmiotow
    public String name;
    public int maxHP;
    public int HP;

    // zmienna do okreslania nietykalnosci gracza po kolizji z potworem
    public boolean immune = false;
    public int immuneCount = 0;

    // zmienna do wstrzymania zmiany kierunku poruszania sie potwora
    public int actionLockCounter = 0;

    // podtyp klasy entity, w celu pozniejszego rozroznienia instancji klas
    // dziedziczących do np rysowania
    public int entityType = 1; // Monster = 1

    // konstruktor klasy - tylko przypisanie "planszy" instancji gry
    public Entity(GameBoard gb) {
        this.gb = gb;
    }

    // metoda do overwrite'a, jest jako "wzorzec" dla klas dziedziczacych, jesli
    // bedziemy chcieli wzbogacic gre o inne podmioty
    public void setAction() {
    }

    // metoda do aktualizowania stanu podmiotow
    public void update() {
        setAction();

        // sprawdzanie stanow kolizji, by np. potwor nie wchodzil na budynek...
        collisionState = false;
        gb.collisionHandler.checkTile(this);
        gb.collisionHandler.checkEntity(this, gb.monster);
        boolean contact = gb.collisionHandler.checkPlayer(this);

        // dodane w celu "nietracenia" calego zycia w 0,01 sekundy po dotknieciu potwora
        // przez gracza,
        // czyli zeby czlowiek raz dotknie - raz traci pol zycia, a nie od razu cale...
        if (this.entityType == 1 && contact == true) {
            if (gb.player.immune == false) {
                gb.player.HP -= 1;
                gb.player.immune = true;
            }
        }

        // poruszanie podmiotu, wykorzystywane przez albo keyhandlera, albo prosty
        // system imituajcy AI - losowanie kierunku przez potwory
        if (collisionState == false) {
            switch (direction) {
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
            }
        }
    }

    // metoda do usuwania z potworow z planszy po ich "zabiciu"
    public void deleteInstance(Entity[] array, int index) {

        // okreslenie poprawnosci podanego indeksu
        if (index >= 0 && index < array.length) {
            array[index] = null;
        } else {
            // System.out.println("Invalid index");
        }
        gb.ui.incMonsterCounter();
    }


    // metoda do obnizania poziomu zycia
    public void takeDMG(int dmg) {
        this.HP -= 1;
        gb.hittaken.play();
    }

    // metoda do rysowania potworow, wiemy ze powinna byc ona w klasie od potworow
    // wedle zasady podzialu odpowiedzialnosc S (SOLID)
    // Natomaist tutaj ona powstala na poczatku i tutaj poki co zostanie, poprawimy
    // dalej
    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        // dopasowanie pozycji na ekranie
        screenX = worldX - gb.player.worldX + gb.player.screenX;
        screenY = worldY - gb.player.worldY + gb.player.screenY - gb.finalTileSize;

        // optymalne rysowanie potwora, jesli jest w obrebie ekranu to jest rysowany,
        // jesli nie to nie
        // dodatkowo dopasowane do rozmiarow 2x2 potwora - stad te dwojki :)
        if (worldX > gb.player.worldX - 2 * gb.player.screenX - gb.finalTileSize &&
                worldX < gb.player.worldX + 2 * gb.player.screenX + gb.finalTileSize &&
                worldY > gb.player.worldY - 2 * gb.player.screenY - gb.finalTileSize &&
                worldY < gb.player.worldY + 2 * gb.player.screenY + gb.finalTileSize) {

            // baaaaardzo niepotrzebny switch case, ale jest on buforem miejsca na dalszy
            // rozwoj gry
            switch (direction) {
                case "up":
                    image = imageMonster;
                    break;
                case "down":
                    image = imageMonster;
                    break;
                case "left":
                    image = imageMonster;
                    break;
                case "right":
                    image = imageMonster;
                    break;
                default:
                    break;
            }

            // rysowanie obrazu potwora
            g2.drawImage(image, screenX, screenY, 2 * gb.finalTileSize, 2 * gb.finalTileSize, null);
        }

    }

    // metoda do przygotowania obrazu, tym razem jest to oddzielna metoda
    public BufferedImage setup(File file) {

        BufferedImage image = null;

        try {
            image = ImageIO.read(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return image;
    }
}
