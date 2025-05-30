import org.example.ChessBoard;
import org.example.FileHandler;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertTrue;

public class FileHandlerTest {
    @Test
    public void testFileCreation() throws IOException {
        ChessBoard board = new ChessBoard(8);
        board.placeQueen(0, 0);
        FileHandler.saveBoard(board, "testboard.txt");

        ChessBoard loadedBoard = FileHandler.loadBoard("testboard.txt", 8);
        assertTrue(loadedBoard.isValidPosition(0, 0));
    }
}