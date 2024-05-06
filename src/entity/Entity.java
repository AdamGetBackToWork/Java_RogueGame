package entity;

// package Projekt.src.entity;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;

public class Entity {
    
    public int worldX, worldY;
    public int moveSpeed;
    public BufferedImage up1,up2,up3,up4,down1,down2,down3,down4,left1,left2,left3,left4,right1,right2,right3,right4;
    public String direction;
    public int spriteCount = 0;
    public int spriteNum = 1;
    public Rectangle solidRectangle;
    public boolean collisionState = false;
}
