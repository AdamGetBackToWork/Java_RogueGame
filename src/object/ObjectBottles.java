// Klasa do tworzenia obiektow butelek
// dziedziczy po THEObject

package object;

// importy javy
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// cialo klasy
public class ObjectBottles extends THEObject {

    // konstruktor klasy
    public ObjectBottles(){

        // pola obiektu
        name = "Bottles";
        File imageFile = new File("res\\objects\\street_elem\\bottles.png");
        solidArea.width = 12;
        solidArea.height = 12;

        // wczytanie obrazkow
        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        // okreslenie kolizyjnosci
        collision = true;
    }

}
