import java.util.PriorityQueue;

/**
 * Created by deniz on 15/03/17.
 */
public class Solver
{
    public static void main( String[] args)
    {
        EightPuzzle goal = new EightPuzzle();
        boolean solved = false;
        PriorityQueue<EightPuzzle> q = new PriorityQueue<>(new HammingComparator());

        EightPuzzle initial = new EightPuzzle();
        initial.garble(150);

        System.out.println( initial);

        EightPuzzle cur;
        EightPuzzle prev = null;
        EightPuzzle[] possibleMoves;

        q.add(initial);
        int cnt = 0;

        //for (int z = 0; z < 20 && !solved; z++)
        while ( !solved)
        {
            cur = q.remove();
            possibleMoves = getPossibleMoves(cur);

            for (int i = 0; i < 4; i++)
            {
                if (possibleMoves[i] != null && !possibleMoves[i].equals( prev))
                    q.add(possibleMoves[i]);
            }

            if (cur.equals(goal))
            {
                solved = true;
            }

            prev = cur;
            cnt++;
        }

        System.out.println( cnt);

/*/
        EightPuzzle initial = new EightPuzzle();
        initial.garble( 1000);

        EightPuzzle[] possibleMoves = getPossibleMoves( initial);

        System.out.println( initial);
        System.out.println( initial.computeHamming());
        System.out.println();

        for ( int i = 0; i < 4; i++)
        {
            if ( possibleMoves[i] != null)
                System.out.println( possibleMoves[i] + " " + possibleMoves[i].computeHamming());
        }*/
    }

    public static EightPuzzle[] getPossibleMoves( EightPuzzle puz)
    {
        EightPuzzle[] result = new EightPuzzle[4];
        EightPuzzle copy = new EightPuzzle( puz);

        if ( copy.makeMove( EightPuzzle.MOVE.RIGHT))
        {
            copy.incrementMovesSoFar();
            result[0] = copy;
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.DOWN))
        {
            copy.incrementMovesSoFar();
            result[1] = copy;
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.LEFT))
        {
            copy.incrementMovesSoFar();
            result[2] = copy;
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.UP))
        {
            copy.incrementMovesSoFar();
            result[3] = copy;
        }

        return result;
    }
}
