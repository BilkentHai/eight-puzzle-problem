import java.util.ArrayList;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * Created by deniz on 15/03/17.
 */
public class Solver
{
    public static void main( String[] args)
    {
        boolean solved = false;
        PriorityQueue<EightPuzzle> openSet = new PriorityQueue<>(new HammingComparator());
        HashSet<EightPuzzle> closedSet = new HashSet<>();

        EightPuzzle initial = new EightPuzzle();
        initial.garble(1000);

//        System.out.println( initial);

        EightPuzzle cur;
        ArrayList<EightPuzzle> possibleMoves;

        openSet.add(initial);

        while ( openSet.size() > 0)
        {
            cur = openSet.remove();

            if (cur.isGoalState())
            {
                System.out.println( closedSet.size());
                printSolution( cur);
                break;
            }

            closedSet.add( cur);
            possibleMoves = getPossibleMoves(cur);

            for ( EightPuzzle e : possibleMoves)
            {
                if ( !closedSet.contains( e))
                    openSet.add( e);
            }
        }
    }

    public static ArrayList<EightPuzzle> getPossibleMoves(EightPuzzle puz)
    {
        ArrayList<EightPuzzle> result = new ArrayList<>(4);
        EightPuzzle copy = new EightPuzzle( puz);

        if ( copy.makeMove( EightPuzzle.MOVE.RIGHT))
        {
            result.add( copy);
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.DOWN))
        {
            result.add( copy);
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.LEFT))
        {
            result.add( copy);
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.UP))
        {
            result.add( copy);
        }

        return result;
    }

    public static void printSolution( EightPuzzle end)
    {
        Stack<EightPuzzle> stack = new Stack<>();
        stack.push( end);

        while ( stack.peek().getPrev() != null)
            stack.push( stack.peek().getPrev());

        System.out.println( "Solved in " + ( stack.size() - 1) + " moves\n");

        while ( !stack.isEmpty())
            System.out.println( stack.pop());
    }
}
