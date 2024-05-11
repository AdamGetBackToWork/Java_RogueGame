package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Car extends THEObject {

    public Object_Car(){
        name = "Car";
        File imageFile = new File("res\\object\\car1.png");

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
    }

}
