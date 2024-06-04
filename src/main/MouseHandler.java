package main;

import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;


// to do : usunac mousePosition klase calkowicie, niepotrzebna!!

public class MouseHandler extends MouseAdapter implements Runnable {
    private final GameBoard gb;
    private final MousePosition mPosition;
    private boolean aimed;
    private int aimedMonsterId=-1;
    private boolean clicked;

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
                clicked = false;
                aimed = false;
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
            else{
                //aimed = false;
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
            if(aimed && gb.monster[aimedMonsterId]!=null){
                gb.monster[aimedMonsterId].takeDMG(1);
                
            }
            clicked = true;

        }
    public boolean getClickedStatus(){
        return this.clicked;
    }
    public boolean getAimedStatus(){
        return this.aimed;
    }

        
    }

