//Author: Megan Rozal
package games.Minesweeper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Class that contains the functionalities of the menu bar
 * @author Megan Rozal
 * @version 1.0
 */
public class MenuAction implements ActionListener
{
    /**
     * Creates labels and panels for time, bombs left, and the overall frame
     */
	private JLabel bombsLeft;
	private JPanel panel;
	private JLabel time;
	private JFrame frame;
	
	public MenuAction(JFrame frame, JPanel panel, JLabel bombsLeft, JLabel time)
	{
            /**
             * Creates references to the below objects
             */
		this.bombsLeft = bombsLeft;
		this.panel = panel;
		this.time = time;
		this.frame = frame;
	}
	
	public void actionPerformed(ActionEvent e)
	{
            /**
             * Creates board size and dimensions according to the number of cells
             * Creates the level settings to easy, medium, or hard
             * Creates a bomb counter that counts how many bombs are left on the board
             * Sets the time counter to 0
             */

		if(e.getActionCommand() == "10x10")
		{
			Settings.nbRow = 10;
			Settings.nbCol = 10;
			Settings.frameSize = 390;
		}
		else if(e.getActionCommand() == "15x15")
		{
			Settings.nbRow = 15;
			Settings.nbCol = 15;
			Settings.frameSize = 510;
		}
		else if(e.getActionCommand() == "20x20")
		{
			Settings.nbRow = 20;
			Settings.nbCol = 20;
			Settings.frameSize = 700;
		}
		else if(e.getActionCommand() == "Easy")
		{
			Settings.level = 1;
		}
		else if(e.getActionCommand() == "Medium")
		{
			Settings.level = 2;
		}
		else if(e.getActionCommand() == "Hard")
		{
			Settings.level = 3;
		}
		
		Settings.nbBombs = Settings.level * (Settings.nbRow+Settings.nbCol)/2;
		
		Grid grille = new Grid(Settings.nbRow, Settings.nbCol, Settings.nbBombs, bombsLeft);
		grille.constructPanel(panel);
		Settings.flagCount = 0;
		bombsLeft.setText(""+(Settings.nbBombs - Settings.flagCount));
		time.setText("0");
		Settings.hasStarted = false;
		
		frame.setSize(Settings.frameSize, Settings.frameSize);
	}

}