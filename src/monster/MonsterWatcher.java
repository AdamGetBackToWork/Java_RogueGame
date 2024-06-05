// Podklasa Entity, jedna z jej realizacji 
// Stworzona do reprezentacji potworow

package monster;

// importy javy
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

// importy projektowe
import entity.Entity;
import main.GameBoard;

// cialo klasy
public class MonsterWatcher extends Entity{

    // konstruktor klasy
    public MonsterWatcher(GameBoard gb){
        
        // wywolanie z klasy parent powiazania gameboarda
        super(gb);

        // pola potwora
        direction = "down";
        name = "Watcher";
        moveSpeed = 1;
        maxHP = 4;
        HP = maxHP;
        entityType = 1;

        // okreslenia hitboxow
        solidRectangle = new Rectangle(0,0,gb.finalTileSize,gb.finalTileSize);
        solidRectangle.x = 5;
        solidRectangle.y = 5;
        solidRectangle.width = 2*gb.finalTileSize - 15;
        solidRectangle.height = 2*gb.finalTileSize - 15;

        solidAreaX = solidRectangle.x;
        solidAreaY = solidRectangle.y;

        // pobranie grafiki i ustawienie wydarzen
        getMonsterGraphic();
        setAction();
    }

    // metoda pobierania grafiki potwora
    public void getMonsterGraphic(){

        File file01 = new File("res\\\\monster\\\\monster.png");

        try {
            imageMonster = ImageIO.read(file01); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // metoda ustawienia akcji chodzenia potwora
    public void setAction(){

        // inkrementacja przy kazdym wywolaniu 
        // pole do okreslenia wstrzymania kierunku poruszania sie potwora
        actionLockCounter++;

        // przy odczekaniu 2s, ponowne losowanie kierunku, dalej poruszanie w tym kierunku
        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }
    }
}
