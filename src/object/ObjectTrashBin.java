// Klasa do tworzenia smietnikow
// dziedziczy po THEObject

package object;

// importy javy
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// cialo klasy 
public class ObjectTrashBin extends THEObject {

    // konstruktor klaasy
    public ObjectTrashBin(){
        name = "TrashBin";
        File imageFile = new File("res\\objects\\street_elem\\trashbin.png");
        
        // okreslenie hitboxow
        solidAreaX = 0;
        solidAreaY = 4;
        solidArea.width = 48;
        solidArea.height = 58;

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
