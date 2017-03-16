/**
 * Created by deniz on 15/03/17.
 *
 * Represents an 8 puzzle.
 * Puzzles are generated in their goal state.
 */
public class EightPuzzle
{
    private int[][] puzzle;
    private int[] emptyTile;

    // to be used to calculate heuristics
    private int movesSoFar;

    public enum MOVE
    { RIGHT, DOWN, LEFT, UP }

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

        movesSoFar = 0;
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

        this.movesSoFar = toCopy.movesSoFar;
    }

    int[] getEmptyTile() { return emptyTile; }

    public int[][] getPuzzle() { return puzzle; }

    int getTileContent(int y, int x)
    {

        if ( x >= 0 && x <= 2 && y >= 0 && y <= 2)
            return puzzle[y][x];
        else
            return -1;
    }

    public int getMovesSoFar() { return movesSoFar; }

    public void incrementMovesSoFar() { movesSoFar++; }

    // DOES NOT CHECK IF MOVES SO FAR ARE EQUAL
    public boolean equals( EightPuzzle other)
    {
        if ( other == null)
            return false;

        for ( int i = 0; i < 3; i++)
            for ( int j = 0; j < 3; j++)
            {
                if ( this.puzzle[i][j] != other.puzzle[i][j])
                    return false;
            }

        if ( this.emptyTile[0] != other.emptyTile[0])
            return false;

        if ( this.emptyTile[1] != other.emptyTile[1])
            return false;

        return true;
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
        String row0 = "[" + puzzle[0][0] + " " + puzzle[0][1] + " " + puzzle[0][2] + "]";
        String row1 = "[" + puzzle[1][0] + " " + puzzle[1][1] + " " + puzzle[1][2] + "]";
        String row2 = "[" + puzzle[2][0] + " " + puzzle[2][1] + " " + puzzle[2][2] + "]";

        String result = row0 + "\n" + row1 + "\n" + row2 + "\n";

        result = result.replace( '9', ' ');
        return result;
    }

    public static void main( String[] args)
    {
        EightPuzzle puz = new EightPuzzle();
        EightPuzzle puz2 = new EightPuzzle( puz);

        puz2.makeMove( MOVE.UP);

        System.out.println( puz.equals( puz2));
    }
}
