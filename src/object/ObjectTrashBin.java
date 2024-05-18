package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectTrashBin extends THEObject {

    public ObjectTrashBin(){
        name = "TrashBin";
        File imageFile = new File("res\\objects\\street_elem\\trashbin.png");
        solidArea.width = 24;
        solidArea.height = 36;

        try {
            image = ImageIO.read(imageFile);
        }catch(IOException e){
            e.printStackTrace();
        }
        collision = true;
    }

}
