import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CheckerTest {

    @Test
    public void testBoardSingleton() {
        Board board1 = Board.getInstance();
        Board board2 = Board.getInstance();
        assertSame(board1, board2);
    }

    @Test
    public void testBoardCreation() {
        Board board = Board.getInstance();
        Pieces[][] boardArray = board.getBoard();
        assertNotNull(boardArray);
        assertEquals(8, boardArray.length);
    }

    @Test
    public void testPiecePlacement() {
        Board board = Board.getInstance();
        Pieces piece = new xPieces(0, 0);
        board.getBoard()[0][0] = piece;
        assertSame(piece, board.getBoard()[0][0]);
    }

    @Test
    public void testEmptySlotInitialization() {
        emptySlot slot = new emptySlot();
        assertEquals(0, slot.getTeam());
        assertFalse(slot.kingStatus());
    }

    //testing first player piece initialization
    @Test
    public void testxPiecesInitialization() {
        xPieces piece = new xPieces(0, 0);
        assertEquals(1, piece.getTeam());
        assertFalse(piece.kingStatus());
    }

    //testing second player piece initialization
    @Test
    public void testoPiecesInitialization() {
        oPieces piece = new oPieces(0, 0);
        assertEquals(2, piece.getTeam());
        assertFalse(piece.kingStatus());
    }

    @Test
    public void testMakeKing() {
        xPieces piece = new xPieces(0, 0);
        piece.makeKing(true);
        assertTrue(piece.kingStatus());
    }

    @Test
    public void testSetCoordinates() {
        xPieces piece = new xPieces(0, 0);
        piece.setX(2);
        piece.setY(3);
        assertEquals(2, piece.getX());
        assertEquals(3, piece.getY());
    }

    @Test
    public void testCaptureMoveValidity() {
        Board board = Board.getInstance();
        board.getBoard()[1][1] = PieceFactory.createPiece("first", 1, 1);
        board.getBoard()[2][2] = PieceFactory.createPiece("second", 1, 1);
        board.getBoard()[3][3] = PieceFactory.createPiece("none",-1,-1);
        CaptureMoveStrategy strategy = new CaptureMoveStrategy();
        // Set up a board state for capture
        boolean isValid = strategy.isValidMove(1, 1, 3, 3, 1, false, board);
        assertTrue(isValid);
    }

    @Test
    public void testStandardMoveValidity() {
        Board board = Board.getInstance();
        board.getBoard()[1][1] = PieceFactory.createPiece("first", 1, 1);
        board.getBoard()[2][2] = PieceFactory.createPiece("none",-1,-1);
        StandardMoveStrategy strategy = new StandardMoveStrategy();
        boolean isValid = strategy.isValidMove(1, 1, 2, 2, 1, false, board);
        assertTrue(isValid);
    }

    @Test
    public void testCaptureMoveInvalidity() {
        Board board = Board.getInstance();
        board.getBoard()[1][1] = PieceFactory.createPiece("first", 1, 1);
        board.getBoard()[2][2] = PieceFactory.createPiece("second", 1, 1);
        board.getBoard()[3][3] = PieceFactory.createPiece("none",-1,-1);
        CaptureMoveStrategy strategy = new CaptureMoveStrategy();
        // Set up a board state for capture
        boolean isValid = strategy.isValidMove(1, 1, 3, 3, 2, false, board);
        assertFalse(isValid);
    }

    @Test
    public void testStandardMoveInvalidity() {
        Board board = Board.getInstance();
        board.getBoard()[1][1] = PieceFactory.createPiece("first", 1, 1);
        board.getBoard()[2][2] = PieceFactory.createPiece("none",-1,-1);
        StandardMoveStrategy strategy = new StandardMoveStrategy();
        boolean isValid = strategy.isValidMove(1, 1, 2, 2, 2, false, board);
        assertFalse(isValid);
    }

    @Test
    public void testCaptureMoveExecution() {
        Board board = Board.getInstance();
        CaptureMoveStrategy strategy = new CaptureMoveStrategy();
        // Set up a board state for capture
        strategy.executeMove(1, 1, 3, 3, board);
        assertEquals(board.getBoard()[3][3].getTeam(), 1);
        assertEquals(board.getBoard()[2][2].getTeam(), 0);
    }

}
