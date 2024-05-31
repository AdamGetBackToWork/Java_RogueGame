package object;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import main.GameBoard;

public class ObjectHeart extends THEObject {

    GameBoard gb;

    public ObjectHeart(GameBoard gb){

        this.gb = gb;
        File imageFile1 = new File("res\\objects\\hearts\\full_heart.png");
        File imageFile2 = new File("res\\objects\\hearts\\half_heart.png");
        File imageFile3 = new File("res\\objects\\hearts\\empty_heart.png");

        name = "Heart";
        try {
            image = ImageIO.read(imageFile1);
            image2 = ImageIO.read(imageFile2);
            image3 = ImageIO.read(imageFile3);
        }catch(IOException e){
            e.printStackTrace();
        }

    }
}