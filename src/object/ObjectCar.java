// Klasa do tworzenia obiektow samochodow
// dziedziczy po THEObject

package object;

// importy javy
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// cialo klasy
public class ObjectCar extends THEObject {

    // konstruktor klasy
    public ObjectCar(int cartype, boolean facingright) {
        name = "Car";
        File imageFile;

        // switch case obrotu samochodow
        switch (cartype) {
            case 1:
                if(facingright)imageFile = new File("res\\objects\\cars\\car1-right.png");
                else imageFile = new File("res\\objects\\cars\\car1.png");
                break;
            case 2:
                if(facingright)imageFile = new File("res\\objects\\cars\\car2-right.png");
                else imageFile = new File("res\\objects\\cars\\car2.png");
                break;
            case 3:
                if(facingright)imageFile = new File("res\\objects\\cars\\car3-right.png");
                else imageFile = new File("res\\objects\\cars\\car3.png");
                break;
            default:
                imageFile = new File("res\\objects\\cars\\car1.png");
        }
        
        // okreslenie hitboxow
        solidArea.x = 48;
        solidArea.width = 144;
        solidArea.height = 48;

        // wczytanie grafiki
        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // okreslenie kolizji
        collision = true;
    }

}
