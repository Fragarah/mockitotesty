package org.example;

public class ChessBoard {
    private final int size;
    private final boolean[][] board;

    public ChessBoard(int size) {
        this.size = size;
        board = new boolean[size][size];
    }

    public void placeQueen(int row, int col) {
        if (isValidPosition(row, col)) {
            board[row][col] = true;
        } else {
            throw new IllegalArgumentException("Nieprawidłowa pozycja hetmana!");
        }
    }

    public void removeQueen(int row, int col) {
        if (isValidPosition(row, col)) {
            board[row][col] = false;
        } else {
            throw new IllegalArgumentException("Nieprawidłowa pozycja do usunięcia hetmana!");
        }
    }

    public boolean isValidPosition(int row, int col) {
        return row >= 0 && row < size && col >= 0 && col < size;
    }

    public void displayBoard() {
        for (boolean[] row : board) {
            for (boolean cell : row) {
                System.out.print(cell ? "Q " : ". ");
            }
            System.out.println();
        }
    }
    public int getSize() {
        return size; // Assuming `size` is a field in `ChessBoard`
    }

    public boolean isOccupied(int i, int j) {
        return board[i][j];
    }

}

