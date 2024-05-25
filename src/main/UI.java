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
    Font arial_10, arial_20,OCR_A_Extended_80,OCR_A_Extended_40, OCR_A_Extended_30, OCR_A_Extended_20;

    public BufferedImage playerSkin, gunImage;
    public String message = "";
    public boolean messageShowing = false;
    private int alpha = 127; // 50% transparent
    private Color transpColor= new Color(10,10,10, alpha);
    public int commandNum = 0;
    int subState = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GameBoard gb){
        this.gb = gb;
        arial_10 = new Font("Arial", Font.PLAIN, 10);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        OCR_A_Extended_40 = new Font("OCR A Extended",Font.PLAIN, 40);
        OCR_A_Extended_20 = new Font("OCR A Extended",Font.PLAIN, 20);
        OCR_A_Extended_30 = new Font("OCR A Extended",Font.PLAIN, 30);
        OCR_A_Extended_80 = new Font("OCR A Extended",Font.PLAIN, 80);

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


        if(gb.gameState == gb.titleState){
            drawTitleScreen();
        }
        if(gb.gameState == gb.creditsState){
            drawCreditsScreen();
        }
        if(gb.gameState == gb.playState){
            drawPlayScreen();
        }
        if(gb.gameState == gb.pauseState){
            drawPauseScreen();
        }
    } 

    private void drawPlayScreen(){
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

    }

    public void drawTitleScreen() {

        int temp2 = (int) (0.2*gb.finalTileSize);

        // Ustawienie kolorow ekranu poczatkowego
        Color bckgndColor = new Color(21,42,66);
        //Color bckgndColor = new Color(0,0,0);
        Color txtColor = new Color(247,214,169);

        // Rysowanie tla ekranu poczatkowego
        g2.setColor(bckgndColor);
        g2.fillRect(0,0,gb.screenWidth,gb.screenHeight);

        // Wyswietlenie tytulu gry
        g2.setFont(OCR_A_Extended_80);  
        String text = "Streets of Abbys";
        int x = centerText(text);
        int y = 3*gb.finalTileSize;
            //Cien tekstu 
            g2.setColor(new Color(0,0,0,127));
            g2.drawString(text, x+20, y+40);
            g2.setColor(Color.black);
            g2.drawString(text, x+10, y+20);
            // Tekst tekst
            g2.setColor(txtColor);
            g2.drawString(text, x, y);


        // Menu
            // nowa gra
            g2.setFont(OCR_A_Extended_40);
            text = "NEW GAME";
            x = centerText(text);
            y += 4*gb.finalTileSize;
            // wyswietlenie zaznaczenia poprzez dodanie cienia do wybieranej opcji
            if(commandNum == 0){
                g2.setColor(new Color(0,0,0,127));
                g2.drawString(text, x+10, y+20);
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+10);
            }
            g2.setColor(txtColor);
            g2.drawString(text, x, y);

            // kredyty - tworcy
            text = "CREDITS";
            x = centerText(text);
            y += temp2+gb.finalTileSize;
            // wyswietlenie zaznaczenia poprzez dodanie cienia do wybieranej opcji
            if(commandNum == 1){
                g2.setColor(new Color(0,0,0,127));
                g2.drawString(text, x+10, y+20);
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+10);
            }
            g2.setColor(txtColor);
            g2.drawString(text, x, y);

            // exit
            text = "QUIT";
            x = centerText(text);
            y += temp2+gb.finalTileSize;
            // wyswietlenie zaznaczenia poprzez dodanie cienia do wybieranej opcji
            if(commandNum == 2){
                g2.setColor(new Color(0,0,0,127));
                g2.drawString(text, x+10, y+20);
                g2.setColor(Color.black);
                g2.drawString(text, x+5, y+10);
            }
            g2.setColor(txtColor);
            g2.drawString(text, x, y);
    }

    // pauseState drawing
    public void drawPauseScreen(){

        // pause background
        g2.setColor(transpColor);
        g2.fillRect(0, 0, 16*gb.finalTileSize, 16*gb.finalTileSize);

        int frameX = gb.finalTileSize*4;
        int frameY = gb.finalTileSize;
        int frameWidth = gb.finalTileSize*8;
        int frameHeight = gb.finalTileSize*10;

        drawSubWindow(frameX,frameY,frameWidth,frameHeight);

        switch(subState){
            case 0: pause(frameX,frameY); break;
            case 1: break;
            case 2: break;
        }

        gb.kh.enterPress = false;
    }

    private void drawCreditsScreen() {
        int temp2 = (int) (0.2*gb.finalTileSize);

        // Ustawienie kolorow ekranu poczatkowego
        Color bckgndColor = new Color(21,42,66);
        //Color bckgndColor = new Color(0,0,0);
        Color txtColor = new Color(247,214,169);

        // Rysowanie tla credits
        g2.setColor(bckgndColor);
        g2.fillRect(0,0,gb.screenWidth,gb.screenHeight);

        // tekst
            // Autorze
            g2.setFont(OCR_A_Extended_40);  
            String text = "Authors:";
            int x = centerText(text);
            int y = 3*gb.finalTileSize;
            // Cienie
            g2.setColor(new Color(0,0,0,127));
            g2.drawString(text, x+10, y+20);
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+10);

            g2.setColor(txtColor);
            g2.drawString(text, x, y);


            g2.setFont(OCR_A_Extended_30); 
            // MICHAL
            text = "Michał Rostek";
            x = centerText(text);
            y += 2*gb.finalTileSize;
            // cienie
            g2.setColor(new Color(0,0,0,127));
            g2.drawString(text, x+10, y+20);
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+10);

            g2.setColor(txtColor);
            g2.drawString(text, x, y);

            // ADAM 
            text = "Adam Szajgin";
            x = centerText(text);
            y += gb.finalTileSize;
            // cienie
            g2.setColor(new Color(0,0,0,127));
            g2.drawString(text, x+10, y+20);
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+10);

            g2.setColor(txtColor);
            g2.drawString(text, x, y);

            // powrot do ekranu poczatkowego

            g2.setFont(OCR_A_Extended_20); 
            text = "Press ESC to go back";
            x = centerText(text);
            y += 4*gb.finalTileSize;
            // cienie
            g2.setColor(new Color(0,0,0,127));
            g2.drawString(text, x+10, y+20);
            g2.setColor(Color.black);
            g2.drawString(text, x+5, y+10);

            g2.setColor(txtColor);
            g2.drawString(text, x, y);

    }

    public void pause(int frameX, int frameY){

        int temp = (int)(0.5*gb.finalTileSize);
        int temp2 = (int)(0.4*gb.finalTileSize);

         // Draw top strings
         Color c = new Color(247,214,169);
         g2.setColor(c);
 
         g2.setFont(OCR_A_Extended_40);
         String text = "PAUSED"; 
         int textY = gb.screenHeight/2 - gb.finalTileSize*3 ;
         int textX = centerText(text);
         g2.drawString(text, textX, textY);
 
         // g2.setFont(OCR_A_Extended_20);
         // String optionsText = "Options";
         // int textY2 = gb.screenHeight/2 - gb.finalTileSize*2 ;
         // // int textX2 = centerText(optionsText);
         // int textX2 = frameX + gb.finalTileSize;
         // g2.drawString(optionsText, textX2, textY2);
 
         // Draw pause screen options:
         g2.setFont(OCR_A_Extended_20);
         int textX3 = frameX + gb.finalTileSize+temp;
             // Full screen
                 textY = gb.screenHeight/2 - gb.finalTileSize;
                 g2.drawString("Full Screen", textX3, textY);
                 if (commandNum == 0){
                     g2.drawString(">", textX3 - temp, textY);
                     if(gb.kh.enterPress == true){
                         if(gb.fullScreenState == false){
                             gb.fullScreenState = true;
                         }
                         else if (gb.fullScreenState == true){
                             gb.fullScreenState = false;
                         }
                     }
                 }
             // Sound
                 textY = gb.screenHeight/2;
                 g2.drawString("Music Volume", textX3, textY);
                 if (commandNum == 1){
                     g2.drawString(">", textX3 - temp, textY);
                 }
             // Sound fx
                 textY = gb.screenHeight/2 + gb.finalTileSize;
                 g2.drawString("FX Volume", textX3, textY);
                 if (commandNum == 2){
                     g2.drawString(">", textX3 - temp, textY);
                 }
             // Key bindings
                 textY = gb.screenHeight/2 + 2*gb.finalTileSize;
                 g2.drawString("Key Bindings", textX3, textY);
                 if (commandNum == 3){
                     g2.drawString(">", textX3 - temp, textY);
                 }
             // Restart game
                 textY = gb.screenHeight/2 + 3*gb.finalTileSize;
                 g2.drawString("Restart game", textX3, textY);
                 if (commandNum == 4){
                     g2.drawString(">", textX3 - temp, textY);
                 }
             // End game
                 textY = gb.screenHeight/2 + 4*gb.finalTileSize;
                 g2.drawString("End game", textX3, textY);
                 if (commandNum == 5){
                     g2.drawString(">", textX3 - temp, textY);
                 }
 
         // Next to them:
             // Check Box
             textX = textX3 + gb.finalTileSize*3 + temp;
             textY = gb.screenHeight/2 - gb.finalTileSize - temp2;
             g2.setStroke(new BasicStroke(3));
             g2.drawRect(textX, textY, gb.finalTileSize/2, gb.finalTileSize/2);
             if(gb.fullScreenState == true){
                 g2.fillRect(textX, textY,  gb.finalTileSize/2, gb.finalTileSize/2);
             }
             // Music bar
             textY = gb.screenHeight/2 - temp2;
             g2.setStroke(new BasicStroke(3));
             g2.drawRect(textX, textY, 2*gb.finalTileSize, gb.finalTileSize/2);
             // FX bar
             textY = gb.screenHeight/2 + gb.finalTileSize - temp2;
             g2.setStroke(new BasicStroke(3));
             g2.drawRect(textX, textY, 2*gb.finalTileSize, gb.finalTileSize/2);
    }

    public void drawSubWindow(int x, int y, int width, int height){
        Color c = new Color(83,78,102,127);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        // c = new Color(251, 237, 217);
        c = new Color(247,214,169);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 25, 25);
    }

    // method for getting the centerf for specific string
    private int centerText(String text){
        int textLength = (int)(g2.getFontMetrics().getStringBounds(text, g2).getWidth());
        int x = gb.screenWidth/2 - textLength/2;
        return x;
    }
}
