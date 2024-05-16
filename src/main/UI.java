package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.ObjectGun;

import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;

public class UI {

    GameBoard gb;
    Graphics2D g2;
    Font arial_10, arial_20,OCR_A_Extended_40;

    public BufferedImage playerSkin, gunImage;
    public String message = "";
    public boolean messageShowing = false;
    private int alpha = 127; // 50% transparent
    private Color transpColor= new Color(10,10,10, alpha);

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GameBoard gb){
        this.gb = gb;
        arial_10 = new Font("Arial", Font.PLAIN, 10);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        OCR_A_Extended_40 = new Font("OCR A Extended",Font.PLAIN, 40);

        ObjectGun gun = new ObjectGun();
        gunImage = gun.image;
    }

    private void getPlayerSkin(){
        File file11 = new File("res\\player\\front\\front1.png");
        try {
            playerSkin = ImageIO.read(file11);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMessage(String text){

        message = text;
        messageShowing = true;

    }

    // modify it for player sprite, gun sprite, ammo and stuff
    public void draw(Graphics2D g2){

        this.g2 = g2;

        getPlayerSkin();
        int temp = (int) (10*gb.finalTileSize + gb.finalTileSize/2);
        int temp2 = (int) (0.5*gb.finalTileSize);
        int temp3 = (int) (10*gb.finalTileSize + gb.finalTileSize/4);
        int temp4 = (int) (2*gb.finalTileSize - 0.5*gb.finalTileSize);
        int temp5 = (int) (0.25*gb.finalTileSize);

        g2.setFont(arial_10);
        g2.setColor(Color.ORANGE);
        // g2.drawString();

        g2.setColor(Color.BLACK); 
        g2.setStroke(new BasicStroke(2));
        g2.fillRect(0, 10*gb.finalTileSize, 20*gb.finalTileSize, 2*gb.finalTileSize); 
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 10*gb.finalTileSize, 20*gb.finalTileSize, 2);
        g2.drawRect(temp5,temp3, gb.finalTileSize + temp2, temp4);
        g2.drawRect(13 * gb.finalTileSize - temp5,temp3, 3*gb.finalTileSize, temp4);

        // for this to work we need to call it from for e.g Player class collision checker part
        playTime += (double) (1/60);
        g2.setColor(Color.ORANGE);
        g2.drawString("Time: "+dFormat.format(playTime), temp2 + 14*gb.finalTileSize, temp2);
        g2.drawString("FPS: "+dFormat.format(playTime), temp2 + 14*gb.finalTileSize, temp5+temp2);

        g2.drawImage(gunImage, 13 * gb.finalTileSize, temp3 + temp5, temp2 + 2*gb.finalTileSize, gb.finalTileSize,null);
        g2.drawImage(playerSkin,temp2, temp,gb.finalTileSize,gb.finalTileSize,null);

        if(gb.gameState == gb.playState){
            //nic null nada hula dusza
        } else if(gb.gameState == gb.pauseState){
            drawPauseScreen();
        }
    } 

    // pauseState drawing
    private void drawPauseScreen(){
        g2.setFont(OCR_A_Extended_40);
        String text = "PAUSED"; 
        int y = gb.screenHeight/2 ;
        int x = centerText(text);

        // pause text
        g2.setColor(Color.WHITE);
        g2.drawString(text, x, y);

        // pause background
        g2.setColor(transpColor);
        g2.fillRect(0, 0, 16*gb.finalTileSize, 16*gb.finalTileSize);
    }

    // method for getting the centerf for specific string
    private int centerText(String text){
        int textLength = (int)(g2.getFontMetrics().getStringBounds(text, g2).getWidth());
        int x = gb.screenWidth/2 - textLength/2;
        return x;
    }
}
