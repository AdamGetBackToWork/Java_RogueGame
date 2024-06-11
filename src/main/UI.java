// Tutaj by za duzo pisac komentarzy, mozemy sie tym zajac przed obrona projektu, ale tak:

/*
 *  Klasa UI ma swojego switch case'a, dokladniej w metodzie "draw". Tam w zaleznosci od game State'u
 *  rysowane jest odpowiednie UI. Przez niezmiennosc stanu UI "pod spodem", mozemy rysowac jedno na 
 *  drugim. Kazda konkretna metoda wykrozsytywana do rysowanie konkretnego UI ma swoje "tempy" - czyli
 *  takie temporary wartosci, sluzace do przechowywania zrzutowanych na int wartosci odleglosci na 
 *  ekranie. 
 *  
 *  Poza metodami rysujacymi konkretne mozliwosci/stany UI, rowniez sa metody pobierajace grafiki w celu
 *  ich pozniejszego wyswietlenia na UI, oraz metody pomocnicze, jak np. metoda do centrowania tekstu, 
 *  lub metoda do wyswietlania dodatkowego okna.
 * 
 *  Poziom skomplikowania tej klasy jest duzy, zdajemy sobie z tego sprawe, jednak jesli bedziemy mieli 
 *  chwile, rozwiazemy to spaghetti i stworzymy ladniej opisana, re-uzywalna, klase z uporzadkowanymi
 *  metodami.
 * 
 *  Do tego czasu, to dziala, robi co ma robic, jest czesciowo hermetyzowalne i tyle :).
 */

package main;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.awt.Color;
import java.awt.Font;
import java.awt.BasicStroke;
import javax.swing.Timer;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import object.ObjectGun;
import object.ObjectHeart;
import object.THEObject;

public class UI {

    GameBoard gb;
    Graphics2D g2;
    Font arial_10, arial_20, OCR_A_Extended_80, OCR_A_Extended_40, OCR_A_Extended_30, OCR_A_Extended_20,OCR_A_Extended_15;

    public BufferedImage playerImage, gunImage, monsterImage, fullHeart, halfHeart, emptyHeart;
    public String message = "";
    public boolean messageShowing = false;
    private int alpha = 127; // 50% transparent
    private Color transpColor = new Color(10, 10, 10, alpha);
    public int commandNum = 0;
    public int commandNumTitle = 0;
    public int commandNumEnd = 0;

    private Timer messageTimer;
    private int messageDuration = 5000;

    private int monsterCounter = 0;

    // Ustawienie kolorow ekranu poczatkowego
    private Color bckgndColor = new Color(21, 42, 66);
    // Color bckgndColor = new Color(0,0,0);
    private Color txtColor = new Color(247, 214, 169);

    int subState = 0;

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    public UI(GameBoard gb) {
        this.gb = gb;
        arial_10 = new Font("Arial", Font.PLAIN, 10);
        arial_20 = new Font("Arial", Font.PLAIN, 20);
        OCR_A_Extended_40 = new Font("OCR A Extended", Font.PLAIN, 40);
        OCR_A_Extended_20 = new Font("OCR A Extended", Font.PLAIN, 20);
        OCR_A_Extended_30 = new Font("OCR A Extended", Font.PLAIN, 30);
        OCR_A_Extended_15 = new Font("OCR A Extended", Font.PLAIN, 15);
        OCR_A_Extended_80 = new Font("OCR A Extended", Font.PLAIN, 80);

        ObjectGun gun = new ObjectGun();
        gunImage = gun.image;
        THEObject heart = new ObjectHeart(gb);
        fullHeart = heart.image;
        halfHeart = heart.image2;
        emptyHeart = heart.image3;

        this.monsterCounter = gb.randomMonsterCount;

        // messageTimer = new Timer(messageDuration, new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {
        //         messageShowing = false;
        //         // Stop the timer when the message duration has elapsed
        //         messageTimer.stop();
        //         gb.repaint();
        //     }
        // });
        // messageTimer.setRepeats(false); 
        // startGame();
    }

