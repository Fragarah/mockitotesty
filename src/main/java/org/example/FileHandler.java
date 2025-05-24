package org.example;

import java.io.*;

public class FileHandler {
    public static void saveBoard(ChessBoard board, String filePath) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (int i = 0; i < board.getSize(); i++) {
                for (int j = 0; j < board.getSize(); j++) {
                    writer.write(board.isOccupied(i, j) ? "Q" : ".");
                }
                writer.newLine();
            }
        }
    }

    public static ChessBoard loadBoard(String filePath, int size) throws IOException {
        ChessBoard board = new ChessBoard(size);
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null) {
                for (int col = 0; col < line.length(); col++) {
                    if (line.charAt(col) == 'Q') {
                        board.placeQueen(row, col);
                    }
                }
                row++;
            }
        }
        return board;
    }
}


