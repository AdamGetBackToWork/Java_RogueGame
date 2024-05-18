package main;

import object.ObjectCar;


public class ObjectHandler {

    GameBoard gb;

    public ObjectHandler(GameBoard gb){
        this.gb = gb;
    }

    public void setObject(){
        gb.obj[0] = new ObjectCar();
        gb.obj[0].worldX = 11 * gb.finalTileSize;
        gb.obj[0].worldY = 6 * gb.finalTileSize;   
        
        gb.obj[1] = new ObjectCar();
        gb.obj[1].worldX = 14 * gb.finalTileSize;
        gb.obj[1].worldY = 9 * gb.finalTileSize;  


    }

    
}
