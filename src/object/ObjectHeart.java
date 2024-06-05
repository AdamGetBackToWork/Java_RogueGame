// Klasa do tworzenia obiektow serc
// dziedziczy po THEObject

package object;

// importy javy
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

// importy projektowe
import main.GameBoard;

// cialo klasy
public class ObjectHeart extends THEObject {

    // jest to interkatywny obiekt wiec instancja rozgrywki (planszy)
    GameBoard gb;

    // konstruktor klasy
    public ObjectHeart(GameBoard gb){

        this.gb = gb;
        File imageFile1 = new File("res\\objects\\hearts\\full_heart.png");
        File imageFile2 = new File("res\\objects\\hearts\\half_heart.png");
        File imageFile3 = new File("res\\objects\\hearts\\empty_heart.png");

        name = "Heart";
        
        // wczytanie grafik
        try {
            image = ImageIO.read(imageFile1);
            image2 = ImageIO.read(imageFile2);
            image3 = ImageIO.read(imageFile3);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}