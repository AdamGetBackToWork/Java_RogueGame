// Klasa do wypelniania tablicy obiektow odpowiednimi grafikami o odpowiednich polach

package main;

// importy javy
import object.ObjectBottles;
import object.ObjectCar;
import object.ObjectLamp;
import object.ObjectTrashBin;

// importy projektowe
import monster.MonsterWatcher;

// cialo klasy
public class ObjectHandler {

    GameBoard gb;

    // konstruktor klasy
    public ObjectHandler(GameBoard gb) {
        this.gb = gb;
    }

    // okreslanie pozycji obiektow na mapie, niestety mozolne ale jest ok
    // dalszy pomysl, czytanie z pliku .txt rozmieszczenia ich
    public void setObject() {
        // parking lot
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

        gb.obj[31] = new ObjectCar(3, true);
        gb.obj[31].worldX = 24 * gb.finalTileSize;
        gb.obj[31].worldY = 21 * gb.finalTileSize;
        gb.obj[32] = new ObjectCar(2, false);
        gb.obj[32].worldX = 7 * gb.finalTileSize;
        gb.obj[32].worldY = 15 * gb.finalTileSize;
        gb.obj[32] = new ObjectCar(3, false);
        gb.obj[32].worldX = 22 * gb.finalTileSize;
        gb.obj[32].worldY = 15 * gb.finalTileSize;
        // lamps
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
        gb.obj[14] = new ObjectLamp();
        gb.obj[14].worldX = 33 * gb.finalTileSize;
        gb.obj[14].worldY = 20 * gb.finalTileSize;
        gb.obj[15] = new ObjectLamp();
        gb.obj[15].worldX = 22 * gb.finalTileSize;
        gb.obj[15].worldY = 30 * gb.finalTileSize;
        gb.obj[16] = new ObjectLamp();
        gb.obj[16].worldX = 33 * gb.finalTileSize;
        gb.obj[16].worldY = 30 * gb.finalTileSize;
        gb.obj[17] = new ObjectLamp();
        gb.obj[17].worldX = 33 * gb.finalTileSize;
        gb.obj[17].worldY = 11 * gb.finalTileSize;
        gb.obj[26] = new ObjectLamp();
        gb.obj[26].worldX = 26 * gb.finalTileSize;
        gb.obj[26].worldY = 23 * gb.finalTileSize;
        gb.obj[27] = new ObjectLamp();
        gb.obj[27].worldX = 26 * gb.finalTileSize;
        gb.obj[27].worldY = 27 * gb.finalTileSize;
        gb.obj[28] = new ObjectLamp();
        gb.obj[28].worldX = 29 * gb.finalTileSize;
        gb.obj[28].worldY = 23 * gb.finalTileSize;
        gb.obj[29] = new ObjectLamp();
        gb.obj[29].worldX = 29 * gb.finalTileSize;
        gb.obj[29].worldY = 27 * gb.finalTileSize;
        gb.obj[30] = new ObjectLamp();
        gb.obj[30].worldX = 14 * gb.finalTileSize;
        gb.obj[30].worldY = 31 * gb.finalTileSize;
        // bins
        gb.obj[10] = new ObjectTrashBin();
        gb.obj[10].worldX = 13 * gb.finalTileSize;
        gb.obj[10].worldY = 21 * gb.finalTileSize;
        gb.obj[18] = new ObjectTrashBin();
        gb.obj[18].worldX = 23 * gb.finalTileSize;
        gb.obj[18].worldY = 31 * gb.finalTileSize;
        gb.obj[19] = new ObjectTrashBin();
        gb.obj[19].worldX = 23 * gb.finalTileSize;
        gb.obj[19].worldY = 23 * gb.finalTileSize;
        gb.obj[20] = new ObjectTrashBin();
        gb.obj[20].worldX = 32 * gb.finalTileSize;
        gb.obj[20].worldY = 31 * gb.finalTileSize;
        gb.obj[21] = new ObjectTrashBin();
        gb.obj[21].worldX = 32 * gb.finalTileSize;
        gb.obj[21].worldY = 23 * gb.finalTileSize;
        // bottles
        gb.obj[22] = new ObjectBottles();
        gb.obj[22].worldX = 22 * gb.finalTileSize;
        gb.obj[22].worldY = 23 * gb.finalTileSize;
        gb.obj[23] = new ObjectBottles();
        gb.obj[23].worldX = 31 * gb.finalTileSize;
        gb.obj[23].worldY = 22 * gb.finalTileSize;
        gb.obj[24] = new ObjectBottles();
        gb.obj[24].worldX = 24 * gb.finalTileSize;
        gb.obj[24].worldY = 31 * gb.finalTileSize;
        gb.obj[25] = new ObjectBottles();
        gb.obj[25].worldX = 31 * gb.finalTileSize;
        gb.obj[25].worldY = 24 * gb.finalTileSize;
    }

    // metoda do stawiania potwora na mapie
    public void setMonster() {
        gb.monster[0] = new MonsterWatcher(gb);
        gb.monster[0].worldX = gb.finalTileSize * 27;
        gb.monster[0].worldY = gb.finalTileSize * 32;

    }
}
