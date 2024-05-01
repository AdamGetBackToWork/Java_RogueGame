package Projekt.src.entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.Color;

import javax.imageio.ImageIO;

import Projekt.src.main.GameBoard;
import Projekt.src.main.KeyHandler;

public class Player extends Entity{
    
    GameBoard gb;
    KeyHandler kh; 

    public Player(GameBoard gb, KeyHandler kh){
        
        this.gb = gb;
        this.kh = kh; 

        setDefault();
        getPlayerGraphic();
    }

    public void getPlayerGraphic(){
        
        String testPath1 = "Projekt\\res\\player\\mafia1_up_1.png";
        String testPath2 = "Projekt\\res\\player\\mafia1_down_1.png";
        String testPath3 = "Projekt\\res\\player\\mafia1_left_1.png";
        String testPath4 = "Projekt\\res\\player\\mafia1_right_1.png";

        try {
            up1 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            up2 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            up3 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            up4 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));

            down1 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            down2 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            down3 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            down4 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));

            left1 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            left2 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            left3 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            left4 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));

            right1 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            right2 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            right3 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            right4 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefault(){
        x = 100;
        y = 100;
        moveSpeed = 4;
        direction = "down";
    }



    public void update(){
        if(kh.upPress == true){
            direction = "up";
            y -= moveSpeed;
        }
        else if(kh.downPress == true){
            direction = "down";
            y += moveSpeed;
        }
        else if(kh.leftPress == true){
            direction = "left";
            x -= moveSpeed;
        }
        else if(kh.rightPress == true){
            direction = "right";
            x += moveSpeed;
        }

        if (kh.upPress == true || kh.downPress == true || kh.leftPress == true || kh.rightPress == true){
            spriteCount++;
        }
            
        if(spriteCount > 14){
            if(spriteNum == 1){
                spriteNum = 2;
            }
            else if(spriteNum == 2){
                spriteNum = 3;
            }
            else if(spriteNum == 3){
                spriteNum = 4;
            }
            else if(spriteNum == 4){
                spriteNum = 1;
            }
            System.out.println(spriteNum);
            spriteCount = 0;
        }

    }

    public void draw(Graphics2D g2){

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gb.finalTileSize, gb.finalTileSize);

        BufferedImage image = null;

        switch(direction){
        case "up":
            if(spriteNum == 1){
                image = up1;
            }
            if(spriteNum == 2){
                image = up2;
            }
            if(spriteNum == 3){
                image = up3;
            }
            if(spriteNum == 4){
                image = up4;
            }
            break;
        case "down":
            if(spriteNum == 1){
                image = down1;
            }
            if(spriteNum == 2){
                image = down2;
            }
            if(spriteNum == 3){
                image = down3;
            }
            if(spriteNum == 4){
                image = down4;
            }
            break;
        case "left":
            if(spriteNum == 1){
                image = left1;
            }
            if(spriteNum == 2){
                image = left2;
            }
            if(spriteNum == 3){
                image = left3;
            }
            if(spriteNum == 4){
                image = left4;
            }
            break;
        case "right":
            if(spriteNum == 1){
                image = right1;
            }
            if(spriteNum == 2){
                image = right2;
            }
            if(spriteNum == 3){
                image = right3;
            }
            if(spriteNum == 4){
                image = right4;
            }
            break;
        default:
            image = down1;
            break;                
        }
        g2.drawImage(image,x,y,gb.finalTileSize,gb.finalTileSize,null);
    }
}
