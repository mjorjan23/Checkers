# Checkers — Final Project

**Michael Jan**  
**CS 5278 — Principles of Software Engineering**  
**Vanderbilt University**

## Introduction

For my final project, I developed a Checkers game using four object-oriented design patterns. I chose Checkers because it is similar to, but more advanced than, the programming assignments I typically give my AP Computer Science students. My students often create games such as tic-tac-toe, blackjack, and Connect 4. Checkers introduces more complex game rules and software design challenges, making it a project I would like to share with my students as an example of more advanced programming.

## About the Game

Checkers is a **two-player, console-based game** in which players enter the x- and y-coordinates of a piece and its destination. The program determines whether the requested move is legal and whether it is a standard move or a capture.

The game supports:

- Two players taking alternating turns
- Coordinate-based movement and move validation
- Board boundary checks
- Standard pieces that move forward and kings that can move in both directions
- Captures and multiple captures in a single turn
- Promotion to king when a piece reaches the opposite side of the board
- Win detection when one player has no remaining pieces

## Design Patterns

The application uses four design patterns to organize game state, piece creation, movement, and board display.

### 1. Singleton — Board Ownership

The **Singleton** pattern ensures that the application uses one shared game board. Once the board is created and populated with pieces, the game operates on that same board instance. The `Board` class controls its own construction and provides access to the shared instance.

I chose Singleton to prevent accidental creation of separate boards and to make it easy for different parts of the application to access the same game state. An alternative would be to create a shared `Board` object and pass it between classes. The trade-off is that Singleton makes it harder to support multiple simultaneous games, since each game would need its own board.

### 2. Observer — Board Updates

The **Observer** pattern is used to update the display after a move. The `Move` class acts as the subject, while the `Board` class is an observer. After a move changes the game state, the subject notifies its observers, allowing the board to display its updated state.

I chose Observer to separate move execution from the actions that follow a move. This also makes future additions possible, such as a score tracker or move-history tracker that responds to move notifications. A simpler alternative would be to call the board's display method directly, but Observer reduces that direct dependency at the cost of additional notification logic.

### 3. Strategy — Move Behavior

The **Strategy** pattern separates the two types of movement: standard moves and capture moves. The application selects the appropriate movement strategy based on the requested action. Each strategy handles the rules associated with its type of move.

I chose Strategy instead of putting all movement rules into one large class with conditional statements. Separating the implementations makes the code easier to read, debug, and modify. It also reduces coupling between movement behavior and the code that invokes it. The trade-off is the additional interfaces and classes required to support separate strategies.

### 4. Factory — Piece Creation

The **Factory** pattern centralizes the creation of first-player pieces, second-player pieces, and empty slots. Client code requests a piece using the player designation and coordinates without needing to know the details of each concrete piece class.

I chose Factory to keep piece construction in one place and hide its implementation from the code that uses it. This makes piece creation easier to maintain and change. An alternative would be to instantiate pieces directly wherever they are needed. That approach would require fewer classes, but construction logic would be spread throughout the application.

## Implementation — Putting Everything Together

The application is organized around four main responsibilities:

1. **Game control (`Checkers.java`):** Starts the game, obtains the shared board, populates it with pieces, and manages player turns.
2. **Board management (`Board`):** Stores the board state and displays the current arrangement of pieces. It also responds to observer notifications.
3. **Move processing (`Move`):** Validates requested moves, checks piece types and board boundaries, applies the appropriate movement strategy, and handles king promotion.
4. **Piece creation (`PieceFactory`):** Creates the appropriate player pieces or empty slots when the board is initialized.

At the beginning of the game, `Checkers.java` obtains the board through the Singleton implementation and uses the Factory to populate it. During each turn, the player enters starting and ending coordinates. The move-processing logic validates the request and uses the appropriate Strategy implementation to perform a standard move or capture.

After a successful move, the Observer mechanism notifies the board to display its updated state. This process repeats until a player has no remaining pieces and the game ends.
