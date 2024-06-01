package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectTrashBin extends THEObject {

    public ObjectTrashBin(){
        name = "TrashBin";
        File imageFile = new File("res\\objects\\street_elem\\trashbin.png");
        
        solidAreaX = 0;
        solidAreaY = 4;
        solidArea.width = 48;
        solidArea.height = 58;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
