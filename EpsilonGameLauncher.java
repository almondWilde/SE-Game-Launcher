/**
	Project: Epsilon Game Launcher
	This program creates a window for the Epsilon Game Launcher,
	 the main menu, center gaming area, and top menu bar
	@author Armand Wilson
	Last Edit: 11/28/19
	11/27/19
	11/26/19
	10/24/19
**/
//access to games folder classes
import games.Bingo.Bingo;
import games.TicTacToe.TicTacToe;
import games.BlackJack.BlackJack;
import games.RPS.RPS;
import games.Minesweeper.Minesweeper;
import games.Hangman.Hangman;

//GUI
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;

//Images
import java.io.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
	Creates a window with buttons that will launcher the games:
	Hangman, TicTacToe, Black Jack, Rock Paper Scissors, Minesweeper,
	and Bingo
	@author Armand Wilson
**/
public class EpsilonGameLauncher extends JFrame
{
	/**
		GUI Components
	**/
	private JMenuBar menuBar;
	private JMenu NewGame_menu, Quit_menu, Help_menu;
	private JButton btnTicTacToe,btnBingo,btnHangman,btnBlackJack,btnRockPaperScissors,btnMinsweeper;
	private JPanel pMainMenu, pNorth;
	private Bingo bingoGame;
	private TicTacToe tttGame;
	private Hangman hangmanGame;
	/**
			Panels and frame that can be passed to other classes
	**/
	public JPanel pCenter;
	public JFrame mainWindow;

	/**
		Constructor for EpsilonGameLauncher. Builds and dispalys a window for game
		options.
		@author Armand Wilson
		@throws IOException
	**/
	public EpsilonGameLauncher() throws IOException
	{
		//creates the window
		mainWindow = new JFrame();
		menuBar = new JMenuBar();
		NewGame_menu = new JMenu("New Game");
		Quit_menu = new JMenu("Help");
		Help_menu = new JMenu("Quit");

		menuBar.add(NewGame_menu);
		menuBar.add(Quit_menu);
		menuBar.add(Help_menu);

		pMainMenu = new JPanel();
		pCenter = new JPanel();
		pNorth = new JPanel();

		//button intialization for each game
		btnTicTacToe = new JButton("TicTacToe");
		btnTicTacToe.setToolTipText("Play TicTacTie");
		btnBingo = new JButton("Bingo");
		btnBingo.setToolTipText("Play Bingo");
		btnHangman = new JButton ("Hangman");
		btnHangman.setToolTipText ("Play Hangman");
		btnBlackJack = new JButton("BlackJack");
		btnBlackJack.setToolTipText("Play BlackJack");
		btnRockPaperScissors = new JButton("RockPaperScissors");
		btnRockPaperScissors.setToolTipText("Play Rock, Paper, Scissors");
		btnMinsweeper = new JButton("Minsweeper");
		btnMinsweeper.setToolTipText("Play Minsweeper");

		btnBingo.addActionListener(new aButtonHandler());
		btnTicTacToe.addActionListener(new aButtonHandler());
		btnBlackJack.addActionListener(new aButtonHandler());
		btnMinsweeper.addActionListener(new aButtonHandler());
		btnRockPaperScissors.addActionListener(new aButtonHandler());
		btnHangman.addActionListener (new aButtonHandler ());

		//buttons are added to the main menu
		pMainMenu.add(btnTicTacToe);
		pMainMenu.add(btnBingo);
		pMainMenu.add (btnHangman);
		pMainMenu.add(btnBlackJack);
		pMainMenu.add(btnRockPaperScissors);
		pMainMenu.add(btnMinsweeper);

		//set mainMenu appearance
		pMainMenu.setBorder(BorderFactory.createTitledBorder("Games"));
		pMainMenu.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
		pMainMenu.setLayout(new GridLayout(1,6));
		pMainMenu.setBackground(Color.white);

		//image background for Center panel
		BufferedImage image = ImageIO.read(new File("backgroundtest.jpg"));
		JLabel label = new JLabel( new ImageIcon(image));
		pCenter.add(label);
		pCenter.setBackground(Color.black);
		pCenter.setLayout(new GridLayout(1,1));

		//menuBar is placed at the top of the screen
		pNorth.setBackground(Color.white);
		pNorth.add(menuBar);

		//add the panels onto the window
		mainWindow.add(pMainMenu,"South");
		mainWindow.add(pCenter,"Center");
		mainWindow.add(pNorth,"North");

		//window settings
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		mainWindow.setSize((screenSize.height / 2) + 100, (screenSize.height / 2) + 100);
		mainWindow.setResizable(true);
		mainWindow.setLocation(screenSize.width / 4, screenSize.height / 4);
		mainWindow.setTitle("Epsilon Game Launcher");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		//display menu
		mainWindow.show();
	}

	/**
		Button handler for EpsilonGameLauncher game buttons
	**/
	public class aButtonHandler implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			// in here is where the code goes in response to the click
			if(e.getSource() == btnBingo) {		// Bingo Button!
				System.out.println("BINGO");
				bingoGame = new Bingo();
				bingoGame.startBingo();
			}
			else if(e.getSource() == btnTicTacToe) {		// TicTacToe Button!
				System.out.println("TicTacToe");
				tttGame = new TicTacToe ();
			}
			else if (e.getSource () == btnHangman) {
				System.out.println ("Hangman");
				hangmanGame = new Hangman (mainWindow); //
			}
			else if(e.getSource() == btnMinsweeper) {		// Minesweeper Button!
				System.out.println("Minesweeper");
				try {
                Minesweeper newMinesweeper = new Minesweeper();
                newMinesweeper.startMinesweeper();
				}
				catch (Exception IOException) {
					//failed to play game
					System.out.println("Minesweeper Failed to open");
				}
			}
			else if(e.getSource() == btnBlackJack) {		// Black Jack Button!
				System.out.println("BlackJack");
				try{
					mainWindow.setVisible(true);
					BlackJack newBlackJack = new BlackJack(mainWindow, pCenter);
				}
				catch(Exception IOException){
					//failed to play a game
					System.out.println("BlackJack Failed to open");
				}
			}
			else if(e.getSource() == btnRockPaperScissors) {		// RPS Button!
				System.out.println("RPS");
				try{
					mainWindow.setVisible(false);
					RPS newRPS = new RPS(mainWindow, pCenter);
				}
				catch(Exception IOException){
					//failed to play a game
					System.out.println("RPS Failed to open");
				}
			}

			}
		}

		/**
			EpsilonGameLauncher main class. Creates and runs the launcher
		**/
	public static void main(String[] args) throws IOException
	{
		EpsilonGameLauncher launcher = new EpsilonGameLauncher();
	}
}
