// Klasa do tworzenia obiektow lamp
// dziedziczy po THEObject

package object;

// importy javy
import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// cialo klasy
public class ObjectLamp extends THEObject {

    // konstruktor klasy
    public ObjectLamp() {
        name = "Lamp";
        File imageFile = new File("res\\objects\\street_elem\\lamp.png");
        
        // okreslenie hitboxow
        solidArea = new Rectangle(0, 0, 48, 52);
        solidAreaY = 102;

        // wczytanie grafiki
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        // okreslenie kolizyjnosci
        collision = true;
    }
}