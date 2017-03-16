import java.util.*;

/**
 * Created by deniz on 15/03/17.
 *
 * Creates and maintains a set of puzzles in random states.
 */
public class PuzzleGenerator
{
    private static final int GARBLE_AMOUNT = 1000;

    private Set<EightPuzzle> puzzles;

    public PuzzleGenerator( int size)
    {
        puzzles = new HashSet<>();
        EightPuzzle puz;

        while ( puzzles.size() < size)
        {
            puz = new EightPuzzle();
            puz.garble( GARBLE_AMOUNT);
            puzzles.add( puz);
        }
    }

    public HashSet<EightPuzzle> getPuzzles()
    {
        HashSet<EightPuzzle> copy = new HashSet<EightPuzzle>();

        for ( EightPuzzle puz : puzzles)
            copy.add( new EightPuzzle( puz));

        return copy;
    }
}
