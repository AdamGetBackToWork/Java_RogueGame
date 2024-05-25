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
        tile = new Tile[1000]; // Number of diff tiles in a game
        tileNum = new int[gb.maxWorldCol][gb.maxWorldRow];

        getTileImage();
<<<<<<< HEAD
        loadMap("res\\maps\\mapa-workinprogress-18_05.txt");
=======
        loadMap("res\\maps\\mapa.txt");
>>>>>>> titleScreen
    }

    public void getTileImage(){
        
        // String testPath1 = "res\\background\\ground.png";
        // String testPath2 = "res\\background\\stone.png";
        // String testPath3 = "res\\background\\bricks.png";
        // String testPath4 = "res\\background\\black.png";

        // File file0 = new File("res\\background\\ground.png");
        // File file1 = new File("res\\background\\stone.png");
        // File file2 = new File("res\\background\\bricks.png");
        // File file3 = new File("res\\background\\black.png");

        // TILES
            // BUILDINGS
                // DARKHOUSE
                File fileDarkBuildFloorLeft = new File("res\\tiles\\buildings\\darkhouse\\floor_left.png");
                File fileDarkBuildFloorMiddleLights = new File("res\\tiles\\buildings\\darkhouse\\floor_middle_lights.png");
                File fileDarkBuildFloorMiddle = new File("res\\tiles\\buildings\\darkhouse\\floor_middle.png");
                File fileDarkBuildFloorRight = new File("res\\tiles\\buildings\\darkhouse\\floor_right.png");
                File fileDarkBuildParterDoor = new File("res\\tiles\\buildings\\darkhouse\\parter_door.png");
                File fileDarkBuildParterLeft = new File("res\\tiles\\buildings\\darkhouse\\parter_left.png");
                File fileDarkBuildParterRight = new File("res\\tiles\\buildings\\darkhouse\\parter_right.png");
                File fileDarkBuildParter = new File("res\\tiles\\buildings\\darkhouse\\parter.png");
                File fileDarkBuildRoofAC = new File("res\\tiles\\buildings\\darkhouse\\roof_ac.png");
                File fileDarkBuildRoofBottom = new File("res\\tiles\\buildings\\darkhouse\\roof_bottom.png");
                File fileDarkBuildRoofBottomLeft = new File("res\\tiles\\buildings\\darkhouse\\roof_bottomleft.png");
                File fileDarkBuildRoofBottomRight = new File("res\\tiles\\buildings\\darkhouse\\roof_bottomright.png");
                File fileDarkBuildRoofLeft = new File("res\\tiles\\buildings\\darkhouse\\roof_left.png");
                File fileDarkBuildRoofRight = new File("res\\tiles\\buildings\\darkhouse\\roof_right.png");
                File fileDarkBuildRoofTopLeft = new File("res\\tiles\\buildings\\darkhouse\\roof_topleft.png");
                File fileDarkBuildRoofTopMiddle = new File("res\\tiles\\buildings\\darkhouse\\roof_topmiddle.png");
                File fileDarkBuildRoofTopRight = new File("res\\tiles\\buildings\\darkhouse\\roof_topright.png");
                File fileDarkBuildRoof = new File("res\\tiles\\buildings\\darkhouse\\roof.png");
                // LIGHTHOUSE
                File fileLightBuildFloorDoor2 = new File("res\\tiles\\buildings\\lighthouse\\floor_door2.png");
                File fileLightBuildFloorLeft = new File("res\\tiles\\buildings\\lighthouse\\floor_left.png");
                File fileLightBuildFloorMiddleLights = new File("res\\tiles\\buildings\\lighthouse\\floor_middle_lights.png");
                File fileLightBuildFLoorMiddle = new File("res\\tiles\\buildings\\lighthouse\\floor_middle.png");
                File fileLightBuildFloorRight = new File("res\\tiles\\buildings\\lighthouse\\floor_right.png");
                File fileLightBuildParterDoor = new File("res\\tiles\\buildings\\lighthouse\\parter_door.png");
                File fileLightBuildParterLeft = new File("res\\tiles\\buildings\\lighthouse\\parter_left.png");
                File fileLightBuildParterRight = new File("res\\tiles\\buildings\\lighthouse\\parter_right.png");
                File fileLightBuildParter = new File("res\\tiles\\buildings\\lighthouse\\parter.png");
                File fileLightBuildRoofBottom = new File("res\\tiles\\buildings\\lighthouse\\roof_bottom.png");
                File fileLightBuildRoofBottomLeft = new File("res\\tiles\\buildings\\lighthouse\\roof_bottomleft.png");
                File fileLightBuildRoofBottomRight = new File("res\\tiles\\buildings\\lighthouse\\roof_bottomright.png");
                File fileLightBuildRoofLeft = new File("res\\tiles\\buildings\\lighthouse\\roof_left.png");
                File fileLightBuildRoofTop = new File("res\\tiles\\buildings\\lighthouse\\roof_top.png");
                File fileLightBuildRoofTopLeft = new File("res\\tiles\\buildings\\lighthouse\\roof_topleft.png");
                File fileLightBuildRoofTopRight = new File("res\\tiles\\buildings\\lighthouse\\roof_topright.png");
                File fileLightBuildRoof = new File("res\\tiles\\buildings\\lighthouse\\roof.png");
                File fileLightBuildRoofRight = new File("res\\tiles\\buildings\\lighthouse\\roof_right.png");
                // 35
            // PAVEMENTS
            File filePavement1Bottom = new File("res\\tiles\\pavements\\pavement-tile1-bottomfence.png");
            File filePavement1BottomLeft = new File("res\\tiles\\pavements\\pavement-tile1-bottomleftfence.png");
            File filePavement1BottomRight = new File("res\\tiles\\pavements\\pavement-tile1-bottomrightfence.png");
            File filePavement1InwBottomLeft = new File("res\\tiles\\pavements\\pavement-tile1-inwardbottomleftfence.png");
            File filePavement1InwBottomRight = new File("res\\tiles\\pavements\\pavement-tile1-inwardbottomrightfence.png");
            File filePavement1InwTopLeft = new File("res\\tiles\\pavements\\pavement-tile1-inwardtopleftfence.png");
            File filePavement1InwTopRight = new File("res\\tiles\\pavements\\pavement-tile1-inwardtoprightfence.png");
            File filePavement1Left = new File("res\\tiles\\pavements\\pavement-tile1-leftfence.png");
            File filePavement1Right = new File("res\\tiles\\pavements\\pavement-tile1-rightfence.png");
            File filePavement1Top = new File("res\\tiles\\pavements\\pavement-tile1-topfence.png");
            File filePavement1TopLeft = new File("res\\tiles\\pavements\\pavement-tile1-topleftfence.png");
            File filePavement1TopRight = new File("res\\tiles\\pavements\\pavement-tile1-toprightfence.png");
            File filePavement1 = new File("res\\tiles\\pavements\\pavement-tile1.png");
            File filePavement2Bottom = new File("res\\tiles\\pavements\\pavement-tile2-bottomfence.png");
            File filePavement2Top = new File("res\\tiles\\pavements\\pavement-tile2-topfence.png");
            File filePavement2 = new File("res\\tiles\\pavements\\pavement-tile2.png");
            File filePavement3 = new File("res\\tiles\\pavements\\pavement-tile3.png");
            // ROADS
            File fileRoadBottom = new File("res\\tiles\\roads\\road-bottomend.png");
            File fileRoadBottomLeft = new File("res\\tiles\\roads\\road-bottomleftend.png");
            File fileRoadBottomRight = new File("res\\tiles\\roads\\road-bottomrightend.png");
            File fileRoadLineHor = new File("res\\tiles\\roads\\road-dashedline-single-horizontal.png");
            File fileRoadLineVer = new File("res\\tiles\\roads\\road-dashedline-single-vertical.png");
            File fileRoad = new File("res\\tiles\\roads\\road-full.png");
            File fileRoadInwBotLeft = new File("res\\tiles\\roads\\road-inward-botleft.png");
            File fileRoadInwBotRight = new File("res\\tiles\\roads\\road-inward-botright.png");
            File fileRoadInwTopLeft = new File("res\\tiles\\roads\\road-inward-topleft.png");
            File fileRoadInwTopRight = new File("res\\tiles\\roads\\road-inward-topright.png");
            File fileRoadLeft = new File("res\\tiles\\roads\\road-leftend.png");
            File fileRoadManhole = new File("res\\tiles\\roads\\road-manhole.png");
            File fileRoadRight = new File("res\\tiles\\roads\\road-rightend.png");
            File fileRoadTop = new File("res\\tiles\\roads\\road-topend.png");
            File fileRoadTopLeft = new File("res\\tiles\\roads\\road-topleftend.png");
            File fileRoadTopRight = new File("res\\tiles\\roads\\road-toprightend.png");
            // 33
        try {
            /* TUTAJ WYKOMENTOWAŁEM BO NIZEJ W PETLI TO SAMO ROBIMY
            tile[0] = new Tile();
            tile[1] = new Tile();
            tile[2] = new Tile();
            tile[3] = new Tile();
            */
<<<<<<< HEAD

=======
>>>>>>> titleScreen
            // zmienilem na 99 zeby byc bezpiecznym na ten moment z zakresem
            for (int i = 0; i <= 99; i++) {
                tile[i] = new Tile();
            }
            
            /* 
            tile[0].image = ImageIO.read(file0);  
            tile[1].image = ImageIO.read(file1); 
            tile[2].image = ImageIO.read(file2); 
            
            tile[3].image = ImageIO.read(file3); 
            tile[3].collision = true;
*/
            //loading eachfile 

            //na ten moment bez: (todo)
            // drzwi bundykow i zapalonych swiatel
            // ladnych chodnikow (zaokrąglenia)
            // zaokraglen ulicy

            //light house roof - 1
            tile[10].image = ImageIO.read(fileLightBuildRoof);
            tile[11].image = ImageIO.read(fileLightBuildRoofLeft);
            tile[12].image = ImageIO.read(fileLightBuildRoofRight);
            tile[13].image = ImageIO.read(fileLightBuildRoofTop);
            tile[14].image = ImageIO.read(fileLightBuildRoofBottom);
            tile[15].image = ImageIO.read(fileLightBuildRoofTopLeft);
            tile[16].image = ImageIO.read(fileLightBuildRoofTopRight);
            tile[17].image = ImageIO.read(fileLightBuildRoofBottomLeft);
            tile[18].image = ImageIO.read(fileLightBuildRoofBottomRight);
            
            
            //light house blocks - 6
            tile[60].image = ImageIO.read(fileLightBuildFLoorMiddle);
            tile[61].image = ImageIO.read(fileLightBuildFloorLeft);
            tile[62].image = ImageIO.read(fileLightBuildFloorRight);
            tile[63].image = ImageIO.read(fileLightBuildFLoorMiddle);
            tile[64].image = ImageIO.read(fileLightBuildParter);
            tile[65].image = ImageIO.read(fileLightBuildFloorLeft);
            tile[66].image = ImageIO.read(fileLightBuildFloorRight);
            tile[67].image = ImageIO.read(fileLightBuildParterLeft);
            tile[68].image = ImageIO.read(fileLightBuildParterRight);

            //pavement - to do -> change map
            tile[20].image = ImageIO.read(filePavement1);
            tile[21].image = ImageIO.read(filePavement1Left);
            tile[22].image = ImageIO.read(filePavement1Right);
            tile[23].image = ImageIO.read(filePavement1Top);
            tile[24].image = ImageIO.read(filePavement1Bottom);
            tile[25].image = ImageIO.read(filePavement1TopLeft);
            tile[26].image = ImageIO.read(filePavement1TopRight);
            tile[27].image = ImageIO.read(filePavement1BottomLeft);
            tile[28].image = ImageIO.read(filePavement1BottomRight);
            //to do : inversed | pavements 2 and 3

            // road
            // to do: manhole + inverse
            tile[30].image = ImageIO.read(fileRoad);
            tile[31].image = ImageIO.read(fileRoadLeft);
            tile[32].image = ImageIO.read(fileRoadRight);
            tile[33].image = ImageIO.read(fileRoadTop);
            tile[34].image = ImageIO.read(fileRoadBottom);
            tile[35].image = ImageIO.read(fileRoadTopLeft);
            tile[36].image = ImageIO.read(fileRoadTopRight);
            tile[37].image = ImageIO.read(fileRoadBottomLeft);
            tile[38].image = ImageIO.read(fileRoadBottomRight);

            //dark building roof - 4
            tile[40].image = ImageIO.read(fileDarkBuildRoof);
            tile[41].image = ImageIO.read(fileDarkBuildRoofLeft);
            tile[42].image = ImageIO.read(fileDarkBuildRoofRight);
            tile[43].image = ImageIO.read(fileDarkBuildRoofTopMiddle);
            tile[44].image = ImageIO.read(fileDarkBuildRoofBottom);
            tile[45].image = ImageIO.read(fileDarkBuildRoofTopLeft);
            tile[46].image = ImageIO.read(fileDarkBuildRoofTopRight);
            tile[47].image = ImageIO.read(fileDarkBuildRoofBottomLeft);
            tile[48].image = ImageIO.read(fileDarkBuildRoofBottomRight);

            //dark building blocks - 5
            tile[50].image = ImageIO.read(fileDarkBuildFloorMiddle);
            tile[51].image = ImageIO.read(fileDarkBuildFloorLeft);
            tile[52].image = ImageIO.read(fileDarkBuildFloorRight);
            tile[53].image = ImageIO.read(fileDarkBuildFloorMiddle);
            tile[54].image = ImageIO.read(fileDarkBuildParter);
            tile[55].image = ImageIO.read(fileDarkBuildFloorLeft);
            tile[56].image = ImageIO.read(fileDarkBuildFloorRight);
            tile[57].image = ImageIO.read(fileDarkBuildParterLeft);
            tile[58].image = ImageIO.read(fileDarkBuildParterRight);


            // to do : rest of blocks




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
