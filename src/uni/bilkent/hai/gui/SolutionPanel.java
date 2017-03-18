package uni.bilkent.hai.gui;

import uni.bilkent.hai.EightPuzzle;
import uni.bilkent.hai.ManhattanComparator;
import uni.bilkent.hai.Solver;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Stack;

/**
 * Created by deniz on 18/03/17.
 */
public class SolutionPanel extends JPanel
{
    Stack<EightPuzzle> prevStack, nextStack;
    Solver solver;

    JButton genButton, prevButton, nextButton;
    JLabel stepLabel;
    JPanel bottomPanel, bottomBotPanel;
    GridPanel puzPanel;

    public SolutionPanel()
    {
        solver = new Solver( new ManhattanComparator());

        setLayout( new BorderLayout());
        puzPanel = new GridPanel( null);
        genButton = new JButton( "Generate");

        genButton.addActionListener( new generateListener());

        prevButton = new JButton( "Previous");
        prevButton.addActionListener( new PrevListener());
        prevButton.setEnabled( false);

        nextButton = new JButton( "Next");
        nextButton.addActionListener( new NextListener());
        nextButton.setEnabled( false);

        bottomPanel = new JPanel();
        bottomPanel.setLayout( new BorderLayout());

        bottomBotPanel = new JPanel();
        bottomPanel.add( bottomBotPanel, BorderLayout.SOUTH);

        stepLabel = new JLabel( "Solution step: 0/0", JLabel.CENTER);
        bottomPanel.add( stepLabel, BorderLayout.NORTH);

        bottomBotPanel.add( prevButton);
        bottomBotPanel.add( nextButton);

        add( puzPanel, BorderLayout.WEST);
        add( genButton, BorderLayout.EAST);
        add( bottomPanel, BorderLayout.SOUTH);

        setBorder( new EmptyBorder( 3, 3, 3, 3));
    }

    private void updateStepLabel()
    {
        String text = "Solution step: ";
        text += prevStack.size() + "/" + ( prevStack.size() + nextStack.size());
        stepLabel.setText( text);
    }

    private class generateListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            EightPuzzle puz = new EightPuzzle();
            puz.garble( 1000);
            puz = solver.solve( puz);

            nextStack = Solver.getSolutionSteps( puz);
            prevStack = new Stack<>();

            puzPanel.setPuzzle( nextStack.pop());

            if ( !nextStack.isEmpty())
                nextButton.setEnabled( true);
            else
                nextButton.setEnabled( false);

            prevButton.setEnabled( false);

            updateStepLabel();
        }
    }

    private class NextListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            prevStack.push( puzPanel.getPuzzle());
            puzPanel.setPuzzle( nextStack.pop());

            if ( nextStack.isEmpty())
                nextButton.setEnabled( false);

            prevButton.setEnabled( true);

            updateStepLabel();
        }
    }

    private class PrevListener implements ActionListener
    {
        @Override
        public void actionPerformed( ActionEvent e)
        {
            nextStack.push( puzPanel.getPuzzle());
            puzPanel.setPuzzle( prevStack.pop());

            if ( prevStack.isEmpty())
                prevButton.setEnabled( false);

            nextButton.setEnabled( true);

            updateStepLabel();
        }
    }
}
