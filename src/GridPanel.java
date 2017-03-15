import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by deniz on 15/03/17.
 */
public class GridPanel extends JPanel
{
    static final int ROWS = 3;
    static final int COLS = 3;
    static final int CELL_SIDE = 30;
    static final int PANEL_WIDTH = CELL_SIDE * COLS;
    static final int PANEL_HEIGHT = CELL_SIDE * ROWS;
    static final int X_OFFSET = 12;
    static final int Y_OFFSET = 20;
    static final int NUM_OF_CELLS = ROWS * COLS;

    EightPuzzle puzzle;

    public GridPanel()
    {
        puzzle = new EightPuzzle();

        setPreferredSize( new Dimension( PANEL_WIDTH + 1, PANEL_HEIGHT + 1));
        this.addKeyListener( new MyListener());
        this.setFocusable( true);
    }

    @Override
    protected void paintComponent( Graphics g)
    {
        super.paintComponent( g);

        // Draw panel borders
        g.drawLine( 0, 0, 0, PANEL_HEIGHT);
        g.drawLine( 0, 0, PANEL_WIDTH, 0);
        g.drawLine( PANEL_WIDTH, 0, PANEL_WIDTH, PANEL_HEIGHT);
        g.drawLine( 0, PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT);

        // Draw cell borders
        for ( int i = CELL_SIDE; i < PANEL_WIDTH; i += CELL_SIDE)
            g.drawLine( i, 0, i, PANEL_HEIGHT);

        for ( int i = CELL_SIDE; i < PANEL_HEIGHT; i += CELL_SIDE)
            g.drawLine( 0, i, PANEL_WIDTH, i);

        // Draw cell contents
        for ( int i = 0; i < ROWS; i++)
        {
            for ( int j = 0; j < COLS; j++)
            {
                if ( !( puzzle.getEmptyTile()[0] == i && puzzle.getEmptyTile()[1] == j))
                    g.drawString( puzzle.getTileContent( i, j) + "", j * CELL_SIDE + X_OFFSET, i * CELL_SIDE + Y_OFFSET);
            }
        }
    }


    public static void main( String[] args)
    {
        JFrame frame = new JFrame();
        frame.setTitle( "8");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE);
        GridPanel panel = new GridPanel();
        frame.add( panel);
        frame.pack();
        frame.setResizable( false);
        frame.setVisible( true);
    }

    class MyListener extends KeyAdapter
    {
        @Override
        public void keyPressed( KeyEvent e)
        {
            if ( e.getKeyCode() == KeyEvent.VK_UP) { puzzle.makeMove( EightPuzzle.MOVE.UP); }
            else if ( e.getKeyCode() == KeyEvent.VK_LEFT) { puzzle.makeMove( EightPuzzle.MOVE.LEFT); }
            else if ( e.getKeyCode() == KeyEvent.VK_RIGHT) { puzzle.makeMove( EightPuzzle.MOVE.RIGHT); }
            else if ( e.getKeyCode() == KeyEvent.VK_DOWN) { puzzle.makeMove( EightPuzzle.MOVE.DOWN); }

            repaint();
        }
    }

}

