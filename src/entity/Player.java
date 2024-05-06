package entity;

// package Projekt.src.entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
// import java.awt.Color;

import javax.imageio.ImageIO;

import background.Tile;
import main.GameBoard;
import main.KeyHandler;

public class Player extends Entity{
    
    GameBoard gb;
    KeyHandler kh; 

    public final int screenX;
    public final int screenY;

    private volatile BufferedImage lastDrawnSprite; 

    public Player(GameBoard gb, KeyHandler kh){
        
        this.gb = gb;
        this.kh = kh; 

        screenX = gb.screenWidth/2 - (gb.finalTileSize/2);
        screenY = gb.screenHeight/2 - (gb.finalTileSize/2);

        solidRectangle = new Rectangle(8, 16, gb.finalTileSize/2, gb.finalTileSize/2);

        setDefault();
        getPlayerGraphic();

        lastDrawnSprite = down1;
    }

    public void getPlayerGraphic(){
        
        // String testPath1 = "res\\player\\mafia1_up_1.png";
        // String testPath2 = "res\\player\\mafia1_down_1.png";
        // String testPath3 = "res\\player\\mafia1_left_1.png";
        // String testPath4 = "res\\player\\mafia1_right_1.png";

        // Going up - back of the player
        File file01 = new File("res\\player\\back\\back1.png");
        File file02 = new File("res\\player\\back\\back2.png");
        File file03 = new File("res\\player\\back\\back1.png");
        File file04 = new File("res\\player\\back\\back4.png");

        // Going down - front of the player
        File file11 = new File("res\\player\\front\\front1.png");
        File file12 = new File("res\\player\\front\\front2.png");
        File file13 = new File("res\\player\\front\\front1.png");
        File file14 = new File("res\\player\\front\\front4.png");

        // Going left - left side of the player
        File file21 = new File("res\\player\\left\\left1.png");
        File file22 = new File("res\\player\\left\\left2.png");
        File file23 = new File("res\\player\\left\\left1.png");
        File file24 = new File("res\\player\\left\\left4.png");

        // Going right - right side of the player
        File file31 = new File("res\\player\\right\\right1.png");
        File file32 = new File("res\\player\\right\\right2.png");
        File file33 = new File("res\\player\\right\\right1.png");
        File file34 = new File("res\\player\\right\\right4.png");

        try {
            // up1 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            // up2 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            // up3 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            // up4 = ImageIO.read(getClass().getClassLoader().getResource(testPath2));

            // down1 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            // down2 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            // down3 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            // down4 = ImageIO.read(getClass().getClassLoader().getResource(testPath1));

            // left1 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            // left2 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            // left3 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            // left4 = ImageIO.read(getClass().getClassLoader().getResource(testPath3));

            // right1 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            // right2 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            // right3 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            // right4 = ImageIO.read(getClass().getClassLoader().getResource(testPath4));

            up1 = ImageIO.read(file01); 
            up2 = ImageIO.read(file02);
            up3 = ImageIO.read(file03);
            up4 = ImageIO.read(file04);

            down1 = ImageIO.read(file11);
            down2 = ImageIO.read(file12);
            down3 = ImageIO.read(file13);
            down4 = ImageIO.read(file14);

            left1 = ImageIO.read(file21);
            left2 = ImageIO.read(file22);
            left3 = ImageIO.read(file23);
            left4 = ImageIO.read(file24);

            right1 = ImageIO.read(file31);
            right2 = ImageIO.read(file32);
            right3 = ImageIO.read(file33);
            right4 = ImageIO.read(file34);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setDefault(){
        worldX = gb.finalTileSize * 17;
        worldY = gb.finalTileSize * 12;
        moveSpeed = 4;
        direction = "down";
    }

    public void update(){
        if(kh.upPress == true){
            direction = "up";
        }
        else if(kh.downPress == true){
            direction = "down";
        }
        else if(kh.leftPress == true){
            direction = "left";
        }
        else if(kh.rightPress == true){
            direction = "right";
        } else {
            direction = "none";
        }

        if (kh.upPress == true || kh.downPress == true || kh.leftPress == true || kh.rightPress == true){
            spriteCount++;
        }
            
        collisionState = false;
        gb.collisionHandler.checkTile(this);

        if(collisionState == false){
            switch(direction){
                case "up":
                    worldY -= moveSpeed;
                    break;
                case "down":
                    worldY += moveSpeed;
                    break;
                case "left":
                    worldX -= moveSpeed;
                    break;
                case "right":
                    worldX += moveSpeed;
                    break;
            }
        }

        if(spriteCount > 8){
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
            image = lastDrawnSprite;
            break;                
        }
        g2.drawImage(image,screenX,screenY,gb.finalTileSize,gb.finalTileSize,null);
        lastDrawnSprite = image;
    }
}
