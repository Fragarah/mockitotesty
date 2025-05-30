package com.example.queens;

/**
 * Interface for counting attacking queens on the chessboard
 */
public interface QueenCounter {
    /**
     * Counts the number of queens that are attacking each other on the chessboard
     * @param board The chessboard state (true represents a queen, false represents empty square)
     * @return Number of pairs of queens that are attacking each other
     */
    int count(boolean[][] board);
} 