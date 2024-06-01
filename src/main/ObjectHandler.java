package main;

import monster.MonsterWatcher;
import object.ObjectCar;
import object.ObjectLamp;
import object.ObjectTrashBin;


public class ObjectHandler {

    GameBoard gb;

    public ObjectHandler(GameBoard gb){
        this.gb = gb;
    }

    public void setObject(){
        //parking lot
        gb.obj[0] = new ObjectCar(1, true);
        gb.obj[0].worldX = 5 * gb.finalTileSize;
        gb.obj[0].worldY = 23 * gb.finalTileSize;   
        
        gb.obj[1] = new ObjectCar(2, false);
        gb.obj[1].worldX = 5 * gb.finalTileSize;
        gb.obj[1].worldY = 24 * gb.finalTileSize;  

        gb.obj[2] = new ObjectCar(3, false);
        gb.obj[2].worldX = 5 * gb.finalTileSize;
        gb.obj[2].worldY = 27 * gb.finalTileSize;  
        
        gb.obj[3] = new ObjectCar(1, true);
        gb.obj[3].worldX = 5 * gb.finalTileSize;
        gb.obj[3].worldY = 28 * gb.finalTileSize;          
        gb.obj[4] = new ObjectCar(2, false);
        gb.obj[4].worldX = 5 * gb.finalTileSize;
        gb.obj[4].worldY = 29 * gb.finalTileSize;  
        gb.obj[5] = new ObjectCar(3, true);
        gb.obj[5].worldX = 5 * gb.finalTileSize;
        gb.obj[5].worldY = 30 * gb.finalTileSize;  
        gb.obj[6] = new ObjectCar(1, false);
        gb.obj[6].worldX = 10 * gb.finalTileSize;
        gb.obj[6].worldY = 30 * gb.finalTileSize;          
        gb.obj[7] = new ObjectCar(2, false);
        gb.obj[7].worldX = 10 * gb.finalTileSize;
        gb.obj[7].worldY = 31 * gb.finalTileSize;  
        gb.obj[8] = new ObjectCar(3, false);
        gb.obj[8].worldX = 10 * gb.finalTileSize;
        gb.obj[8].worldY = 32 * gb.finalTileSize; 
        //lamps
        gb.obj[9] = new ObjectLamp();
        gb.obj[9].worldX = 14 * gb.finalTileSize;
        gb.obj[9].worldY = 20 * gb.finalTileSize;  
        gb.obj[11] = new ObjectLamp();
        gb.obj[11].worldX = 22 * gb.finalTileSize;
        gb.obj[11].worldY = 20 * gb.finalTileSize;  
        gb.obj[12] = new ObjectLamp();
        gb.obj[12].worldX = 22 * gb.finalTileSize;
        gb.obj[12].worldY = 11 * gb.finalTileSize; 
        gb.obj[13] = new ObjectLamp();
        gb.obj[13].worldX = 14 * gb.finalTileSize;
        gb.obj[13].worldY = 11 * gb.finalTileSize; 
        //bins
        gb.obj[10] = new ObjectTrashBin();
        gb.obj[10].worldX = 13 * gb.finalTileSize;
        gb.obj[10].worldY = 21 * gb.finalTileSize;  
    }

        public void setMonster(){
        gb.monster[0] = new MonsterWatcher(gb);
        gb.monster[0].worldX = gb.finalTileSize*23;
        gb.monster[0].worldY = gb.finalTileSize*36;

        gb.monster[1] = new MonsterWatcher(gb);
        gb.monster[1].worldX = gb.finalTileSize*23;
        gb.monster[1].worldY = gb.finalTileSize*38;
    }
}
