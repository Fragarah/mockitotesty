import org.junit.Test;
import src.main.ChessBoard;
import src.main.FileHandler;

import java.io.IOException;
import static org.junit.Assert.*;

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