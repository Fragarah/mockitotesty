public class ChessBoardTestMockito {
    import org.junit.Test;
import org.mockito.Mockito;
import src.main.ChessBoard;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

    public class ChessBoardTestMockito {
        @Test
        public void testMockCountFunction() {
            ChessBoard board = Mockito.mock(ChessBoard.class);
            when(board.count()).thenReturn(2);
            assertEquals(2, board.count());
        }
    }
}
