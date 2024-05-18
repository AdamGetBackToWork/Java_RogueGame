package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectCar extends THEObject {

    public ObjectCar(){
        name = "Car";
        File imageFile = new File("res\\objects\\cars\\car1.png");
        // Object_Car.solidArea(0,0,48,150);
        solidArea.width = 144;
        solidArea.height = 48;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
