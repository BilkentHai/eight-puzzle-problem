import java.util.Comparator;

/**
 * Created by deniz on 16/03/17.
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

    static int[] findGoalPosition( int tile)
    {
        switch ( tile)
        {
            case 1: return new int[]{ 0, 0 };
            case 2: return new int[]{ 0, 1 };
            case 3: return new int[]{ 0, 2 };
            case 4: return new int[]{ 1, 0 };
            case 5: return new int[]{ 1, 1 };
            case 6: return new int[]{ 1, 2 };
            case 7: return new int[]{ 2, 0 };
            case 8: return new int[]{ 2, 1 };
            default: return null;
        }
    }

    public static void main( String[] args)
    {
        EightPuzzle e = new EightPuzzle();
        e.garble( 3);
        System.out.println( e);
        System.out.println( computeManhattan( e));
    }
}
