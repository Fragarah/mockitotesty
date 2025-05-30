package com.example.queens;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ChessBoardMockitoTest {
    
    @Mock
    private QueenCounter mockCounter;
    
    private ChessBoard board;
    
    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        board = new ChessBoard(8, mockCounter);
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
        when(mockCounter.count(any())).thenReturn(2);
        
        board.placeQueen(0, 0);
        board.placeQueen(1, 1);
        
        assertEquals(2, board.countAttackingQueens());
        verify(mockCounter, times(1)).count(any());
    }
    
    @Test
    public void testSaveAndLoadBoard() throws IOException {
        Path tempFile = Files.createTempFile("chess", ".txt");
        
        board.placeQueen(0, 0);
        board.placeQueen(1, 1);
        
        board.saveToFile(tempFile.toString());
        
        ChessBoard loadedBoard = ChessBoard.loadFromFile(tempFile.toString(), mockCounter);
        
        assertEquals(board.getSize(), loadedBoard.getSize());
        assertTrue(loadedBoard.hasQueen(0, 0));
        assertTrue(loadedBoard.hasQueen(1, 1));
        assertFalse(loadedBoard.hasQueen(0, 1));
        
        Files.delete(tempFile);
    }
    
    @Test(expected = IOException.class)
    public void testLoadBoard_NonexistentFile() throws IOException {
        ChessBoard.loadFromFile("nonexistent.txt", mockCounter);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testLoadBoard_InvalidFormat() throws IOException {
        Path tempFile = Files.createTempFile("chess", ".txt");
        Files.write(tempFile, "invalid".getBytes());
        
        try {
            ChessBoard.loadFromFile(tempFile.toString(), mockCounter);
        } finally {
            Files.delete(tempFile);
        }
    }
} 