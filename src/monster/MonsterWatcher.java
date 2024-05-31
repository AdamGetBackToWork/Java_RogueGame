package monster;

import java.awt.Rectangle;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import org.w3c.dom.css.Rect;

import entity.Entity;
import main.GameBoard;

public class MonsterWatcher extends Entity{

    public MonsterWatcher(GameBoard gb){
        super(gb);

        direction = "down";
        name = "Watcher";
        moveSpeed = 1;
        maxHP = 4;
        HP = maxHP;

        solidRectangle = new Rectangle(0,0,gb.finalTileSize,gb.finalTileSize);
        solidRectangle.x = 3;
        solidRectangle.y = 18;
        solidRectangle.width = 42;
        solidRectangle.height = 42;

        solidAreaX = solidRectangle.x;
        solidAreaY = solidRectangle.y;

        getMonsterGraphic();
        setAction();
    }

    public void getMonsterGraphic(){
        File file01 = new File("res\\\\monster\\\\monster.png");
        try {
            imageMonster = ImageIO.read(file01); 

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setAction(){

        actionLockCounter++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1;

            if(i <= 25){
                direction = "up";
            }
            if(i > 25 && i <= 50){
                direction = "down";
            }
            if(i > 50 && i <= 75){
                direction = "left";
            }
            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }

    // public void getImage(){
    //     File monsterImage1 = new File("res\\monster\\monster.png");
    //     image = setup(monsterImage1);
    // }
}
