package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectGun extends THEObject {
    
    public ObjectGun(){
        name = "Gun";
        File imageFile = new File("res\\object\\car1.png");
        // Object_Car.solidArea(0,0,48,150);
        solidArea.width = 24;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}