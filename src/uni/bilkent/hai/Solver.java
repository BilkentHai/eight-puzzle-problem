package uni.bilkent.hai;

import java.util.*;

/**
 * Created by deniz on 15/03/17.
 *
 * Randomly generates and solves 8 puzzles.
 * Saves move count and closed set size for the last puzzle solved.
 * Does not mutate its inputs.
 */
public class Solver
{
    private PriorityQueue<EightPuzzle> openSet;
    private HashSet<EightPuzzle> closedSet;
    private int lastMoveCount;
    private int lastClosedSetSize;

    public Solver(Comparator<EightPuzzle> comp)
    {
        openSet = new PriorityQueue<>( comp);
        closedSet = new HashSet<>();
        lastMoveCount = -1;
        lastClosedSetSize = -1;
    }

    public int getLastMoveCount() { return lastMoveCount; }

    public int getLastClosedSetSize() { return lastClosedSetSize; }

    public EightPuzzle solve( EightPuzzle puz)
    {
        openSet.clear();
        closedSet.clear();
        openSet.add( puz);

        EightPuzzle cur = null;
        ArrayList<EightPuzzle> possibleMoves;

        while ( openSet.size() > 0)
        {
            cur = openSet.remove();

            if (cur.isGoalState())
                break;

            closedSet.add( cur);
            possibleMoves = getPossibleMoves(cur);

            for ( EightPuzzle e : possibleMoves)
            {
                if ( !closedSet.contains( e))
                    openSet.add( e);
            }
        }

        lastClosedSetSize = closedSet.size();
        lastMoveCount = cur.getMovesSoFar();

        return cur;
    }

    private ArrayList<EightPuzzle> getPossibleMoves(EightPuzzle puz)
    {
        ArrayList<EightPuzzle> result = new ArrayList<>(4);
        EightPuzzle copy = new EightPuzzle( puz);

        if ( copy.makeMove( EightPuzzle.MOVE.RIGHT))
        {
            result.add( copy);
            copy.setPrev( puz);
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.DOWN))
        {
            result.add( copy);
            copy.setPrev( puz);
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.LEFT))
        {
            result.add( copy);
            copy.setPrev( puz);
            copy = new EightPuzzle( puz);
        }

        if ( copy.makeMove( EightPuzzle.MOVE.UP))
        {
            copy.setPrev( puz);
            result.add( copy);
        }

        return result;
    }

    public static Stack<EightPuzzle> getSolutionSteps( EightPuzzle end)
    {
        Stack<EightPuzzle> stack = new Stack<>();

        stack.push( end);

        while ( stack.peek().getPrev() != null)
            stack.push( stack.peek().getPrev());

        return stack;
    }
}
