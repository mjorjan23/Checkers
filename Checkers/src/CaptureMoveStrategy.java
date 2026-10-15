public class CaptureMoveStrategy implements MoveStrategy {

    //This is the capture move strategy

    @Override
    public boolean isValidMove(int fromX, int fromY, int toX, int toY, int team, boolean isKing, Board board) {
        int dx = toX - fromX;
        int dy = toY - fromY;
        int midX = fromX + dx / 2;
        int midY = fromY + dy / 2;
        boolean validCaptureMove = (team == 1 && dy == 2) || (team == 2 && dy == -2) || isKing;
        return Math.abs(dx) == 2 && Math.abs(dy) == 2 && board.getBoard()[toY][toX].getTeam() == 0
                && board.getBoard()[midY][midX].getTeam() == (3 - team) && validCaptureMove;
    }

    @Override
    public void executeMove(int fromX, int fromY, int toX, int toY, Board board) {
        int midX = fromX + (toX - fromX) / 2;
        int midY = fromY + (toY - fromY) / 2;
        board.getBoard()[midY][midX] = new emptySlot();
        board.getBoard()[toY][toX] = board.getBoard()[fromY][fromX];
        board.getBoard()[fromY][fromX] = new emptySlot();
    }

    @Override
    public boolean shouldContinue(int fromX, int fromY, int toX, int toY, int team, Board board) {
        return true; // Capture move allows another kill move
    }
}
