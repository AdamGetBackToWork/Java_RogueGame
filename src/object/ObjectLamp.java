package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectLamp extends THEObject {

    public ObjectLamp() {
        name = "Lamp";
        File imageFile = new File("res\\objects\\street_elem\\lamp.png");
        

        // Object_Car.solidArea(0,0,48,150);
        solidArea.width = 48;
        solidArea.height = 144;

        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

}

