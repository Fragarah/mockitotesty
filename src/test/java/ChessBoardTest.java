import org.example.ChessBoard;
import org.example.FileHandler;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class ChessBoardTest {
    @Test
    public void testSaveAndLoad() throws IOException {
        ChessBoard board = new ChessBoard(8);
        board.placeQueen(0, 0);
        board.placeQueen(1, 2);
        String filePath = "chessboard.txt";
        FileHandler.saveBoard(board, filePath);

        ChessBoard loadedBoard = FileHandler.loadBoard(filePath, 8);
        assertTrue(loadedBoard.isValidPosition(0, 0));
        assertTrue(loadedBoard.isValidPosition(1, 2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidPlacement() {
        ChessBoard board = new ChessBoard(8);
        board.placeQueen(10, 10);
    }
}