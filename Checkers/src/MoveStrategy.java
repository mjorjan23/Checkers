public interface MoveStrategy {

    //parent interface of standardmovestrategy and capturemovestrategy. Full explanation ins move.java class

    //ensuring validity of move
    boolean isValidMove(int fromX, int fromY, int toX, int toY, int team, boolean isKing, Board board);

    //performing the actual move according to rules of subclass
    void executeMove(int fromX, int fromY, int toX, int toY, Board board);

    //for use only of capture to capture multiple pieces
    boolean shouldContinue(int fromX, int fromY, int toX, int toY, int team, Board board);
}