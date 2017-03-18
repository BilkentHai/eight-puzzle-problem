package uni.bilkent.hai;

import java.util.*;

/**
 * Created by deniz on 15/03/17.
 *
 * Generates a set of puzzles in random states.
 */
public class PuzzleGenerator
{
    public static ArrayList<EightPuzzle> generate( int numOfPuzzles, int garbleAmount)
    {
        // Need a set to ensure distinct states
        Set<EightPuzzle> puzzles = new HashSet<>();
        EightPuzzle puz;

        while ( puzzles.size() < numOfPuzzles)
        {
            puz = new EightPuzzle();
            puz.garble( garbleAmount);
            puzzles.add( puz);
        }

        ArrayList<EightPuzzle> copyList = new ArrayList<>();
        copyList.addAll( puzzles);

        return copyList;
    }
}
