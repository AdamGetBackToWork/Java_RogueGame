// Dosyc dziwna klasa, wyszla w praniu
// sluzy do okreslania zdarzenia jak nasza postac w wyniku kolizji z potworem otrzymuje obrazenia

package main;

// importy javy
import java.awt.Rectangle;

// cialo klasy
public class EventHandler {

    GameBoard gb;
    Rectangle eventRect;
    int defEventRectX, defEventRectY;

    // konstruktor klasy
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

    // do overwrite'u lub czegos innego, szczerze mowiac zapomnialem po co mi to
    public void checkEvent() {}

    // metoda do sprawdzenia uderzenia w przeciwnika
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
