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
        }

    }
}
