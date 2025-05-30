import org.example.ChessBoard;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(Enclosed.class)
public class ChessBoardTestMockito {


    public static class chessBoardTestMockito {
        @Test
        public void testMockCountFunction() {
            ChessBoard board = Mockito.mock(ChessBoard.class);
            when(board.count()).thenReturn(2);
            assertEquals(2, board.count());
        }
    }
}
