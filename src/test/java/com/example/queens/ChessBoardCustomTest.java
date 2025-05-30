package com.example.queens;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;

public class ChessBoardCustomTest {
    
    private CustomQueenCounter counter;
    private ChessBoard board;
    
    @Before
    public void setUp() {
        counter = new CustomQueenCounter(0);
        board = new ChessBoard(8, counter);
    }
    
    @Test
    public void testPlaceQueen_ValidPosition() {
        assertTrue(board.placeQueen(0, 0));
        assertTrue(board.hasQueen(0, 0));
    }
    
    @Test
    public void testPlaceQueen_OccupiedPosition() {
        board.placeQueen(0, 0);
        assertFalse(board.placeQueen(0, 0));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceQueen_InvalidPosition() {
        board.placeQueen(-1, 0);
    }
    
    @Test
    public void testCountAttackingQueens() {
        counter.setReturnValue(2);
        
        board.placeQueen(0, 0);
        board.placeQueen(1, 1);
        
        assertEquals(2, board.countAttackingQueens());
    }
    
    @Test
    public void testSaveAndLoadBoard() throws IOException {
        Path tempFile = Files.createTempFile("chess", ".txt");
        
        board.placeQueen(0, 0);
        board.placeQueen(1, 1);
        
        board.saveToFile(tempFile.toString());
        
        ChessBoard loadedBoard = ChessBoard.loadFromFile(tempFile.toString(), counter);
        
        assertEquals(board.getSize(), loadedBoard.getSize());
        assertTrue(loadedBoard.hasQueen(0, 0));
        assertTrue(loadedBoard.hasQueen(1, 1));
        assertFalse(loadedBoard.hasQueen(0, 1));
        
        Files.delete(tempFile);
    }
    
    @Test(expected = IOException.class)
    public void testLoadBoard_NonexistentFile() throws IOException {
        ChessBoard.loadFromFile("nonexistent.txt", counter);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLoadBoard_InvalidFormat() throws IOException {
        Path tempFile = Files.createTempFile("chess", ".txt");
        Files.write(tempFile, "invalid".getBytes());
        
        try {
            ChessBoard.loadFromFile(tempFile.toString(), counter);
        } finally {
            Files.delete(tempFile);
        }
    }
} 