//Autorzy: Michal Rostek i Adam Szajgin 
//Program tworzony w ramach projektu z przedmiotu Programowanie Obiektowe

package main;

// importy javy
import javax.swing.JFrame;

// cialo klasy
public class Main {

    // JFrame to stworzenia okna gry
    public static JFrame window;

    public static void main(String[] args) {

        // instancjonowanie okna i planszy rozgrywki - gry
        window = new JFrame();
        GameBoard gameBoard = new GameBoard();

        // ustawienia dotyczace ogna rozgrywki
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Streets of Abbys");

        // ponizsza linijka, jesli odkomentowana, tworzy gre w  trybie full screen
        // window.setUndecorated(true);
        // chociaz tak naprawde nalezy troche wiecej rzeczy odkomentowac w GameBoardzie i sposobie rysowania planszy

        // dodanie gry do okna i dalsze standardowe ustawienia pozycji okna, opakowania okna, oraz widocznosci okna
        window.add(gameBoard);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        // zaczecie watkow
        gameBoard.gameSetup();
        gameBoard.startGameThread();
    }
}