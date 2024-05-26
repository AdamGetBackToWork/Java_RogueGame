package main;

import javax.swing.JFrame;

public class Main {

    public static JFrame window;
    public static void main(String[] args) {
        
        window = new JFrame();
        GameBoard gameBoard = new GameBoard();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Streets of Abbys");
        //window.setUndecorated(true);
        window.add(gameBoard);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameBoard.gameSetup();
        gameBoard.startGameThread();
    }
}