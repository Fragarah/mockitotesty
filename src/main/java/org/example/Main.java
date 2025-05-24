package org.example;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ChessBoard board = new ChessBoard(8);

        System.out.println("Wstawianie hetmana na planszę (podaj wiersz i kolumnę): ");
        int row = scanner.nextInt();
        int col = scanner.nextInt();

        board.placeQueen(row, col);
        board.displayBoard();

        try {
            FileHandler.saveBoard(board, "chessboard.txt");
        } catch (IOException e) {
            System.err.println("Błąd zapisu do pliku!");
        }
    }
}
