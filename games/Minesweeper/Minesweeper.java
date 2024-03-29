//Author: Megan Rozal
package games.Minesweeper;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Timer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * Main Driver for Minesweeper Game
 * @author Megan Rozal
 * @version 1.0
 */

public class Minesweeper
{
    /**
     * Creates menu bar, frame, and container which gives the player the option to select difficulty level and board size. Also adds titles and restart time.
     * @throws IOException 
     */
	public void startMinesweeper() throws IOException
	{
		JFrame frame = new JFrame();
		JPanel container = new JPanel();
		JPanel title = new JPanel();
		JPanel panel = new JPanel();
		JLabel bombsLeft = new JLabel("", JLabel.CENTER);
		JLabel time = new JLabel("0", JLabel.CENTER);
		JButton restart = new JButton("Restart Game");
		restart.addActionListener(new RestartGame(restart,panel,frame,bombsLeft,time));
		
		// Default 
		Settings.nbRow = 15;
		Settings.nbCol = 15;
		Settings.nbBombs = 30;
		Settings.frameSize = 510;
		Settings.flagCount = 0;
		Settings.level = 2;
		
		JMenuBar menuBar = new JMenuBar();
		JMenu size = new JMenu("Board Size");
		JMenu difficulty = new JMenu("Difficulty");
		
		JMenuItem easy = new JMenuItem("Easy");
		JMenuItem medium = new JMenuItem("Medium");
		JMenuItem hard = new JMenuItem("Hard");
		
		JMenuItem small = new JMenuItem("10x10");
		JMenuItem med = new JMenuItem("15x15");
		JMenuItem big = new JMenuItem("20x20");
		
		easy.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		medium.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		hard.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		
		small.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		med.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		big.addActionListener(new MenuAction(frame,panel,bombsLeft,time));
		
		frame.setLayout(new BorderLayout());
		container.setLayout(new BorderLayout());
		title.setLayout(new GridLayout(1,3));
		frame.setBackground(Color.LIGHT_GRAY);
		
		difficulty.add(easy);
		difficulty.add(medium);
		difficulty.add(hard);
		
		size.add(small);
		size.add(med);
		size.add(big);
		
		menuBar.add(size);
		menuBar.add(difficulty);
		
		title.add(bombsLeft);
		title.add(restart);
		title.add(time);
		
		Timer timer = new Timer();
		timer.schedule(new Counter(time), 1000, 1000);
		
		Grid grille = new Grid(Settings.nbRow, Settings.nbCol, Settings.nbBombs, bombsLeft);
		
		grille.constructPanel(panel);
		
		container.add(title, BorderLayout.NORTH);
		container.add(panel, BorderLayout.CENTER);       
                
	    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
	    frame.setLocation((dim.width-Settings.frameSize)/2, (dim.height-Settings.frameSize)/2);
		frame.setSize(Settings.frameSize, Settings.frameSize);
		frame.setResizable(false);
		frame.add(menuBar, BorderLayout.NORTH);
		frame.add(container, BorderLayout.CENTER);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
	}
 
}

class updateBoard implements ActionListener
{
    /**
     * Updates board's panel, row and column
     */
	private JPanel panel;
	private JTextField fieldRow;
	private JTextField fieldCol;
	
	public updateBoard(JPanel panel, JTextField fieldRow, JTextField fieldCol)
	{
            /**
             * Updates board per field with a number
             */
		this.panel = panel;
		this.fieldRow = fieldRow;
		this.fieldCol = fieldCol;
	}
	public void actionPerformed(ActionEvent e)
	{
            /**
             * Removes all items from board
             */
		panel.removeAll();
		int nbRow = Integer.valueOf(fieldRow.getText());
		int nbCol = Integer.valueOf(fieldCol.getText());
		panel.setLayout(new GridLayout(nbRow, nbCol));
		
		for(int i=0; i < nbRow; i++)
		{
			for(int j=0; j < nbCol; j++)
			{
				panel.add(new JButton((nbCol*i+j)+""));
			}
		}
		panel.revalidate();
	}
}