package Projekt.src.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

    public boolean upPress, downPress, leftPress, rightPress, elsePress;

    @Override
    public void keyTyped(KeyEvent e){
    }

    @Override
    public void keyPressed(KeyEvent e){

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPress = true;
        }
        if(code == KeyEvent.VK_S){
            downPress = true;
        }
        if(code == KeyEvent.VK_A){
            leftPress = true;
        }
        if(code == KeyEvent.VK_D){
            rightPress = true;
        }
        if (!(code == KeyEvent.VK_W || code == KeyEvent.VK_A || code == KeyEvent.VK_S || code == KeyEvent.VK_D)) {
            elsePress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e){

        int code = e.getKeyCode();

        if(code == KeyEvent.VK_W){
            upPress = false;
        }
        if(code == KeyEvent.VK_S){
            downPress = false;
        }
        if(code == KeyEvent.VK_A){
            leftPress = false;
        }
        if(code == KeyEvent.VK_D){
            rightPress = false;
        }
        if (!(code == KeyEvent.VK_W || code == KeyEvent.VK_A || code == KeyEvent.VK_S || code == KeyEvent.VK_D)) {
            elsePress = false;
        }
    }
}