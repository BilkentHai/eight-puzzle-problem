import java.util.ArrayList;

/**
 * Created by deniz on 18/03/17.
 */
public class Driver
{
    public static void main( String[] a)
    {
        ArrayList<EightPuzzle> list = PuzzleGenerator.generate( 300, 300);
        Solver hamSolver = new Solver( new HammingComparator());
        Solver manSolver = new Solver( new ManhattanComparator());

        for ( EightPuzzle p : list)
        {
            hamSolver.solve( p);
            System.out.println( hamSolver.getLastClosedSetSize() + " " + hamSolver.getLastMoveCount());
            manSolver.solve( p);
            System.out.println( manSolver.getLastClosedSetSize() + " " + manSolver.getLastMoveCount());
        }
    }
}
