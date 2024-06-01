package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GameBoard;

public class THEObject {
    public int worldX;
    public int worldY;
    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public Rectangle solidArea = new Rectangle(0,0,48,48);
    public int solidAreaX = 0;
    public int solidAreaY = 0;

    public void drawCar(Graphics2D g2, GameBoard gb){

        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1*gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 3 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 3 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 3* gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 3 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, 3*gb.finalTileSize, gb.finalTileSize, null);
        }
    }

    public void drawGun(Graphics2D g2, GameBoard gb){
        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1 * gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 1 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 1 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 1 * gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 1 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, 1 * gb.finalTileSize, gb.finalTileSize, null);
        }
    }

    public void drawLamp(Graphics2D g2, GameBoard gb){
        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1 * gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 1 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 1 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 2 * gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 2 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, gb.finalTileSize, 3 * gb.finalTileSize, null);
        }
    }

    // method for drawing a 1x1 object like bin, bottles, or hydrant.
    public void draw(Graphics2D g2, GameBoard gb){
        int screenX = worldX - gb.player.worldX + gb.player.screenX;
        int screenY = worldY - gb.player.worldY + gb.player.screenY - 1 * gb.finalTileSize;

        if(worldX > gb.player.worldX - gb.player.screenX - 1 * gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + 1 * gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - 1 * gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + 1 * gb.finalTileSize){
                    g2.drawImage(image, screenX, screenY, gb.finalTileSize, gb.finalTileSize, null);
        }
    }
}

