package background;
// package Projekt.src.bgr;

import main.GameBoard;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class TileHandler {
    
    GameBoard gb;
    public Tile[] tile;
    public int tileNum[][];

    public TileHandler(GameBoard gb){

        this.gb = gb;
        tile = new Tile[10]; // Number of diff tiles in a game
        tileNum = new int[gb.maxWorldCol][gb.maxWorldRow];

        getTileImage();
        loadMap("res\\maps\\testmap2.txt");
    }

    public void getTileImage(){
        
        // String testPath1 = "res\\background\\ground.png";
        // String testPath2 = "res\\background\\stone.png";
        // String testPath3 = "res\\background\\bricks.png";
        // String testPath4 = "res\\background\\black.png";

        File file0 = new File("res\\background\\ground.png");
        File file1 = new File("res\\background\\stone.png");
        File file2 = new File("res\\background\\bricks.png");
        File file3 = new File("res\\background\\black.png");


        System.out.println(new File(".").getAbsolutePath());

        try {
            tile[0] = new Tile();
            tile[1] = new Tile();
            tile[2] = new Tile();
            tile[3] = new Tile();

            
            tile[0].image = ImageIO.read(file0);  
            tile[1].image = ImageIO.read(file1); 
            tile[2].image = ImageIO.read(file2); 
            
            tile[3].image = ImageIO.read(file3); 
            tile[3].collision = true;

            // tile[0].image = ImageIO.read(getClass().getResourceAsStream(testPath1));
            // tile[0].image = ImageIO.read(getClass().getClassLoader().getResource(testPath1));
            // tile[1].image = ImageIO.read(getClass().getClassLoader().getResource(testPath2));
            // tile[2].image = ImageIO.read(getClass().getClassLoader().getResource(testPath3));
            // tile[3].image = ImageIO.read(getClass().getClassLoader().getResource(testPath4));
            

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

        // try {

        //     InputStream is = getClass().getResourceAsStream(testMapPath);
        //     BufferedReader br = new BufferedReader(new InputStreamReader(is));

        //     int col = 0;
        //     int row = 0;

        //     while(col < gb.maxWorldCol && row < gb.maxWorldRow){
        //         String line = br.readLine();

        //         while(col < gb.maxWorldCol){
        //             String numbers[] = line.split(" ");
        //             int num = Integer.parseInt(numbers[col]);
        //             tileNum[col][num] = num;
        //             col++;
        //         }
        //         if(col == gb.maxWorldCol){
        //             col = 0;
        //             row++;
        //         }
        //     }
        //     br.close();
        // }catch(Exception e){
        //     e.printStackTrace();
        // }

        // try (InputStream is = getClass().getResourceAsStream(testMapPath);
        //     Scanner scanner = new Scanner(new BufferedReader(new InputStreamReader(is)))) {

        //     int col = 0;
        //     int row = 0;

        //     while (scanner.hasNextLine() && row < gb.maxWorldRow) {
        //         String line = scanner.nextLine();
        //         String[] numbers = line.split("\\s+");

        //         for (String number : numbers) {
        //             if (col < gb.maxWorldCol) {
        //                 int num = Integer.parseInt(number);
        //                 tileNum[col][row] = num;
        //                 col++;
        //             } else {
        //                 break; // Stop processing the line if we've reached the column limit
        //             }
        //         }

        //         col = 0; // Reset column count for the next row
        //         row++;
        //     }
        // } catch (Exception e) {
        //     e.printStackTrace();
        // }

        try (Scanner scanner = new Scanner(new File(testMapPath))) {
        int col = 0;
        int row = 0;

        while (scanner.hasNextLine() && row < gb.maxWorldRow) {
            String line = scanner.nextLine();
            String[] numbers = line.split("\\s+");

            for (String number : numbers) {
                if (col < gb.maxWorldCol) {
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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
    }


    }


    public void draw(Graphics2D g2){
       
        // g2.drawImage(tile[0].image, 0,0,gb.finalTileSize,gb.finalTileSize,null);
        
        int worldCol = 0; 
        int worldRow = 0;


        while (worldCol < gb.maxWorldCol && worldRow < gb.maxWorldRow){
            
            int drawTileNum = tileNum[worldCol][worldRow];

            int worldX = worldCol * gb.finalTileSize;
            int worldY = worldRow * gb.finalTileSize;
            int screenX = worldX - gb.player.worldX + gb.player.screenX;
            int screenY = worldY - gb.player.worldY + gb.player.screenY - 1*gb.finalTileSize;
            
            if(worldX > gb.player.worldX - gb.player.screenX - gb.finalTileSize && 
                worldX < gb.player.worldX + gb.player.screenX + gb.finalTileSize && 
                worldY> gb.player.worldY - gb.player.screenY - gb.finalTileSize && 
                worldY < gb.player.worldY + gb.player.screenY + gb.finalTileSize){
                    g2.drawImage(tile[drawTileNum].image,screenX,screenY,gb.finalTileSize,gb.finalTileSize,null);
                }

            worldCol++;
            
            if (worldCol == gb.maxWorldCol){
                worldCol = 0;
                worldRow++;
            }
        }
    
    }
}
