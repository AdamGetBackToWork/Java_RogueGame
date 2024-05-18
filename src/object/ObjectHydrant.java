package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectHydrant extends THEObject {

    public ObjectHydrant(){
        name = "Hydrant";
        File imageFile = new File("res\\objects\\street_elem\\hydrant.png");
        solidArea.width = 36;
        solidArea.height = 48;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
