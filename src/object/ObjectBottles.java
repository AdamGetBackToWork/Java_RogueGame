package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectBottles extends THEObject {

    public ObjectBottles(){
        name = "Bottles";
        File imageFile = new File("res\\objects\\street_elem\\bottles.png");
        solidArea.width = 12;
        solidArea.height = 12;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
