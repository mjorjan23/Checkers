public class StandardMoveStrategy implements MoveStrategy {

    //This is the standard move strategy

    private static final int BOARD_SIZE = 8;

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY, int team, boolean isKing, Board board) {
        int dx = toX - fromX;
        int dy = toY - fromY;
        boolean validForwardMove = (team == 1 && dy == 1) || (team == 2 && dy == -1);
        return Math.abs(dx) == 1 && Math.abs(dy) == 1 && board.getBoard()[toY][toX].getTeam() == 0
                && (validForwardMove || isKing);
    }

    @Override
    public void executeMove(int fromX, int fromY, int toX, int toY, Board board) {
        board.getBoard()[toY][toX] = board.getBoard()[fromY][fromX];
        board.getBoard()[fromY][fromX] = new emptySlot();
    }

    @Override
    public boolean shouldContinue(int fromX, int fromY, int toX, int toY, int team, Board board) {
        return false; // No capture, so no need to continue
    }
}
