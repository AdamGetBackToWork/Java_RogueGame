package Projekt;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        
        JFrame window = new JFrame();

        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        
        window.setTitle("Streets of Abbys");

        GameBoard gameBoard = new GameBoard();
        window.add(gameBoard);

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
    }
}
