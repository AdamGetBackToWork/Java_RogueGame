package main;

import entity.Entity;

public class CollisionHandler {

    GameBoard gb;

    public CollisionHandler(GameBoard gb){
        this.gb = gb;
    }

    public void checkTile(Entity entity){
        
        int entityLeftX = entity.worldX + entity.solidRectangle.x;
        int entityRightX= entity.worldX + entity.solidRectangle.x + entity.solidRectangle.width;
        int entityTopY = entity.worldY + entity.solidRectangle.y;
        int entityBottomY = entity.worldY + entity.solidRectangle.y + entity.solidRectangle.height;

        int entityLeftCol = entityLeftX/gb.finalTileSize;
        int entityRightCol = entityRightX/gb.finalTileSize;
        int entityTopRow = entityTopY/gb.finalTileSize;
        int entityBottomRow = entityBottomY/gb.finalTileSize;

        int tileNum1, tileNum2;

        switch(entity.direction){
            case "up":
                entityTopRow = (entityTopY - entity.moveSpeed)/gb.finalTileSize;                
                tileNum1 = gb.th.tileNum[entityLeftCol][entityTopRow];
                tileNum2 = gb.th.tileNum[entityRightCol][entityTopRow];
                if(gb.th.tile[tileNum1].collision == true || gb.th.tile[tileNum2].collision == true){
                    entity.collisionState = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomY + entity.moveSpeed)/gb.finalTileSize;                
                tileNum1 = gb.th.tileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gb.th.tileNum[entityRightCol][entityBottomRow];
                if(gb.th.tile[tileNum1].collision == true || gb.th.tile[tileNum2].collision == true){
                    entity.collisionState = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftX - entity.moveSpeed)/gb.finalTileSize;                
                tileNum1 = gb.th.tileNum[entityLeftCol][entityTopRow];
                tileNum2 = gb.th.tileNum[entityLeftCol][entityBottomRow];
                if(gb.th.tile[tileNum1].collision == true || gb.th.tile[tileNum2].collision == true){
                    entity.collisionState = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightX + entity.moveSpeed)/gb.finalTileSize;                
                tileNum1 = gb.th.tileNum[entityRightCol][entityTopRow];
                tileNum2 = gb.th.tileNum[entityRightCol][entityBottomRow];
                if(gb.th.tile[tileNum1].collision == true || gb.th.tile[tileNum2].collision == true){
                    entity.collisionState = true;
                }
                break;
            default:
                System.err.println("Invalid direction: " + entity.direction);
            break;

        }

    }

    public int checkObject(Entity entity, boolean player){

        int index = 100;

        for(int i = 0; i < gb.obj.length; i++){

            if(gb.obj[i] != null){
                entity.solidRectangle.x = entity.worldX + entity.solidRectangle.x;
                entity.solidRectangle.y = entity.worldY + entity.solidRectangle.y;

                gb.obj[i].solidArea.x = gb.obj[i].worldX + gb.obj[i].solidArea.x;
                gb.obj[i].solidArea.y = gb.obj[i].worldY + gb.obj[i].solidArea.y;
            
                switch(entity.direction){
                    case "up":
                        entity.solidRectangle.y -= entity.moveSpeed;
                        if(entity.solidRectangle.intersects(gb.obj[i].solidArea)){
                            if(gb.obj[i].collision == true){
                                entity.collisionState = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "down":
                        entity.solidRectangle.y += entity.moveSpeed;
                        if(entity.solidRectangle.intersects(gb.obj[i].solidArea)){
                            if(gb.obj[i].collision == true){
                                entity.collisionState = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "left":
                        entity.solidRectangle.x -= entity.moveSpeed;
                        if(entity.solidRectangle.intersects(gb.obj[i].solidArea)){
                            if(gb.obj[i].collision == true){
                                entity.collisionState = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                    case "right":
                        entity.solidRectangle.x += entity.moveSpeed;
                        if(entity.solidRectangle.intersects(gb.obj[i].solidArea)){
                            if(gb.obj[i].collision == true){
                                entity.collisionState = true;
                            }
                            if(player == true){
                                index = i;
                            }
                        }
                        break;
                }
                entity.solidRectangle.x = entity.solidAreaX;
                entity.solidRectangle.y = entity.solidAreaY;
                gb.obj[i].solidArea.x = gb.obj[i].solidAreaX;
                gb.obj[i].solidArea.y = gb.obj[i].solidAreaY;
                
            }
            

        }

        return index;
    }

    public int checkEntity(Entity entity, Entity[] target){

        int index = 100;

        for(int i = 0; i < target.length; i++){

            if(target[i] != null){
                entity.solidRectangle.x = entity.worldX + entity.solidRectangle.x;
                entity.solidRectangle.y = entity.worldY + entity.solidRectangle.y;

                target[i].solidRectangle.x = target[i].worldX + target[i].solidRectangle.x;
                target[i].solidRectangle.y = target[i].worldY + target[i].solidRectangle.y;
            
                switch(entity.direction){
                    case "up":
                        entity.solidRectangle.y -= entity.moveSpeed;
                        break;
                    case "down":
                        entity.solidRectangle.y += entity.moveSpeed;
                        break;
                    case "left":
                        entity.solidRectangle.x -= entity.moveSpeed;
                        break;
                    case "right":
                        entity.solidRectangle.x += entity.moveSpeed;
                        break;
                }
                if(entity.solidRectangle.intersects(target[i].solidRectangle)){
                    if(target[i] != entity){
                        entity.collisionState = true;
                        index = i;
                    }
                }
                entity.solidRectangle.x = entity.solidAreaX;
                entity.solidRectangle.y = entity.solidAreaY;
                target[i].solidRectangle.x = target[i].solidAreaX;
                target[i].solidRectangle.y = target[i].solidAreaY;
                
            }
            

        }
        return index;
    }

    // Klasa zwracająca bool czy entity dotyka gracza
    public boolean checkPlayer(Entity entity){

        boolean contact = false;

        entity.solidRectangle.x = entity.worldX + entity.solidRectangle.x;
        entity.solidRectangle.y = entity.worldY + entity.solidRectangle.y;

        gb.player.solidRectangle.x = gb.player.worldX + gb.player.solidRectangle.x;
        gb.player.solidRectangle.y = gb.player.worldY + gb.player.solidRectangle.y;
    
        switch(entity.direction){
            case "up":
                entity.solidRectangle.y -= entity.moveSpeed;
                break;
            case "down":
                entity.solidRectangle.y += entity.moveSpeed;
                break;
            case "left":
                entity.solidRectangle.x -= entity.moveSpeed;
                break;
            case "right":
                entity.solidRectangle.x += entity.moveSpeed;
                break;
        }
        if(entity.solidRectangle.intersects(gb.player.solidRectangle)){
            entity.collisionState = true;
            contact = true;
        }

        entity.solidRectangle.x = entity.solidAreaX;
        entity.solidRectangle.y = entity.solidAreaY;
        gb.player.solidRectangle.x = gb.player.solidAreaX;
        gb.player.solidRectangle.y = gb.player.solidAreaY;

        return contact;
    }

}
