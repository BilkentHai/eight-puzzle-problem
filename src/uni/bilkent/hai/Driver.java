package uni.bilkent.hai;

/**
 * Created by deniz on 18/03/17.
 */
public class Driver
{
    public static void main( String[] a)
    {
        EightPuzzle puz = new EightPuzzle();
        // modify garble parameter to get more complex puzzles
        puz.garble( 100);

        System.out.println( "Solving using Hamming Distance...");
        Solver solver = new Solver( new HammingComparator());
        solver.printSolve( puz);

        System.out.println();
        System.out.println( "Solving using Manhattan Distance...");
        solver = new Solver( new ManhattanComparator());
        solver.printSolve( puz);
    }
}
