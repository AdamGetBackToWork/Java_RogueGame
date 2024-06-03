package main;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class MouseHandler extends MouseAdapter implements Runnable {
    private final GameBoard gb;
    private final MousePosition mPosition;

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
                //System.out.print("mouseevt");
                Thread.sleep(100);  // Adjust the sleep time as needed
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        mPosition.setPosition(e.getX(), e.getY());
    }
}

