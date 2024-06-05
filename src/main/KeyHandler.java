package main;

import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    GameBoard gb;
    public boolean upPress, downPress, leftPress, rightPress, elsePress, enterPress;

    public KeyHandler(GameBoard gb) {
        this.gb = gb;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        if (gb.gameState == gb.titleState) {
            titleState(code);
        }
        if (gb.gameState == gb.creditsState) {
            creditsState(code);
        }
        if (gb.gameState == gb.playState) {
            playState(code);
        } else if (gb.gameState == gb.pauseState) {
            pauseState(code);
        } else if (gb.gameState == gb.endGameState) {
            endGameState(code);
        }

        // if (gb.gameState == gb.menuKBState){
        // menuKBState(code);
        // }
        if (!(code == KeyEvent.VK_W || code == KeyEvent.VK_A || code == KeyEvent.VK_S || code == KeyEvent.VK_D
                || code == KeyEvent.VK_ESCAPE)) {
            elsePress = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();

        if (code == KeyEvent.VK_W) {
            upPress = false;
        }
        if (code == KeyEvent.VK_S) {
            downPress = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPress = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPress = false;
        }
        if (!(code == KeyEvent.VK_W || code == KeyEvent.VK_A || code == KeyEvent.VK_S || code == KeyEvent.VK_D)) {
            elsePress = false;
        }
    }

    public void playState(int code) {
        if (code == KeyEvent.VK_W) {
            upPress = true;
        }
        if (code == KeyEvent.VK_S) {
            downPress = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPress = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPress = true;
        }
        if (code == KeyEvent.VK_ESCAPE) {
            if (gb.gameState == gb.playState) {
                gb.gameState = gb.pauseState;
            } else if (gb.gameState == gb.pauseState) {
                gb.gameState = gb.playState;
            }
        }
    }

    private void titleState(int code) {
        if (code == KeyEvent.VK_W) {
            gb.ui.commandNumTitle--;
            if (gb.ui.commandNumTitle < 0) {
                gb.ui.commandNumTitle = 2;
            }
        }
        if (code == KeyEvent.VK_S) {
            gb.ui.commandNumTitle++;
            if (gb.ui.commandNumTitle > 2) {
                gb.ui.commandNumTitle = 0;
            }
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gb.ui.commandNumTitle == 0) {
                gb.gameState = gb.playState;
                gb.playMusic();
            }
            if (gb.ui.commandNumTitle == 1) {
                gb.gameState = gb.creditsState;
            }
            if (gb.ui.commandNumTitle == 2) {
                System.exit(0);
            }
        }
    }

    private void endGameState(int code) {

        if (code == KeyEvent.VK_ENTER) {
            if (gb.ui.commandNumEnd == 0) {
                System.exit(0);
            }
        }
    }

    private void creditsState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gb.gameState = gb.titleState;
        }
    }

    public void pauseState(int code) {
        if (code == KeyEvent.VK_ESCAPE) {
            gb.gameState = gb.playState;
        }
        if (code == KeyEvent.VK_ENTER) {
            if (gb.ui.commandNum == 0) {
                enterPress = true;
            }
            if (gb.ui.commandNum == 1) {
                enterPress = true;
            }
            if (gb.ui.commandNum == 2) {
                enterPress = true;
            }
            if (gb.ui.commandNum == 3) {
                enterPress = true;
            }
            if (gb.ui.commandNum == 4) {
                enterPress = true;
            }
        }
        if (code == KeyEvent.VK_W) {
            gb.ui.commandNum--;
            if (gb.ui.commandNum < 0) {
                gb.ui.commandNum = 4;
            }
        }
        if (code == KeyEvent.VK_S) {
            gb.ui.commandNum++;
            if (gb.ui.commandNum > 4) {
                gb.ui.commandNum = 0;
            }
        }
        if (code == KeyEvent.VK_A) {
            if (gb.ui.subState == 0) {
                if (gb.ui.commandNum == 1 && gb.sound.volumeBar > 0) {
                    gb.sound.volumeBar--;
                    gb.sound.controlVolume();
                }
                // if(gb.ui.commandNum == 2 && gb.fx.volumeBar > 0){
                // gb.fx.volumeBar--;
                // gb.fx.controlVolume();
                // }
            }
        }
        if (code == KeyEvent.VK_D) {
            if (gb.ui.subState == 0) {
                if (gb.ui.commandNum == 1 && gb.sound.volumeBar < 5) {
                    gb.sound.volumeBar++;
                    gb.sound.controlVolume();
                }
                // if(gb.ui.commandNum == 2 && gb.fx.volumeBar > 0){
                // gb.fx.volumeBar--;
                // gb.fx.controlVolume();
                // }
            }
        }
    }
    // public void menuKBState(int code){
    // if(code == KeyEvent.VK_ESCAPE){
    // gb.gameState = gb.playState;
    // }
    // if(code == KeyEvent.VK_ENTER){
    // if(gb.ui.commandNum == 0){
    // enterPress = true;
    // gb.ui.commandNum = 0;
    // }
    // if(gb.ui.commandNum == 1){
    // gb.gameState = gb.creditsState;
    // gb.ui.commandNum = 0;
    // }
    // if(gb.ui.commandNum == 2){
    // System.exit(0);
    // gb.ui.commandNum = 0;
    // }
    // if(gb.ui.commandNum == 3){
    // enterPress = true;
    // gb.ui.commandNum = 0;
    // }
    // if(gb.ui.commandNum == 4){
    // gb.gameState = gb.maybeQuitState;
    // gb.ui.commandNum = 0;
    // }
    // gb.ui.commandNum = 0;
    // enterPress = false;
    // }

    // if(code == KeyEvent.VK_W){
    // gb.ui.commandNum--;
    // if(gb.ui.commandNum < 0){
    // gb.ui.commandNum = 4;
    // }
    // }
    // if(code == KeyEvent.VK_S){
    // gb.ui.commandNum++;
    // if(gb.ui.commandNum > 4){
    // gb.ui.commandNum = 0;
    // }
    // }
    // }
}