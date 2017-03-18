package uni.bilkent.hai.gui;

import javax.swing.*;

/**
 * Created by deniz on 18/03/17.
 */
public class GuiDemo
{
    public static void main( String[] args)
    {
        JFrame frame = new JFrame();
        frame.add( new SolutionPanel());
        frame.pack();
        frame.setTitle( "8 Puzzle Demo");
        frame.setResizable( false);
        frame.setLocationRelativeTo( null);
        frame.setDefaultCloseOperation( WindowConstants.EXIT_ON_CLOSE);

        frame.setVisible( true);

    }
}
