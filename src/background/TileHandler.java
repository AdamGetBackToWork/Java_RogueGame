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
        tile = new Tile[100]; // Number of diff tiles in a game
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

        // TILES
            // BUILDINGS
                // DARKHOUSE
                File fileDarkBuild01 = new File("res\\tiles\\buildings\\darkhouse\\floor_left.png");
                File fileDarkBuild02 = new File("res\\tiles\\buildings\\darkhouse\\floor_middle_lights.png");
                File fileDarkBuild03 = new File("res\\tiles\\buildings\\darkhouse\\floor_middle.png");
                File fileDarkBuild04 = new File("res\\tiles\\buildings\\darkhouse\\floor_right.png");
                File fileDarkBuild05 = new File("res\\tiles\\buildings\\darkhouse\\parter_door.png");
                File fileDarkBuild06 = new File("res\\tiles\\buildings\\darkhouse\\parter_left.png");
                File fileDarkBuild07 = new File("res\\tiles\\buildings\\darkhouse\\parter_right.png");
                File fileDarkBuild08 = new File("res\\tiles\\buildings\\darkhouse\\parter.png");
                File fileDarkBuild09 = new File("res\\tiles\\buildings\\darkhouse\\roof_ac.png");
                File fileDarkBuild10 = new File("res\\tiles\\buildings\\darkhouse\\roof_bottom.png");
                File fileDarkBuild11 = new File("res\\tiles\\buildings\\darkhouse\\roof_bottomleft.png");
                File fileDarkBuild12 = new File("res\\tiles\\buildings\\darkhouse\\roof_bottomright.png");
                File fileDarkBuild13 = new File("res\\tiles\\buildings\\darkhouse\\roof_left.png");
                File fileDarkBuild14 = new File("res\\tiles\\buildings\\darkhouse\\roof_right.png");
                File fileDarkBuild15 = new File("res\\tiles\\buildings\\darkhouse\\roof_topleft.png");
                File fileDarkBuild16 = new File("res\\tiles\\buildings\\darkhouse\\roof_topmiddle.png");
                File fileDarkBuild17 = new File("res\\tiles\\buildings\\darkhouse\\roof_topright.png");
                File fileDarkBuild18 = new File("res\\tiles\\buildings\\darkhouse\\roof.png");
                // LIGHTHOUSE
                File fileLightBuild01 = new File("res\\tiles\\buildings\\lighthouse\\floor_door2.png");
                File fileLightBuild02 = new File("res\\tiles\\buildings\\lighthouse\\floor_left.png");
                File fileLightBuild03 = new File("res\\tiles\\buildings\\lighthouse\\floor_middle_lights.png");
                File fileLightBuild04 = new File("res\\tiles\\buildings\\lighthouse\\floor_middle.png");
                File fileLightBuild05 = new File("res\\tiles\\buildings\\lighthouse\\floor_right.png");
                File fileLightBuild06 = new File("res\\tiles\\buildings\\lighthouse\\parter_door.png");
                File fileLightBuild07 = new File("res\\tiles\\buildings\\lighthouse\\parter_left.png");
                File fileLightBuild08 = new File("res\\tiles\\buildings\\lighthouse\\parter_right.png");
                File fileLightBuild09 = new File("res\\tiles\\buildings\\lighthouse\\parter.png");
                File fileLightBuild10 = new File("res\\tiles\\buildings\\lighthouse\\roof_bottom.png");
                File fileLightBuild11 = new File("res\\tiles\\buildings\\lighthouse\\roof_bottomleft.png");
                File fileLightBuild12 = new File("res\\tiles\\buildings\\lighthouse\\roof_bottomright.png");
                File fileLightBuild13 = new File("res\\tiles\\buildings\\lighthouse\\roof_left.png");
                File fileLightBuild14 = new File("res\\tiles\\buildings\\lighthouse\\roof_top.png");
                File fileLightBuild15 = new File("res\\tiles\\buildings\\lighthouse\\roof_topleft.png");
                File fileLightBuild16 = new File("res\\tiles\\buildings\\lighthouse\\roof_topright.png");
                File fileLightBuild17 = new File("res\\tiles\\buildings\\lighthouse\\roof.png");
                // 35
            // PAVEMENTS
            File filePavement01 = new File("res\\tiles\\pavements\\pavement-tile1-bottomfence.png");
            File filePavement02 = new File("res\\tiles\\pavements\\pavement-tile1-bottomleftfence.png");
            File filePavement03 = new File("res\\tiles\\pavements\\pavement-tile1-bottomrightfence.png");
            File filePavement04 = new File("res\\tiles\\pavements\\pavement-tile1-inwardbottomleftfence.png");
            File filePavement05 = new File("res\\tiles\\pavements\\pavement-tile1-inwardbottomrightfence.png");
            File filePavement06 = new File("res\\tiles\\pavements\\pavement-tile1-inwardtopleftfence.png");
            File filePavement07 = new File("res\\tiles\\pavements\\pavement-tile1-inwardtoprightfence.png");
            File filePavement08 = new File("res\\tiles\\pavements\\pavement-tile1-leftfence.png");
            File filePavement09 = new File("res\\tiles\\pavements\\pavement-tile1-rightfence.png");
            File filePavement10 = new File("res\\tiles\\pavements\\pavement-tile1-topfence.png");
            File filePavement11 = new File("res\\tiles\\pavements\\pavement-tile1-topleftfence.png");
            File filePavement12 = new File("res\\tiles\\pavements\\pavement-tile1-toprightfence.png");
            File filePavement13 = new File("res\\tiles\\pavements\\pavement-tile1.png");
            File filePavement14 = new File("res\\tiles\\pavements\\pavement-tile2-bottomfence.png");
            File filePavement15 = new File("res\\tiles\\pavements\\pavement-tile2-topfence.png");
            File filePavement16 = new File("res\\tiles\\pavements\\pavement-tile2.png");
            File filePavement17 = new File("res\\tiles\\pavements\\pavement-tile3.png");
            // ROADS
            File fileRoad01 = new File("res\\tiles\\roads\\road-bottomend.png");
            File fileRoad02 = new File("res\\tiles\\roads\\road-bottomleftend.png");
            File fileRoad03 = new File("res\\tiles\\roads\\road-bottomrightend.png");
            File fileRoad04 = new File("res\\tiles\\roads\\road-dashedline-single-horizontal.png");
            File fileRoad05 = new File("res\\tiles\\roads\\road-dashedline-single-vertical.png");
            File fileRoad06 = new File("res\\tiles\\roads\\road-full.png");
            File fileRoad07 = new File("res\\tiles\\roads\\road-inward-botleft.png");
            File fileRoad08 = new File("res\\tiles\\roads\\road-inward-botright.png");
            File fileRoad09 = new File("res\\tiles\\roads\\road-inward-topleft.png");
            File fileRoad10 = new File("res\\tiles\\roads\\road-inward-topright.png");
            File fileRoad11 = new File("res\\tiles\\roads\\road-leftend.png");
            File fileRoad12 = new File("res\\tiles\\roads\\road-manhole.png");
            File fileRoad13 = new File("res\\tiles\\roads\\road-rightend.png");
            File fileRoad14 = new File("res\\tiles\\roads\\road-topend.png");
            File fileRoad15 = new File("res\\tiles\\roads\\road-topleftend.png");
            File fileRoad16 = new File("res\\tiles\\roads\\road-toprightend.png");
            // 33
        try {
            tile[0] = new Tile();
            tile[1] = new Tile();
            tile[2] = new Tile();
            tile[3] = new Tile();

            /* 
                tile[0] = new Tile();
                tile[1] = new Tile();
                tile[2] = new Tile();
                tile[3] = new Tile();
                tile[4] = new Tile();
                tile[5] = new Tile();
                tile[6] = new Tile();
                tile[7] = new Tile();
                tile[8] = new Tile();
                tile[9] = new Tile();
                tile[10] = new Tile();
                tile[11] = new Tile();
                tile[12] = new Tile();
                tile[13] = new Tile();
                tile[14] = new Tile();
                tile[15] = new Tile();
                tile[16] = new Tile();
                tile[17] = new Tile();
                tile[18] = new Tile();
                tile[19] = new Tile();
                tile[20] = new Tile();
                tile[21] = new Tile();
                tile[22] = new Tile();
                tile[23] = new Tile();
                tile[24] = new Tile();
                tile[25] = new Tile();
                tile[26] = new Tile();
                tile[27] = new Tile();
                tile[28] = new Tile();
                tile[29] = new Tile();
                tile[30] = new Tile();
                tile[31] = new Tile();
                tile[32] = new Tile();
                tile[33] = new Tile();
                tile[34] = new Tile();
                tile[35] = new Tile();
                tile[36] = new Tile();
                tile[37] = new Tile();
                tile[38] = new Tile();
                tile[39] = new Tile();
                tile[40] = new Tile();
                tile[41] = new Tile();
                tile[42] = new Tile();
                tile[43] = new Tile();
                tile[44] = new Tile();
                tile[45] = new Tile();
                tile[46] = new Tile();
                tile[47] = new Tile();
                tile[48] = new Tile();
                tile[49] = new Tile();
                tile[50] = new Tile();
                tile[51] = new Tile();
                tile[52] = new Tile();
                tile[53] = new Tile();
                tile[54] = new Tile();
                tile[55] = new Tile();
                tile[56] = new Tile();
                tile[57] = new Tile();
                tile[58] = new Tile();
                tile[59] = new Tile();
                tile[60] = new Tile();
                tile[61] = new Tile();
                tile[62] = new Tile();
                tile[63] = new Tile();
                tile[64] = new Tile();
                tile[65] = new Tile();
                tile[66] = new Tile();
                tile[67] = new Tile();
                tile[68] = new Tile();
            */

            for (int i = 0; i <= 68; i++) {
                tile[i] = new Tile();
            }
            
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
