package main;

import java.awt.Rectangle;

public class EventHandler {

    GameBoard gb;
    Rectangle eventRect;
    int defEventRectX, defEventRectY;

    public EventHandler(GameBoard gb) {
        this.gb = gb;

        eventRect = new Rectangle();
        eventRect.x = gb.finalTileSize / 2;
        eventRect.y = gb.finalTileSize / 2;
        eventRect.width = 2;
        eventRect.height = 2;
        defEventRectX = eventRect.x;
        defEventRectY = eventRect.y;
    }

    public void checkEvent() {
        // if(hit()){

        // }
    }

    public boolean hit(int eventCol, int eventRow, String reqDirection) {

        boolean hit = false;

        gb.player.solidRectangle.x = gb.player.worldX + gb.player.solidRectangle.x;
        gb.player.solidRectangle.y = gb.player.worldY + gb.player.solidRectangle.y;
        eventRect.x = eventCol * gb.finalTileSize + eventRect.x;
        eventRect.y = eventRow * gb.finalTileSize + eventRect.y;

        if (gb.player.solidRectangle.intersects(eventRect)) {
            if (gb.player.direction.contentEquals(reqDirection) || reqDirection.contentEquals("any")) {
                hit = true;
            }
        }

        gb.player.solidRectangle.x = gb.player.solidAreaX;
        gb.player.solidRectangle.y = gb.player.solidAreaY;
        eventRect.x = defEventRectX;
        eventRect.y = defEventRectY;

        return hit;
    }
}
