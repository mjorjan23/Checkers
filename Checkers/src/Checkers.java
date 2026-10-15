public class Checkers {
    public static void main(String[] args) {

        /*
        This is the drive class for Checkers. We follow the standard rule of a 2 player checkers. player 1 is
        designated by X and player 2 is designated by O.

        Full explanation in doc file provided.
        Summaries included in board.java, pieces.java, and move.java
         */

        Board board = Board.getInstance();  //creating a single board instance
        Move moveHandler = new Move(board); // Create an instance of Move
        moveHandler.addObserver(board);     //making the board and observer. will display whenever a move is made

        //intro to user
        System.out.println("Welcome to Checkers!");
        System.out.println("X represents a non king piece on team 1");
        System.out.println("O represents a non king piece on team 2");
        System.out.println("K represents a king piece on team 1");
        System.out.println("Q represents a king piece on team 2");
        System.out.println(". represents a blank space");

        // Initialize pieces for the board. Filling the board with pieces objects in all 64 slots
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (i >= 0 && i < 3) {
                    if ((i + j) % 2 == 0) {
                        board.getBoard()[i][j] = PieceFactory.createPiece("first", i, j);
                    } else {
                        board.getBoard()[i][j] = new emptySlot();
                    }
                } else if (i >= 5 && i < 8) {
                    if ((i + j) % 2 == 0) {
                        board.getBoard()[i][j] = PieceFactory.createPiece("second", i, j);
                    } else {
                        board.getBoard()[i][j] = new emptySlot();
                    }
                } else {
                    board.getBoard()[i][j] = new emptySlot();
                }
            }
        }

        //alternating turns and playing the game
        int curTeam = 1;
        boolean gameEnd = false;
        while (!gameEnd) {
            if (moveHandler.move(curTeam)) {
                moveHandler.killAgain(curTeam);
            }

            boolean appear1 = false, appear2 = false;
            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    if (!appear1 && board.getBoard()[i][j].getTeam() == 1) appear1 = true;
                    if (!appear2 && board.getBoard()[i][j].getTeam() == 2) appear2 = true;
                }
            }

            if (!appear1) {
                System.out.println("Player 2 Wins!");
                break;
            }
            if (!appear2) {
                System.out.println("Player 1 Wins!");
                break;
            }

            curTeam = (curTeam == 1) ? 2 : 1; // Swap between teams 1 and 2
        }
    }
}

