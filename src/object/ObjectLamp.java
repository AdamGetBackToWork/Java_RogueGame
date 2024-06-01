package object;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectLamp extends THEObject {

    public ObjectLamp() {
        name = "Lamp";
        File imageFile = new File("res\\objects\\street_elem\\lamp.png");
        
        solidArea = new Rectangle(0, 0, 48, 52);
        solidAreaY = 102;

        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

    

}
