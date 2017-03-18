package uni.bilkent.hai;

import java.util.Comparator;

/**
 * Created by deniz on 16/03/17.
 *
 * Manhattan distance function for priority queue
 */
public class ManhattanComparator implements Comparator<EightPuzzle>
{
    public int compare(EightPuzzle x, EightPuzzle y)
    {
        return computeManhattan( x) - computeManhattan( y);
    }

    public static int computeManhattan( EightPuzzle puz)
    {
        int totalDistance = 0;

        for ( int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {
                if ( puz.getTiles()[i][j] == 9)
                    continue;

                int[] goalPos = findGoalPosition( puz.getTiles()[i][j]);
                totalDistance += Math.abs( i - goalPos[0]) + Math.abs( j - goalPos[1]);
            }
        }

        return totalDistance + puz.getMovesSoFar();
    }

    private static int[] findGoalPosition( int tile)
    {
        int[] result = new int[2];
        tile--;
        result[0] = tile / 3;
        result[1] = tile % 3;
        return result;
    }
}
