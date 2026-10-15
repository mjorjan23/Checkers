public class PieceFactory
{
    /*
    * factory for creating pieces. Pieces.java will have full explanation. In creating pieces, client just needs to
    * identify which team it's on and the coordinates of each piece.
    * */
    public static Pieces createPiece(String player, int x, int y)
    {
        if( player.equals("first"))
            return createXPiece(x,y);
        else if( player.equals("second"))
            return createOPiece(x,y);
        else
            return createEmptySlot();
    }

    private static Pieces createEmptySlot() {
        return new emptySlot();
    }

    private static Pieces createXPiece(int x, int y) {
        return new xPieces(x, y);
    }

    private static Pieces createOPiece(int x, int y) {
        return new oPieces(x, y);
    }
}

