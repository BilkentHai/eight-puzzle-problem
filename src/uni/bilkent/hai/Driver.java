package uni.bilkent.hai;

import java.util.ArrayList;

/**
 * Created by deniz on 18/03/17.
 */
public class Driver
{
    public static void main( String[] a)
    {
        ArrayList<EightPuzzle> puzList = PuzzleGenerator.generate( 100, 1000);
        Solver hamSolver = new Solver( new HammingComparator());
        Solver manSolver = new Solver( new ManhattanComparator());

        int i = 0;
        for ( EightPuzzle p : puzList)
        {
            System.out.println( "Puzzle " + ++i);
            System.out.println( p);
            hamSolver.solve( p);
            System.out.println( "Hamming closed set size: " + hamSolver.getLastClosedSetSize());
            System.out.println( "Hamming solution length: "  + hamSolver.getLastMoveCount());
            manSolver.solve( p);
            System.out.println( "Manhattan closed set size: " + manSolver.getLastClosedSetSize());
            System.out.println( "Manhattan solution length: "  + manSolver.getLastMoveCount());
            System.out.println();
        }
    }
}
