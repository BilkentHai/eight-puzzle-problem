import java.awt.*;

/**
 * Created by deniz on 15/03/17.
 */
public class EightPuzzle
{
    int[][] puzzle;
    int[] emptyTile;

    public enum MOVE { RIGHT, DOWN, LEFT, UP }

    public EightPuzzle()
    {
        puzzle = new int[3][3];

        int count = 1;
        for ( int i = 0; i < 3; i++)
            for ( int j = 0; j < 3; j++)
                puzzle[i][j] = count++;

        // empty tile represented by 9 on puzzle
        // [0] holds y value, [1] holds x value
        emptyTile = new int[] { 2, 2 };
    }


    // copy constructor not tested
    public EightPuzzle( EightPuzzle toCopy)
    {
        this.puzzle = new int[3][3];

        for ( int i = 0; i < 3; i++)
            for ( int j = 0; j < 3; j++)
                this.puzzle[i][j] = toCopy.puzzle[i][j];

        this.emptyTile = new int[2];
        this.emptyTile[0] = toCopy.emptyTile[0];
        this.emptyTile[1] = toCopy.emptyTile[1];
    }

    @Override
    public String toString()
    {
        String row0 = "[" + puzzle[0][0] + " " + puzzle[0][1] + " " + puzzle[0][2] + "]";
        String row1 = "[" + puzzle[1][0] + " " + puzzle[1][1] + " " + puzzle[1][2] + "]";
        String row2 = "[" + puzzle[2][0] + " " + puzzle[2][1] + " " + puzzle[2][2] + "]";

        return row0 + "\n" + row1 + "\n" + row2 + "\n";
    }

    boolean makeMove( MOVE move)
    {
        if ( move == MOVE.RIGHT)
        {
            if ( emptyTile[1] < 2)
            {
                puzzle[emptyTile[0]][emptyTile[1]] = puzzle[emptyTile[0]][emptyTile[1] + 1];
                emptyTile[1] = emptyTile[1] + 1;
                puzzle[emptyTile[0]][emptyTile[1]] = 9;
                return true;
            }
            else
                return false;
        }
        else if ( move == MOVE.LEFT)
        {
            if ( emptyTile[1] > 0)
            {
                puzzle[emptyTile[0]][emptyTile[1]] = puzzle[emptyTile[0]][emptyTile[1] - 1];
                emptyTile[1] = emptyTile[1] - 1;
                puzzle[emptyTile[0]][emptyTile[1]] = 9;
                return true;
            }
            else
                return false;
        }
        else if ( move == MOVE.DOWN)
        {
            if ( emptyTile[0] < 2)
            {
                puzzle[emptyTile[0]][emptyTile[1]] = puzzle[emptyTile[0] + 1][emptyTile[1]];
                emptyTile[0] = emptyTile[0] + 1;
                puzzle[emptyTile[0]][emptyTile[1]] = 9;
                return true;
            }
            else
                return false;
        }
        else if ( move == MOVE.UP)
        {
            if ( emptyTile[0] > 0)
            {
                puzzle[emptyTile[0]][emptyTile[1]] = puzzle[emptyTile[0] - 1][emptyTile[1]];
                emptyTile[0] = emptyTile[0] - 1;
                puzzle[emptyTile[0]][emptyTile[1]] = 9;
                return true;
            }
            else
                return false;
        }

        return false;
    }

    int[] getEmptyTile() { return emptyTile; }

    int getTileContent( int y, int x)
    {
        if ( x >= 0 && x <= 2 && y >= 0 && y <= 2)
            return puzzle[y][x];
        else
            return -1;
    }

    public static void main( String[] args)
    {
        EightPuzzle puz = new EightPuzzle();
        System.out.println( puz);
    }
}
