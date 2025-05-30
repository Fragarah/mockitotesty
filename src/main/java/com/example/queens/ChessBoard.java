package com.example.queens;

import java.io.*;
import java.nio.file.*;

public class ChessBoard {
    private final int size;
    private final boolean[][] board;
    private final QueenCounter counter;

    public ChessBoard(int size, QueenCounter counter) {
        if (size <= 0) {
            throw new IllegalArgumentException("Board size must be positive");
        }
        this.size = size;
        this.board = new boolean[size][size];
        this.counter = counter;
    }

    /**
     * Places a queen on the specified position
     * @param row Row index (0-based)
     * @param col Column index (0-based)
     * @return true if queen was placed successfully, false if position was already occupied
     * @throws IllegalArgumentException if position is outside the board
     */
    public boolean placeQueen(int row, int col) {
        validatePosition(row, col);
        if (board[row][col]) {
            return false;
        }
        board[row][col] = true;
        return true;
    }

    /**
     * Removes a queen from the specified position
     * @param row Row index (0-based)
     * @param col Column index (0-based)
     * @throws IllegalArgumentException if position is outside the board
     */
    public void removeQueen(int row, int col) {
        validatePosition(row, col);
        board[row][col] = false;
    }

    /**
     * Checks if a position has a queen
     * @param row Row index (0-based)
     * @param col Column index (0-based)
     * @return true if there is a queen at the specified position
     * @throws IllegalArgumentException if position is outside the board
     */
    public boolean hasQueen(int row, int col) {
        validatePosition(row, col);
        return board[row][col];
    }

    /**
     * Returns the number of attacking queen pairs
     * @return number of queen pairs that are attacking each other
     */
    public int countAttackingQueens() {
        return counter.count(board);
    }

    /**
     * Saves the current board state to a file
     * @param filePath path to the file
     * @throws IOException if there's an error writing to the file
     */
    public void saveToFile(String filePath) throws IOException {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(filePath))) {
            writer.write(size + "\n");
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    writer.write(board[i][j] ? "Q" : ".");
                }
                writer.write("\n");
            }
        }
    }

    /**
     * Loads board state from a file
     * @param filePath path to the file
     * @throws IOException if there's an error reading the file
     * @throws IllegalArgumentException if file format is invalid
     */
    public static ChessBoard loadFromFile(String filePath, QueenCounter counter) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            int size = Integer.parseInt(reader.readLine());
            ChessBoard board = new ChessBoard(size, counter);
            
            for (int i = 0; i < size; i++) {
                String line = reader.readLine();
                if (line == null || line.length() != size) {
                    throw new IllegalArgumentException("Invalid file format");
                }
                for (int j = 0; j < size; j++) {
                    if (line.charAt(j) == 'Q') {
                        board.placeQueen(i, j);
                    } else if (line.charAt(j) != '.') {
                        throw new IllegalArgumentException("Invalid character in file");
                    }
                }
            }
            return board;
        }
    }

    private void validatePosition(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            throw new IllegalArgumentException("Position (" + row + "," + col + ") is outside the board");
        }
    }

    public int getSize() {
        return size;
    }
} 