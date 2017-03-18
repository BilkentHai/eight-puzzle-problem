package uni.bilkent.hai.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;
import uni.bilkent.hai.EightPuzzle;

/**
 * Created by deniz on 15/03/17.
 *
 * GUI to test 8 puzzles.
 */
public class GridPanel extends JPanel
{
    private static final int ROWS = 3;
    private static final int COLS = 3;
    private static final int CELL_SIDE = 30;
    private static final int PANEL_WIDTH = CELL_SIDE * COLS;
    private static final int PANEL_HEIGHT = CELL_SIDE * ROWS;
    private static final int X_OFFSET = 12;
    private static final int Y_OFFSET = 20;
    private static final int NUM_OF_CELLS = ROWS * COLS;

    private EightPuzzle puzzle;

    public GridPanel( EightPuzzle puz)
    {
        puzzle = puz;

        setPreferredSize( new Dimension( PANEL_WIDTH + 1, PANEL_HEIGHT + 1));
        //this.setFocusable( true);

        setBackground( new Color(250, 255, 134));
        setLayout( new BorderLayout());
        setBorder( new LineBorder( Color.BLACK));
    }

    public EightPuzzle getPuzzle() { return puzzle; }

    public void setPuzzle(EightPuzzle puzzle)
    {
        this.puzzle = puzzle;
        repaint();
    }

    @Override
    protected void paintComponent( Graphics g)
    {
        super.paintComponent( g);

        /* Draw panel borders
        g.drawLine( 0, 0, 0, PANEL_HEIGHT);
        g.drawLine( 0, 0, PANEL_WIDTH, 0);
        g.drawLine( PANEL_WIDTH, 0, PANEL_WIDTH, PANEL_HEIGHT);
        g.drawLine( 0, PANEL_HEIGHT, PANEL_WIDTH, PANEL_HEIGHT);
        */

        // Draw cell borders
        for ( int i = CELL_SIDE; i < PANEL_WIDTH; i += CELL_SIDE)
            g.drawLine( i, 0, i, PANEL_HEIGHT);

        for ( int i = CELL_SIDE; i < PANEL_HEIGHT; i += CELL_SIDE)
            g.drawLine( 0, i, PANEL_WIDTH, i);

        // Draw cell contents
        if ( puzzle != null)
        {
            for (int i = 0; i < ROWS; i++)
            {
                for (int j = 0; j < COLS; j++)
                {
                    if (!(puzzle.getEmptyTile()[0] == i && puzzle.getEmptyTile()[1] == j))
                        g.drawString(puzzle.getTileContent(i, j) + "", j * CELL_SIDE + X_OFFSET, i * CELL_SIDE + Y_OFFSET);
                }
            }
        }
    }

    class KbListener extends KeyAdapter
    {
        @Override
        public void keyPressed( KeyEvent e)
        {
            if ( e.getKeyCode() == KeyEvent.VK_DOWN) { puzzle.makeMove( EightPuzzle.MOVE.UP); }
            else if ( e.getKeyCode() == KeyEvent.VK_RIGHT) { puzzle.makeMove( EightPuzzle.MOVE.LEFT); }
            else if ( e.getKeyCode() == KeyEvent.VK_LEFT) { puzzle.makeMove( EightPuzzle.MOVE.RIGHT); }
            else if ( e.getKeyCode() == KeyEvent.VK_UP) { puzzle.makeMove( EightPuzzle.MOVE.DOWN); }

            repaint();
        }
    }

}

