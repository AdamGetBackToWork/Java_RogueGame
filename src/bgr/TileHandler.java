package Projekt.src.bgr;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


import javax.imageio.ImageIO;

import Projekt.src.main.GameBoard;

public class TileHandler{
    
    GameBoard gb;
    Tile[] tile;
    int tileNum[][];

    public TileHandler(GameBoard gb){

        this.gb = gb;
        tile = new Tile[10]; // Number of diff tiles in a game
        tileNum = new int[gb.screenColumns][gb.screenRows];

        getTileImage();
        loadMap("/Projekt/res/maps/testmap.txt");
    }

    public void getTileImage(){
        
        String testPath1 = "Projekt\\res\\background\\ground.png";
        String testPath2 = "Projekt\\res\\background\\stone.png";
        String testPath3 = "Projekt\\res\\background\\bricks.png";

        try {
            tile[0] = new Tile();
            tile[1] = new Tile();
            tile[2] = new Tile();

            tile[0].image = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            tile[1].image = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            tile[2].image = ImageIO.read(getClass().getClassLoader().getResource(testPath3));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public void loadMap(){

    //     String testMapPath = "Projekt\\res\\maps\\testmap.txt";

    //     try {
            
    //         // InputStream is = getClass().getClassLoader().getResource(testMapPath);
    //         Scanner is = new Scanner(getClass().getResourceAsStream(testMapPath));
            
    //         //InputStream is = getClass().getResourceAsStream(testMapPath);
            
    //         BufferedReader br = new BufferedReader(new InputStreamReader(is));

    //         int col = 0;
    //         int row = 0;

    //         while (col < gb.screenColumns && row < gb.screenRows){
    //             String  line = br.readLine();
                
    //             while(col < gb.screenColumns){
    //                 String numbers[] = line.split(" ");
    //                 int num = Integer.parseInt(numbers[col]);

    //                 tileNum[col][row] = num;
    //                 col++;
    //             }
    //             if(col == gb.screenColumns){
    //                 col = 0;
    //                 row++;
    //             }
    //         }
    //         br.close();

    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }
    // }

    public void loadMap(String mapfile) {
        String testMapPath = mapfile;

        try (InputStream is = getClass().getResourceAsStream(testMapPath);
            Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)))) {

            int col = 0;
            int row = 0;

            while (scanner.hasNextLine() && row < gb.screenRows) {
                String line = scanner.nextLine();
                String[] numbers = line.split("\\s+");

                for (String number : numbers) {
                    if (col < gb.screenColumns) {
                        int num = Integer.parseInt(number);
                        tileNum[col][row] = num;
                        col++;
                    } else {
                        break; // Stop processing the line if we've reached the column limit
                    }
                }

                col = 0; // Reset column count for the next row
                row++;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2){
       
        g2.drawImage(tile[0].image, 0,0,gb.finalTileSize,gb.finalTileSize,null);
        
        int col = 0; 
        int row = 0;
        int x = 0;
        int y = 0;

        while (col < gb.screenColumns && row < gb.screenRows){
            
            int drawTileNum = tileNum[col][row];

            g2.drawImage(tile[drawTileNum].image, x,y,gb.finalTileSize,gb.finalTileSize,null);
            col++;
            x += gb.finalTileSize;
            
            if (col == gb.screenColumns){
                col = 0;
                x = 0;
                row++;
                y += gb.finalTileSize;
            }
        }
    
    }
}
