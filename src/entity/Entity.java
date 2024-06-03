package entity;

import java.awt.Graphics2D;

// package Projekt.src.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.Path;

import javax.imageio.ImageIO;

import main.GameBoard;

public class Entity {
    
    GameBoard gb;

    public int worldX, worldY;
    public int screenX, screenY;
    public int moveSpeed;
    // dla gracza
    public BufferedImage up1,up2,up3,up4,down1,down2,down3,down4,left1,left2,left3,left4,right1,right2,right3,right4;
    // dla potwora
    public BufferedImage imageMonster;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle solidRectangle;
    public int solidAreaX, solidAreaY;
    public boolean collisionState = false;
    public String name;
    public boolean immune = false;
    public int immuneCount = 0;
    public int actionLockCounter = 0;
    public int entityType = 1; //Monster = 1

    public int maxHP;
    public int HP;

    public Entity(GameBoard gb){
        this.gb = gb;
    }

    public void setAction(){

    }

    public void update(){
        setAction();

        collisionState = false;
        gb.collisionHandler.checkTile(this);
        gb.collisionHandler.checkEntity(this, gb.monster);
        boolean contact = gb.collisionHandler.checkPlayer(this);

        if(this.entityType == 1 && contact == true){
            if(gb.player.immune == false){
                gb.player.HP -= 1;
                gb.player.immune = true;
            }
        }
        
        if(collisionState == false){
            switch (direction) {
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
    }
    public void deleteInstance(Entity[] array, int index) {
        if (index >= 0 && index < array.length) {
            array[index] = null;
        } else {
            //System.out.println("Invalid index");
        }
    }
    public void takeDMG(int dmg){
        this.HP -= 1;
    }

    public void draw(Graphics2D g2){

        BufferedImage image = null;

        screenX = worldX - gb.player.worldX + gb.player.screenX;
        screenY = worldY - gb.player.worldY + gb.player.screenY - gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + gb.finalTileSize){

                    switch(direction){
                        case "up":
                            image = imageMonster;
                            break;
                        case "down":
                            image = imageMonster;
                            break;
                        case "left":
                            image = imageMonster;
                            break;
                        case "right":
                            image = imageMonster;
                            break;
                        default:
                            break;                
                        }
                    
                    g2.drawImage(image, screenX, screenY, 2*gb.finalTileSize, 2*gb.finalTileSize, null);
        }

    }

    public BufferedImage setup(File file){

        BufferedImage image = null;

        try{
            image = ImageIO.read(file); 
        }catch(IOException e){
            e.printStackTrace();
        }

        return image;
    }
}
