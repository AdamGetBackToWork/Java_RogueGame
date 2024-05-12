package object;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Object_Car extends THEObject {

    public Object_Car(){
        name = "Car";
        File imageFile = new File("res\\object\\car1.png");
        // Object_Car.solidArea(0,0,48,150);
        solidArea.width = 146;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
