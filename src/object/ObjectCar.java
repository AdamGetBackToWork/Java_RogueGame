package object;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ObjectCar extends THEObject {

    public ObjectCar(int cartype, boolean facingright) {
        name = "Car";
        File imageFile;
        switch (cartype) {
            case 1:
                if(facingright)imageFile = new File("res\\objects\\cars\\car1-right.png");
                else imageFile = new File("res\\objects\\cars\\car1.png");
                break;
            case 2:
                if(facingright)imageFile = new File("res\\objects\\cars\\car2-right.png");
                else imageFile = new File("res\\objects\\cars\\car2.png");
                break;
            case 3:
                if(facingright)imageFile = new File("res\\objects\\cars\\car3-right.png");
                else imageFile = new File("res\\objects\\cars\\car3.png");
                break;
            default:
                imageFile = new File("res\\objects\\cars\\car1.png");
        }
        

        // Object_Car.solidArea(0,0,48,150);
        solidArea.width = 144;
        solidArea.height = 48;

        try {
            image = ImageIO.read(imageFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
