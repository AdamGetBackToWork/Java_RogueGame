package main;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame();
        GameBoard gameBoard = new GameBoard();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Streets of Abbys");
        window.add(gameBoard);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gameBoard.startGameThread();
    }
}
