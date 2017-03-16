import java.util.Arrays;
import java.util.HashSet;

/**
 * Created by deniz on 15/03/17.
 *
 * Represents an 8 tiles.
 * Puzzles are generated in their goal state.
 */
public class EightPuzzle
{
    public static int[][] goalTiles = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

    private int[][] tiles;
    private int[] emptyTile;

    // to be used for state search
    private int movesSoFar;
    private  EightPuzzle prev;

    public enum MOVE
    { RIGHT, DOWN, LEFT, UP }

    public EightPuzzle()
    {
        tiles = new int[3][3];

        int count = 1;
        for ( int i = 0; i < 3; i++)
            for ( int j = 0; j < 3; j++)
                tiles[i][j] = count++;

        // empty tile represented by 9 on tiles
        // [0] holds y value, [1] holds x value
        emptyTile = new int[] { 2, 2 };
    }

    public EightPuzzle getPrev() { return prev; }

    // copy constructor not tested
    public EightPuzzle( EightPuzzle prev)
    {
        this.tiles = new int[3][3];

        for ( int i = 0; i < 3; i++)
            for ( int j = 0; j < 3; j++)
                this.tiles[i][j] = prev.tiles[i][j];

        this.emptyTile = new int[2];
        this.emptyTile[0] = prev.emptyTile[0];
        this.emptyTile[1] = prev.emptyTile[1];

        this.movesSoFar = prev.movesSoFar + 1;
        this.prev = prev;
    }

    int[] getEmptyTile() { return emptyTile; }

    public int[][] getTiles() { return tiles; }

    int getTileContent(int y, int x)
    {

        if ( x >= 0 && x <= 2 && y >= 0 && y <= 2)
            return tiles[y][x];
        else
            return -1;
    }

    public int getMovesSoFar() { return movesSoFar; }

    public boolean isGoalState()
    {
        return ( Arrays.deepEquals( this.tiles, goalTiles));
    }

    @Override
    public boolean equals( Object other)
    {
        if ( other == null) return false;
        if ( other == this) return true;
        if ( !( other instanceof EightPuzzle)) return false;

        EightPuzzle otherEP = ( EightPuzzle) other;

        return Arrays.deepEquals( this.tiles, otherEP.tiles);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode( tiles);
    }

    boolean makeMove( MOVE move)
    {
        if ( move == MOVE.RIGHT)
        {
            if ( emptyTile[1] < 2)
            {
                tiles[emptyTile[0]][emptyTile[1]] = tiles[emptyTile[0]][emptyTile[1] + 1];
                emptyTile[1] = emptyTile[1] + 1;
                tiles[emptyTile[0]][emptyTile[1]] = 9;
                movesSoFar++;
                return true;
            }
            else
                return false;
        }
        else if ( move == MOVE.LEFT)
        {
            if ( emptyTile[1] > 0)
            {
                tiles[emptyTile[0]][emptyTile[1]] = tiles[emptyTile[0]][emptyTile[1] - 1];
                emptyTile[1] = emptyTile[1] - 1;
                tiles[emptyTile[0]][emptyTile[1]] = 9;
                movesSoFar++;
                return true;
            }
            else
                return false;
        }
        else if ( move == MOVE.DOWN)
        {
            if ( emptyTile[0] < 2)
            {
                tiles[emptyTile[0]][emptyTile[1]] = tiles[emptyTile[0] + 1][emptyTile[1]];
                emptyTile[0] = emptyTile[0] + 1;
                tiles[emptyTile[0]][emptyTile[1]] = 9;
                movesSoFar++;
                return true;
            }
            else
                return false;
        }
        else if ( move == MOVE.UP)
        {
            if ( emptyTile[0] > 0)
            {
                tiles[emptyTile[0]][emptyTile[1]] = tiles[emptyTile[0] - 1][emptyTile[1]];
                emptyTile[0] = emptyTile[0] - 1;
                tiles[emptyTile[0]][emptyTile[1]] = 9;
                movesSoFar++;
                return true;
            }
            else
                return false;
        }

        return false;
    }

    void makeRandomMove()
    {
        int randomMove = (int) ( Math.random() * 4);

        if ( randomMove == 0)
            makeMove( EightPuzzle.MOVE.RIGHT);
        else if ( randomMove == 1)
            makeMove( EightPuzzle.MOVE.DOWN);
        else if ( randomMove == 2)
            makeMove( EightPuzzle.MOVE.LEFT);
        else
            makeMove( EightPuzzle.MOVE.UP);
    }

    // Moves so far is restored to zero after garbling
    public void garble( int moves)
    {
        for ( int i = 0; i < moves; i++)
            makeRandomMove();

        movesSoFar = 0;
    }

    @Override
    public String toString()
    {
        String row0 = "[" + tiles[0][0] + " " + tiles[0][1] + " " + tiles[0][2] + "]";
        String row1 = "[" + tiles[1][0] + " " + tiles[1][1] + " " + tiles[1][2] + "]";
        String row2 = "[" + tiles[2][0] + " " + tiles[2][1] + " " + tiles[2][2] + "]";

        String result = row0 + "\n" + row1 + "\n" + row2 + "\n";

        result = result.replace( '9', ' ');
        return result;
    }

    public static void main( String[] args)
    {
        EightPuzzle e1 = new EightPuzzle();
        EightPuzzle e2 = new EightPuzzle();

        e2.makeMove( MOVE.UP);
        e2.makeMove(MOVE.DOWN);

        System.out.println( e1.equals( e2));

        HashSet<EightPuzzle> set = new HashSet<EightPuzzle>();
        set.add( e1);
        set.add( e2);

        System.out.println( set.size());
    }
}
