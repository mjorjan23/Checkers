public class xPieces implements Pieces
{
    //1st player class implementing all the method from the interface class

    private int xCor;
    private int yCor;
    private boolean king;
    private int team;

    public xPieces( int x, int y)
    {
        xCor = x;
        yCor = y;
        king = false;
        team = 1;
    }

    public int getX()
    {
        return xCor;
    }

    public int getY()
    {
        return yCor;
    }

    public boolean kingStatus()
    {
        return king;
    }

    public int getTeam(){
        return team;
    }
    public void setX(int x)
    {
        xCor = x;
    }

    public void setY(int y)
    {
        yCor = y;
    }

    public void makeKing(boolean k)
    {
        king = k;
    }
    public void setTeam(int t){
        team = t;
    }
}
