public class emptySlot implements Pieces
{
    // the way my game is created, empty slots need to be instantiated as well
    private int xCor;
    private int yCor;
    private boolean king;
    private int team;

    public emptySlot()
    {
        xCor = -1;
        yCor = -1;
        king = false;
        team = 0;
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
