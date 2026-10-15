public class Board implements Observer
{
    //singleton design pattern when instantiating board
    //observer pattern observer(called upon when a move is made)

    /*
    * This class created the 8 by 8 board. It will store pieces in every one of its slots(1st player piece, 2nd
    * player piece, or empty piece. There is a creation portion and a display portion. The display portion will be
    * called upon by the observer pattern once every move is made. For the board, we will be utilizing the singleton
    * pattern. For any game, only ONE game can be created. That is why this is pattern is so important. We cannot
    * have multiple instances of the board. That will be illegal in checkers. This also ensures that the players will
    *  play on one board and only THAT board will be displayed
    */


    private Pieces[][] board;
    private final int SIZE = 8;

    private static Board instance = null;

    //ensuring only one instance of board is created
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    //privatizing the creation of the board(singleton pattern)
    private Board() {
        board = new Pieces[SIZE][SIZE];
    }

    public Pieces[][] getBoard()
    {
        return board;
    }

    public void display() { // display board
        System.out.println("    0 1 2 3 4 5 6 7"); // x axis
        System.out.println("   ----------------");
        for(int i = 0; i < 8; i++){
            System.out.print(i + " | ");
            for(int j = 0; j < 8; j++){
                if((i + j) % 2 == 0) { // only display alternate spaces
                    if (board[i][j].getTeam() != 0) {
                        if(board[i][j].kingStatus() && board[i][j].getTeam() == 1) { //determine if king piece or not
                            System.out.print("K ");
                        }
                        else if(board[i][j].kingStatus() && board[i][j].getTeam() == 2) {
                            System.out.print("Q ");
                        }
                        else if( board[i][j].getTeam() == 1)
                            System.out.print("X ");
                        else
                            System.out.print("O ");
                    }
                    else {
                        System.out.print(". ");
                    }
                }
                else{
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    //will be ran whenever a move is made(observer pattern)
    public void update(){
        display();
    }
}
