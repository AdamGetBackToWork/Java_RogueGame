// Klasa do tworzenia obiektow broni
// dziedziczy po THEObject

package object;

// importy javy
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// cialo klasy
public class ObjectGun extends THEObject {
    
    // konstruktor klasy
    public ObjectGun(){
        
        // pola obiektu
        name = "Gun";
        File imageFile = new File("res\\objects\\weapons\\magnum.png");
        solidArea.width = 24;

        // zaladowanie grafiki
        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        // troche nie potrzebne, ale kolizja, moze sie przydac kiedys
        collision = true;
    }

}