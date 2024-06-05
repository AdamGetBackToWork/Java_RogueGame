package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

// klasa odpowiedzialna za obsługę myszy w kontekście mechanik 
public class MouseHandler extends MouseAdapter implements Runnable {
    private final GameBoard gb;
    private final MousePosition mPosition;

    // pole świadczące o "wycelowaniu" w przeciwnika
    private boolean aimed;

    // pole inforumjące o id danego przeciwnika
    private int aimedMonsterId = -1;

    // pole świadczące o oddaniu strzału
    private boolean clicked;

    // konstruktor - przyjmuje GameBoard oraz MousePosition
    public MouseHandler(GameBoard gb, MousePosition mPosition) {
        this.gb = gb;
        this.mPosition = mPosition;
        gb.addMouseListener(this);
        gb.addMouseMotionListener(this);
    }

    // nadpisana metoda run
    @Override
    public void run() {
        while (true) {
            try {
                clicked = false;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // listener ruchu myszy - analizuje obecne polożenie kursora i wystawia
    // odpowiednie flagi
    @Override
    public void mouseMoved(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < gb.monster.length; i++) {
            if (gb.monster[i] != null) {
                if ((x >= gb.monster[i].screenX - 20 && x <= gb.monster[i].screenX + 48 + 20)
                        && ((y >= gb.monster[i].screenY - 20 && y <= gb.monster[i].screenY + 48 + 20))) {
                    aimed = true;
                    aimedMonsterId = i;
                } else {
                    aimed = false;
                    aimedMonsterId = -1;
                }
            } 
        }
    }

    // praktycznie to samo co mouseMoved, nie ma większego znaczenia poza updatem położenia
    @Override
    public void mouseDragged(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {

        if (aimed && gb.monster[aimedMonsterId] != null) {
            gb.monster[aimedMonsterId].takeDMG(1);
        }
        clicked = true;
    }

    public boolean getClickedStatus() {
        return this.clicked;
    }

    public boolean getAimedStatus() {
        return this.aimed;
    }

}
