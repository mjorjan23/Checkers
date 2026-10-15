public interface Pieces
{
    //factory pattern used to create pieces(1st player, 2nd player, empty slot)

    //this is the parent interface for pieces. All pieces will have these functionalites.
    //there are going to be x pieces(first player) o pieces(second player) and empty pieces(empty slots on the board)
    /*
    We will be implementing the factory for the pieces class as shown in the PieceFactory class. This will allow the
    subclasses to choose the type to create. A piece can either be of 1st player or of 2nd player. Each player's
    pieces have their own specific rules. 1st player can only move down and 2nd player can only move up. This also
    makes is so when capturing a piece a player can identify which pieces can be captured and which pieces are of its
     own team.
     */

    //getters
    public int getX();

    public int getY();

    //checking to see if something is king or not
    public boolean kingStatus();

    //identifying if 1st player or 2nd player
    public int getTeam();

    //setters
    public void setX(int x);

    public void setY(int y);

    //changing from normal piece to king piece
    public void makeKing(boolean k);

    //changing someone's team(not used)
    public void setTeam(int t);
}
