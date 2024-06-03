package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class MouseHandler extends MouseAdapter implements Runnable {
    private final GameBoard gb;
    private final MousePosition mPosition;
    private boolean aimed;
    private int aimedMonsterId;

    public MouseHandler(GameBoard gb, MousePosition mPosition) {
        this.gb = gb;
        this.mPosition = mPosition;
        gb.addMouseListener(this);
        gb.addMouseMotionListener(this);

    }

    @Override
    public void run() {
        // The run method can be used to handle any other logic in the future.
        while (true) {
            try {
                // System.out.print("mouseevt");
                //System.out.println(mPosition.getX());
                Thread.sleep(100); // Adjust the sleep time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
        int x = e.getX();
        int y = e.getY();
        for (int i = 0; i < gb.monster.length; i++) {
            if(gb.monster[i]!=null){
            if(x >= gb.monster[i].screenX && x <=gb.monster[i].screenX+48){
                if((y >= gb.monster[i].screenY && y <=gb.monster[i].screenY+48))
                    aimed = true;
                    aimedMonsterId = i;
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
            System.out.println("crosshair in the mdidle");
            gb.monster[aimedMonsterId].takeDMG(1);

        }
        
    }

