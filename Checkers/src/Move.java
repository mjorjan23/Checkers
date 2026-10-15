import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Move {

    //Strategy pattern used to choose basic move or capture move

    /*
    The move class is where we actually move the piece or capture the pieces. There are going to be two design
    patterns present here. The first pattern is going to be the strategy pattern. We use the pattern when deciding
    which type of move to utilize. There is a standard move and a capture move highlighted in their respective
    classes. Depending on the type of move, the game will act accordingly.

    The observer pattern is also visible here. What happens is whenever a moved is made, that signals a state change.
     This will notify its observers. In our case, it is going to be the board class as shown in the ArrayList
     addition in our driver class. The observer to alert the board class to display. In essence, we are using the
     observer pattern to display whenever a move is made.
     */


    //observer instantiation, board access, and strategy selector
    private List<Observer> observers = new ArrayList<>();
    private static final int BOARD_SIZE = 8;
    private Board board;
    private MoveStrategy currentStrategy;

    public Move(Board board) {
        this.board = board;
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    //ensuring a coordinate is in bounds
    private boolean inBounds(int x, int y) {
        return x >= 0 && x < BOARD_SIZE && y >= 0 && y < BOARD_SIZE;
    }

    //promoting a piece to king
    private void promoteToKing(int team, int x, int y) {
        if ((team == 1 && y == BOARD_SIZE - 1) || (team == 2 && y == 0)) {
            board.getBoard()[y][x].makeKing(true);
        }
    }

    //depending on strategy, child class will be selected to perform a move
    private MoveStrategy getStrategy(int fromX, int fromY, int toX, int toY, int team) {
        // Determine the appropriate strategy
        if (Math.abs(toX - fromX) == 2) {
            return new CaptureMoveStrategy();
        } else {
            return new StandardMoveStrategy();
        }
    }

    //performing the actual move
    public boolean move(int team) {
        notifyObservers();    //notifying observers that a move is about to be made
        int fromX, fromY, toX, toY;
        boolean isKing;

        System.out.println("Player " + team + "'s move");

        // we use a loop in case there's a capture. User can capture multiple pieces if aligned correctly
        while (true) {
            fromX = getCoordinate("Enter horizontal coordinate of piece: ");
            fromY = getCoordinate("Enter vertical coordinate of piece: ");

            if (!inBounds(fromX, fromY) || board.getBoard()[fromY][fromX].getTeam() != team) {
                System.out.println("Invalid piece, please pick again");
                continue;
            }

            isKing = board.getBoard()[fromY][fromX].kingStatus();
            toX = getCoordinate("Enter horizontal coordinate of empty space to move to: ");
            toY = getCoordinate("Enter vertical coordinate of empty space to move to: ");

            if (!inBounds(toX, toY) || board.getBoard()[toY][toX].getTeam() != 0) {
                System.out.println("Invalid space, please pick again");
                continue;
            }

            currentStrategy = getStrategy(fromX, fromY, toX, toY, team);
            if (currentStrategy.isValidMove(fromX, fromY, toX, toY, team, isKing, board)) {
                currentStrategy.executeMove(fromX, fromY, toX, toY, board);
                promoteToKing(team, toX, toY);
                if (!currentStrategy.shouldContinue(fromX, fromY, toX, toY, team, board)) {
                    return false; // No more moves possible
                }
                return true; // Move was successful
            }

            System.out.println("Invalid move, please try again");
        }
    }

    //receiving coordintes from user
    private int getCoordinate(String prompt) {
        //coordinate validation
        Scanner input = new Scanner(System.in);
        int coordinate = -1;
        while (true) {
            System.out.print(prompt);
            try {
                coordinate = input.nextInt();
                if (coordinate < 0 || coordinate >= BOARD_SIZE) {
                    System.out.println("Coordinate out of bounds. Please enter a value between 0 and " + (BOARD_SIZE - 1));
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                input.next(); // Clear the invalid input
            }
        }
        return coordinate;
    }

    //for use when capturing multiple pieces
    public void killAgain(int team) {
        //used for multi-kill
        Scanner input = new Scanner(System.in);
        System.out.print("If possible, kill again? (y/n): ");
        if (input.next().equalsIgnoreCase("y")) {
            move(team);
        }
    }
}