    private void getPlayerGraphic() {
        File file11 = new File("res\\player\\front\\front1.png");
        try {
            playerImage = ImageIO.read(file11);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    

    private void getMonsterGraphic() {

        File file11 = new File("res\\monster\\monster.png");
        try {
            monsterImage = ImageIO.read(file11);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    // public void showMessage(String text) {
    //     message = text;
    //     messageShowing = true;
    //     messageTimer.start();
    // }

    // public void startGame() {
    //     // Start the game
    //     // Set a timer to show the message after 5 seconds
    //     Timer delayTimer = new Timer(5000, new ActionListener() {
    //         @Override
    //         public void actionPerformed(ActionEvent e) {
    //             showMessage("Your message here");
    //         }
    //     });
    //     delayTimer.setRepeats(false);
    //     delayTimer.start();
    // }

    // modify it for player sprite, gun sprite, ammo and stuff
    public void draw(Graphics2D g2) {

        this.g2 = g2;

        if (gb.gameState == gb.titleState) {
            drawTitleScreen();
        }
        if (gb.gameState == gb.creditsState) {
            drawCreditsScreen();
        }
        if (gb.gameState == gb.playState) {
            drawPlayScreen();
        }
        if (gb.gameState == gb.pauseState) {
            drawPlayScreen();
            drawPauseScreen();
        }
        if (gb.gameState == gb.endGameState) {
            drawPlayScreen();

            drawEndScreen();
        }
        // if(gb.gameState == gb.menuKBState){
        // drawMenuKBScreen();
        // }
        // if(gb.gameState == gb.maybeQuitState){
        // drawQuitQuestion();
        // }
    }

    private void drawPlayScreen() {
        getPlayerGraphic();
        getMonsterGraphic();
        int temp = (int) (10 * gb.finalTileSize + gb.finalTileSize / 2);
        int temp2 = (int) (0.5 * gb.finalTileSize);
        int temp3 = (int) (10 * gb.finalTileSize + gb.finalTileSize / 4);
        int temp4 = (int) (2 * gb.finalTileSize - 0.5 * gb.finalTileSize);
        int temp5 = (int) (0.25 * gb.finalTileSize);
        int temp6 = (int) (0.2 * gb.finalTileSize);

        g2.setFont(arial_10);
        g2.setColor(Color.ORANGE);

        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(2));
        g2.fillRect(0, 10 * gb.finalTileSize, 20 * gb.finalTileSize, 2 * gb.finalTileSize);
        g2.setColor(Color.WHITE);
        g2.fillRect(0, 10 * gb.finalTileSize, 20 * gb.finalTileSize, 2);
        g2.drawRect(temp5, temp3, gb.finalTileSize + temp2, temp4);
        g2.drawRect(13 * gb.finalTileSize - temp5, temp3, 3 * gb.finalTileSize, temp4);

        // zeby to dzialalo to trzeba to wywolac z np klasy Player collision checker'a
        playTime += (double) (1 / 60);
        g2.setColor(Color.ORANGE);
        g2.drawString("Time: " + dFormat.format(playTime), temp2 + 14 * gb.finalTileSize, temp2);
        g2.drawString("FPS: " + dFormat.format(playTime), temp2 + 14 * gb.finalTileSize, temp5 + temp2);

        g2.drawImage(gunImage, 13 * gb.finalTileSize, temp3 + temp5, temp2 + 2 * gb.finalTileSize, gb.finalTileSize,
                null);
        g2.drawImage(playerImage, temp2, temp, gb.finalTileSize, gb.finalTileSize, null);

        // Rysowanie serc - poziomu zycia gracza
        // Puste serca
        int x = 2 * temp2 + gb.finalTileSize;
        int y = temp;
        int i = 0;
        while (i < gb.player.maxHP / 2) {
            g2.drawImage(emptyHeart, x, y, null);
            i++;
            x += gb.finalTileSize;
        }
        // Obecne serca
        x = 2 * temp2 + gb.finalTileSize;
        y = temp;
        i = 0;
        while (i < gb.player.HP) {
            g2.drawImage(halfHeart, x, y, null);
            i++;
            if (i < gb.player.HP) {
                g2.drawImage(fullHeart, x, y, null);
            }
            i++;
            x += gb.finalTileSize;
        }

        // Rysowanie grafiki potwora oraz wyswietlanie ilosci zabitych potworow
        g2.drawImage(monsterImage, 11 * gb.finalTileSize + temp5, temp, gb.finalTileSize, gb.finalTileSize, null);
        g2.setFont(OCR_A_Extended_30);
        g2.setColor(txtColor);
        g2.drawString("X", 10 * gb.finalTileSize, temp + temp2 + temp5);


        // rysowanie ilosci zabitych potworow
        g2.drawString(String.valueOf(monsterCounter), 10 * gb.finalTileSize + temp2, temp + temp2 + temp5);

        g2.setFont(OCR_A_Extended_15);
        g2.setColor(txtColor);
        String text = "Objective: Find and exterminate monsters IN A GIVEN ORDER.";
        x = 2*gb.finalTileSize;
        y = temp;
        // x = centerText(text);
        // y = gb.finalTileSize;
        g2.drawString(text, x, y);
    }

    // private void drawMessage() {
    //     g2.setFont(OCR_A_Extended_20);
    //     g2.setColor(Color.YELLOW);

    //     g2.drawString(message, x, y);
    // }

    // setter ikrementujący wartość monster counter, wywolywany przy każdym pokonanym przeciwniku
    public void incMonsterCounter(){
        this.monsterCounter--;
    }

    public void drawEndScreen() {

        // pause background
        g2.setColor(transpColor);
        g2.fillRect(0, 0, 16 * gb.finalTileSize, 16 * gb.finalTileSize);

        int frameX = gb.finalTileSize * 4;
        int frameY = gb.finalTileSize;
        int frameWidth = gb.finalTileSize * 8;
        int frameHeight = gb.finalTileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        if (gb.player.HP == 0){
            drawEndChoice(frameX, frameY);
        } else {
            drawPositiveEndChoice(frameX,frameY);
        }


        gb.kh.enterPress = false;

    }

    public void drawPositiveEndChoice(int frameX, int frameY) {

        int temp = (int) (0.5 * gb.finalTileSize);
        int temp2 = (int) (0.4 * gb.finalTileSize);
        int temp3 = (int) (2 * gb.finalTileSize / 5);

        // Draw top strings
        Color c = new Color(247, 214, 169);
        g2.setColor(c);

        g2.setFont(OCR_A_Extended_40);
        String text = "YOU WIN!";
        int textY = gb.screenHeight / 2 - gb.finalTileSize * 3;
        int textX = centerText(text);
        g2.drawString(text, textX, textY);

        g2.setFont(OCR_A_Extended_30);
        
        text = "Congrats!";
        textY = gb.screenHeight / 2 - gb.finalTileSize * 1;
        textX = centerText(text);
        g2.drawString(text, textX, textY);

        // End game
        textY = gb.screenHeight / 2 + 3 * gb.finalTileSize;
        text = "Quit Game";
        int textX3 = centerText(text);
        g2.drawString(text, textX3, textY);
        if (commandNumEnd == 0) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                commandNumEnd = 0;
            }
        }

    }

    public void drawEndChoice(int frameX, int frameY) {

        int temp = (int) (0.5 * gb.finalTileSize);
        int temp2 = (int) (0.4 * gb.finalTileSize);
        int temp3 = (int) (2 * gb.finalTileSize / 5);

        // Draw top strings
        Color c = new Color(247, 214, 169);
        g2.setColor(c);

        g2.setFont(OCR_A_Extended_40);
        String text = "GAME OVER";
        int textY = gb.screenHeight / 2 - gb.finalTileSize * 3;
        int textX = centerText(text);
        g2.drawString(text, textX, textY);

        g2.setFont(OCR_A_Extended_30);
        int textX3 = centerText(text);
        // End game
        textY = gb.screenHeight / 2 + 2 * gb.finalTileSize;
        g2.drawString("Quit Game", textX3, textY);
        if (commandNumEnd == 0) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                commandNumEnd = 0;
            }
        }

    }

    public void drawTitleScreen() {

        int temp2 = (int) (0.2 * gb.finalTileSize);
        int temp = (int) (0.5 * gb.finalTileSize);

        // Rysowanie tla ekranu poczatkowego
        Color c = new Color(83, 78, 102, 127);
        g2.setColor(c);
        g2.fillRect(gb.finalTileSize, gb.finalTileSize, gb.screenWidth - 2 * gb.finalTileSize,
                gb.screenHeight - 2 * gb.finalTileSize);
        g2.setColor(transpColor);
        g2.fillRect(gb.finalTileSize, gb.finalTileSize, gb.screenWidth - 2 * gb.finalTileSize,
                gb.screenHeight - 2 * gb.finalTileSize);
        g2.setColor(bckgndColor);
        g2.setStroke(new BasicStroke(5));
        // g2.drawRoundRect(x, y, width, height, 25, 25);
        g2.drawRoundRect(temp, temp, gb.screenWidth - gb.finalTileSize, gb.screenHeight - gb.finalTileSize, temp, temp);

        // Wyswietlenie tytulu gry
        g2.setFont(OCR_A_Extended_80);
        String text = "Streets of Abbys";
        int x = centerText(text);
        int y = 3 * gb.finalTileSize;
        // Cien tekstu
        g2.setColor(new Color(0, 0, 0, 127));
        g2.drawString(text, x + 20, y + 40);
        g2.setColor(Color.black);
        g2.drawString(text, x + 10, y + 20);
        // Tekst tekst
        g2.setColor(txtColor);
        g2.drawString(text, x, y);

        // Menu
        // nowa gra
        g2.setFont(OCR_A_Extended_40);
        text = "NEW GAME";
        x = centerText(text);
        y += 4 * gb.finalTileSize;
        // wyswietlenie zaznaczenia poprzez dodanie cienia do wybieranej opcji
        if (commandNumTitle == 0) {
            g2.setColor(new Color(0, 0, 0, 127));
            g2.drawString(text, x + 10, y + 20);
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 10);
        }
        g2.setColor(txtColor);
        g2.drawString(text, x, y);

        // kredyty - tworcy
        text = "CREDITS";
        x = centerText(text);
        y += temp2 + gb.finalTileSize;
        // wyswietlenie zaznaczenia poprzez dodanie cienia do wybieranej opcji
        if (commandNumTitle == 1) {
            g2.setColor(new Color(0, 0, 0, 127));
            g2.drawString(text, x + 10, y + 20);
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 10);
        }
        g2.setColor(txtColor);
        g2.drawString(text, x, y);

        // exit
        text = "QUIT";
        x = centerText(text);
        y += temp2 + gb.finalTileSize;
        // wyswietlenie zaznaczenia poprzez dodanie cienia do wybieranej opcji
        if (commandNumTitle == 2) {
            g2.setColor(new Color(0, 0, 0, 127));
            g2.drawString(text, x + 10, y + 20);
            g2.setColor(Color.black);
            g2.drawString(text, x + 5, y + 10);
        }
        g2.setColor(txtColor);
        g2.drawString(text, x, y);
    }

    // pauseState drawing
    public void drawPauseScreen() {

        // pause background
        g2.setColor(transpColor);
        g2.fillRect(0, 0, 16 * gb.finalTileSize, 16 * gb.finalTileSize);

        int frameX = gb.finalTileSize * 4;
        int frameY = gb.finalTileSize;
        int frameWidth = gb.finalTileSize * 8;
        int frameHeight = gb.finalTileSize * 10;

        drawSubWindow(frameX, frameY, frameWidth, frameHeight);

        switch (subState) {
            case 0:
                pause(frameX, frameY);
                break;
            case 1:
                fullScreenNotif(frameX, frameY);
                break;
            case 2:
                drawMenuKBScreen(frameX, frameY);
                break;
            case 3:
                goBackScreen(frameX, frameY);
                break;
        }

        gb.kh.enterPress = false;
    }

    private void drawCreditsScreen() {
        // int temp2 = (int) (0.2*gb.finalTileSize);
        int temp = (int) (0.5 * gb.finalTileSize);

        // Ustawienie kolorow ekranu poczatkowego
        Color bckgndColor = new Color(21, 42, 66);
        // Color bckgndColor = new Color(0,0,0);
        Color txtColor = new Color(247, 214, 169);

        // Rysowanie tla credits
        // g2.setColor(bckgndColor);
        // g2.fillRect(0,0,gb.screenWidth,gb.screenHeight);
        g2.setColor(Color.black);
        g2.fillRect(0, 0, gb.screenWidth, gb.screenHeight);
        Color c = new Color(83, 78, 102, 127);
        g2.setColor(c);
        g2.fillRect(gb.finalTileSize, gb.finalTileSize, gb.screenWidth - 2 * gb.finalTileSize,
                gb.screenHeight - 2 * gb.finalTileSize);
        g2.setColor(transpColor);
        g2.fillRect(gb.finalTileSize, gb.finalTileSize, gb.screenWidth - 2 * gb.finalTileSize,
                gb.screenHeight - 2 * gb.finalTileSize);
        g2.setColor(bckgndColor);
        g2.setStroke(new BasicStroke(5));
        // g2.drawRoundRect(x, y, width, height, 25, 25);
        g2.drawRoundRect(temp, temp, gb.screenWidth - gb.finalTileSize, gb.screenHeight - gb.finalTileSize, temp, temp);

        // tekst
        // Autorze
        g2.setFont(OCR_A_Extended_40);
        String text = "Authors:";
        int x = centerText(text);
        int y = 3 * gb.finalTileSize;
        // Cienie
        g2.setColor(new Color(0, 0, 0, 127));
        g2.drawString(text, x + 10, y + 20);
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 10);

        g2.setColor(txtColor);
        g2.drawString(text, x, y);

        g2.setFont(OCR_A_Extended_30);
        // MICHAL
        text = "Michał Rostek";
        x = centerText(text);
        y += 2 * gb.finalTileSize;
        // cienie
        g2.setColor(new Color(0, 0, 0, 127));
        g2.drawString(text, x + 10, y + 20);
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 10);

        g2.setColor(txtColor);
        g2.drawString(text, x, y);

        // ADAM
        text = "Adam Szajgin";
        x = centerText(text);
        y += gb.finalTileSize;
        // cienie
        g2.setColor(new Color(0, 0, 0, 127));
        g2.drawString(text, x + 10, y + 20);
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 10);

        g2.setColor(txtColor);
        g2.drawString(text, x, y);

        // powrot do ekranu poczatkowego

        g2.setFont(OCR_A_Extended_20);
        text = "Press ESC to go back";
        x = centerText(text);
        y += 4 * gb.finalTileSize;
        // cienie
        g2.setColor(new Color(0, 0, 0, 127));
        g2.drawString(text, x + 10, y + 20);
        g2.setColor(Color.black);
        g2.drawString(text, x + 5, y + 10);

        g2.setColor(txtColor);
        g2.drawString(text, x, y);

    }

    public void pause(int frameX, int frameY) {

        int temp = (int) (0.5 * gb.finalTileSize);
        int temp2 = (int) (0.4 * gb.finalTileSize);
        int temp3 = (int) (2 * gb.finalTileSize / 5);

        // Draw top strings
        Color c = new Color(247, 214, 169);
        g2.setColor(c);

        g2.setFont(OCR_A_Extended_40);
        String text = "PAUSED";
        int textY = gb.screenHeight / 2 - gb.finalTileSize * 3;
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
        int textX3 = frameX + gb.finalTileSize + temp;
        // Full screen
        textY = gb.screenHeight / 2 - gb.finalTileSize;
        g2.drawString("Full Screen", textX3, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                if (gb.fullScreenState == false) {
                    gb.fullScreenState = true;
                } else if (gb.fullScreenState == true) {
                    gb.fullScreenState = false;
                }
                subState = 1;
            }
        }
        // Sound
        textY = gb.screenHeight / 2;
        g2.drawString("Music Volume", textX3, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX3 - temp, textY);
        }
        // Sound fx
        textY = gb.screenHeight / 2 + gb.finalTileSize;
        g2.drawString("FX Volume", textX3, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX3 - temp, textY);
        }
        // Key bindings
        textY = gb.screenHeight / 2 + 2 * gb.finalTileSize;
        g2.drawString("Key Bindings", textX3, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 2;
                commandNum = 0;
            }
        }
        // // Main menu
        // textY = gb.screenHeight/2 + 3*gb.finalTileSize;
        // g2.drawString("Back to main menu", textX3, textY);
        // if (commandNum == 4){
        // g2.drawString(">", textX3 - temp, textY);
        // }
        // End game
        textY = gb.screenHeight / 2 + 4 * gb.finalTileSize;
        g2.drawString("Quit game", textX3, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 3;
                commandNum = 0;
            }
        }

        // Next to them:
        // Check Box
        textX = textX3 + gb.finalTileSize * 3 + temp;
        textY = gb.screenHeight / 2 - gb.finalTileSize - temp2;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, gb.finalTileSize / 2, gb.finalTileSize / 2);
        if (gb.fullScreenState == true) {
            g2.fillRect(textX, textY, gb.finalTileSize / 2, gb.finalTileSize / 2);
        }
        // Music bar
        textY = gb.screenHeight / 2 - temp2;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 2 * gb.finalTileSize, gb.finalTileSize / 2);
        int volWidth = temp3 * gb.sound.volumeBar;
        g2.fillRect(textX, textY, volWidth, gb.finalTileSize / 2);
        // FX bar
        textY = gb.screenHeight / 2 + gb.finalTileSize - temp2;
        g2.setStroke(new BasicStroke(3));
        g2.drawRect(textX, textY, 2 * gb.finalTileSize, gb.finalTileSize / 2);
        // int fxWidth = temp3 * gb.fx.volumeBar;
        // g2.fillRect(textX,textY, fxWidth, gb.finalTileSize);
    }

    public void fullScreenNotif(int frameX, int frameY) {

        int temp = (int) (0.5 * gb.finalTileSize);
        int textX = frameX + gb.finalTileSize;
        int textY = frameY + gb.finalTileSize * 3;

        g2.setFont(OCR_A_Extended_20);

        String text = "Changing to full screen \nwill restart the game";
        for (String line : text.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += gb.finalTileSize;
        }

        // powrot
        textY = frameY + gb.finalTileSize * 9;
        g2.drawString("Back", textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
            }
        }
    }

    public void drawMenuKBScreen(int frameX, int frameY) {

        int temp = (int) (0.5 * gb.finalTileSize);
        int textX = frameX + gb.finalTileSize;
        int textY = frameY + gb.finalTileSize * 3;

        // Draw top strings
        Color c = new Color(247, 214, 169);
        g2.setColor(c);

        g2.setFont(OCR_A_Extended_40);
        String text = "Key Bindings";
        textY = gb.screenHeight / 2 - gb.finalTileSize * 3;
        textX = centerText(text);
        g2.drawString(text, textX, textY);

        g2.setFont(OCR_A_Extended_20);
        int textX3 = frameX + gb.finalTileSize + temp;
        // gora
        textY = gb.screenHeight / 2 - gb.finalTileSize;
        g2.drawString("Move Forward", textX3, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
            }
        }
        // dol
        textY = gb.screenHeight / 2;
        g2.drawString("Move backward", textX3, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
            }
        }
        // prawo
        textY = gb.screenHeight / 2 + gb.finalTileSize;
        g2.drawString("Move right", textX3, textY);
        if (commandNum == 2) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
            }
        }
        // lewo
        textY = gb.screenHeight / 2 + 2 * gb.finalTileSize;
        g2.drawString("Move left", textX3, textY);
        if (commandNum == 3) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
            }
        }
        // powrot
        textX = frameX + gb.finalTileSize;
        textY = frameY + gb.finalTileSize * 9;
        g2.drawString("Go back", textX3, textY);
        if (commandNum == 4) {
            g2.drawString(">", textX3 - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
            }
        }
    }

    public void goBackScreen(int frameX, int frameY) {

        int temp = (int) (0.5 * gb.finalTileSize);
        int textX = frameX + gb.finalTileSize;
        int textY = frameY + gb.finalTileSize * 3;

        g2.setFont(OCR_A_Extended_20);

        String text = "Are you sure you \nwant to quit the game?";
        for (String line : text.split("\n")) {
            g2.drawString(line, textX, textY);
            textY += gb.finalTileSize;
        }

        // g2.drawString(text, textX, textY);

        g2.setFont(OCR_A_Extended_20);
        text = "Yes";
        textX = centerText(text);
        textY += gb.finalTileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 0) {
            g2.drawString(">", textX - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
                gb.stopMusic();
                System.exit(0);
            }
        }

        text = "No";
        textX = centerText(text);
        textY += gb.finalTileSize;
        g2.drawString(text, textX, textY);
        if (commandNum == 1) {
            g2.drawString(">", textX - temp, textY);
            if (gb.kh.enterPress == true) {
                subState = 0;
                gb.gameState = gb.playState;
            }
        }
        if (commandNum > 1) {
            commandNum = 0;
        }
        if (commandNum < 0) {
            commandNum = 1;
        }

    }

    public void drawSubWindow(int x, int y, int width, int height) {
        Color c = new Color(83, 78, 102, 127);
        g2.setColor(c);
        g2.fillRoundRect(x, y, width, height, 35, 35);

        // c = new Color(251, 237, 217);
        c = new Color(247, 214, 169);
        g2.setColor(c);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 25, 25);
    }

    // method for getting the centerf for specific string
    private int centerText(String text) {
        int textLength = (int) (g2.getFontMetrics().getStringBounds(text, g2).getWidth());
        int x = gb.screenWidth / 2 - textLength / 2;
        return x;
    }
}
