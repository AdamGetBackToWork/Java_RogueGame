// Klasa do tworzenia obiektow hydrantow
// dziedziczy po THEObject

package object;

// importy javy
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// cialo klasy
public class ObjectHydrant extends THEObject {

    // konstruktor klasy
    public ObjectHydrant(){
        name = "Hydrant";
        File imageFile = new File("res\\objects\\street_elem\\hydrant.png");
        
        // okreslenie hitboxow
        solidArea.width = 36;
        solidArea.height = 48;

        // wczytanie grafiki 
        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }

        // orkeslenie kolizji
        collision = true;
    }
}
