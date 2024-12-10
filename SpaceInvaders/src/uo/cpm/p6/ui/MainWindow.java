package uo.cpm.p6.ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;

import uo.cpm.p6.model.Board;
import uo.cpm.p6.model.Invader;
import uo.cpm.p6.model.Meteorite;
import uo.cpm.p6.rules.Game.Level;
import uo.cpm.p6.service.SpaceInvaders;
import uo.cpm.p6.util.Console;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane, pnShots, pnBoard;
    private SpaceInvaders game;
    private JButton btnDice;
    private JLabel lblSpaceship, lblEarth, lblScore;
    private JTextField txtScore;

    private JMenuBar menuBar;
    private JMenu mnGame, mnConfiguration, mnHelp;
    private JMenuItem mntmNew, mntmContent, mntmAbout, mntmExit;
    private JSeparator separator, separator_1;

    // This is the one and single listener we are going to use
    private MyButtonListener mBl = new MyButtonListener();

    private final ButtonGroup buttonGroup = new ButtonGroup();

    private JRadioButtonMenuItem rdbtnmntmEasy, rdbtnmntmIntermidiate,
	    rdbtnmntmHard;

    private Level level;

    public MainWindow(SpaceInvaders game) {
	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		if (checkExit()) {
		    Console.printlnRed("\nAPPLICATION CLOSED");
		    System.exit(0);
		}
	    }
	});

	setTitle("Game Invasion");
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(MainWindow.class.getResource("/img/spaceship.png")));
	setResizable(false);
	this.game = game;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 938, 430);
	setJMenuBar(getMenuBar_1());
	contentPane = new JPanel();
	contentPane.setForeground(Color.BLACK);
	contentPane.setBackground(Color.BLACK);

	setContentPane(contentPane);
	contentPane.setLayout(null);
	contentPane.add(getBtnDice());
	contentPane.add(getLblSpaceship());
	contentPane.add(getLblEarth());
	contentPane.add(getTxtScore());
	contentPane.add(getLblScore());
	contentPane.add(getPnShots());
	contentPane.add(getPnBoard());

	level = Level.INTERMIDIATE;
	initialize(level);
    }

    private JButton getBtnDice() {
	if (btnDice == null) {
	    btnDice = new JButton("");
	    btnDice.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    initGame();
		}
	    });
	    btnDice.setDisabledIcon(new ImageIcon(
		    MainWindow.class.getResource("/img/dice.jpg")));
	    btnDice.setToolTipText("Launch the dice");
	    btnDice.setIcon(new ImageIcon(
		    MainWindow.class.getResource("/img/dice.jpg")));
	    btnDice.setBounds(30, 41, 111, 108);
	}
	return btnDice;
    }

    private JLabel getLblSpaceship() {
	if (lblSpaceship == null) {
	    lblSpaceship = new JLabel("");
	    lblSpaceship.setIcon(new ImageIcon(
		    MainWindow.class.getResource("/img/spaceship.png")));
	    lblSpaceship.setBounds(309, 41, 148, 114);
	}
	return lblSpaceship;
    }

    private JLabel getLblEarth() {
	if (lblEarth == null) {
	    lblEarth = new JLabel("");
	    lblEarth.setIcon(new ImageIcon(
		    MainWindow.class.getResource("/img/earth.jpg")));
	    lblEarth.setBounds(719, 41, 188, 187);
	}
	return lblEarth;
    }

    private JTextField getTxtScore() {
	if (txtScore == null) {
	    txtScore = new JTextField();
	    txtScore.setHorizontalAlignment(SwingConstants.CENTER);
	    txtScore.setBorder(new LineBorder(new Color(255, 255, 255)));
	    txtScore.setFont(new Font("Consolas", Font.BOLD, 36));
	    txtScore.setText("0");
	    txtScore.setBackground(Color.BLACK);
	    txtScore.setForeground(new Color(0, 255, 102));
	    txtScore.setEditable(false);
	    txtScore.setBounds(543, 92, 133, 49);
	    txtScore.setColumns(10);
	}
	return txtScore;
    }

    private JLabel getLblScore() {
	if (lblScore == null) {
	    lblScore = new JLabel("Score");
	    lblScore.setForeground(new Color(0, 255, 102));
	    lblScore.setFont(new Font("Consolas", Font.BOLD, 36));
	    lblScore.setBounds(544, 41, 150, 63);
	}
	return lblScore;
    }

    private JPanel getPnShots() {
	if (pnShots == null) {
	    pnShots = new JPanel();
	    pnShots.setBackground(Color.BLACK);
	    pnShots.setBounds(151, 165, 449, 84);
	}
	return pnShots;
    }

    private JPanel getPnBoard() {
	if (pnBoard == null) {
	    pnBoard = new JPanel();
	    pnBoard.setBounds(30, 282, 797, 114);
	    pnBoard.setLayout(new GridLayout(1, 0, 0, 0));
	    // Dynamically generated buttons in the method prepareBoard()
	}
	return pnBoard;
    }

    private JMenuBar getMenuBar_1() {
	if (menuBar == null) {
	    menuBar = new JMenuBar();
	    menuBar.add(getMnGame());
	    menuBar.add(getMnConfiguration());
	    menuBar.add(getMnHelp());
	}
	return menuBar;
    }

    private JMenu getMnGame() {
	if (mnGame == null) {
	    mnGame = new JMenu("Game");
	    mnGame.setMnemonic('G');
	    mnGame.add(getMntmNew());
	    mnGame.add(getSeparator_1());
	    mnGame.add(getMntmExit());
	}
	return mnGame;
    }

    private JMenu getMnHelp() {
	if (mnHelp == null) {
	    mnHelp = new JMenu("Help");
	    mnHelp.setMnemonic('H');
	    mnHelp.add(getMntmContent());
	    mnHelp.add(getSeparator());
	    mnHelp.add(getMntmAbout());
	}
	return mnHelp;
    }

    private JMenuItem getMntmContent() {
	if (mntmContent == null) {
	    mntmContent = new JMenuItem("Content");
	    mntmContent.setMnemonic('n');
	    mntmContent.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    JOptionPane.showMessageDialog(rootPane,
			    "Game Invasion by Mikel Fernández Esparta");
		}
	    });
	}
	return mntmContent;
    }

    private JSeparator getSeparator() {
	if (separator == null) {
	    separator = new JSeparator();
	}
	return separator;
    }

    private JMenuItem getMntmAbout() {
	if (mntmAbout == null) {
	    mntmAbout = new JMenuItem("About");
	    mntmAbout.setMnemonic('A');
	    mntmAbout.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    JOptionPane.showMessageDialog(rootPane,
			    "HUMAN COMPUTER INTERACTION EII 2022/2023");
		}
	    });
	}
	return mntmAbout;
    }

    private JMenuItem getMntmNew() {
	if (mntmNew == null) {
	    mntmNew = new JMenuItem("New");
	    mntmNew.setMnemonic('N');
	    mntmNew.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (JOptionPane.showConfirmDialog(rootPane,
			    "You will start a new game. Are you sure?") == JOptionPane.YES_OPTION)
			restart();
		}
	    });
	    mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
		    InputEvent.CTRL_DOWN_MASK));
	}
	return mntmNew;
    }

    private JSeparator getSeparator_1() {
	if (separator_1 == null) {
	    separator_1 = new JSeparator();
	}
	return separator_1;
    }

    private JMenuItem getMntmExit() {
	if (mntmExit == null) {
	    mntmExit = new JMenuItem("Exit");
	    mntmExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
		    InputEvent.CTRL_DOWN_MASK));
	    mntmExit.setMnemonic('E');
	    mntmExit.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    if (checkExit()) {
			Console.printlnRed("APPLICATION CLOSED");
			System.exit(0);
		    }
		}
	    });
	}
	return mntmExit;
    }

    private JMenu getMnConfiguration() {
	if (mnConfiguration == null) {
	    mnConfiguration = new JMenu("Configuration");
	    mnConfiguration.setMnemonic('C');
	    mnConfiguration.add(getRdbtnmntmEasy());
	    mnConfiguration.add(getRdbtnmntmIntermidiate());
	    mnConfiguration.add(getRdbtnmntmHard());
	}
	return mnConfiguration;
    }

    // Used when we are about to close the application
    private boolean checkExit() {
	if (JOptionPane.showConfirmDialog(this,
		"Are you sure you want to exit and abort the game?") == JOptionPane.YES_OPTION)
	    return true;
	return false;
    }

    private void initialize(Level level) {
	game.initialize(level);
	prepareBoard(level);
	getBtnDice().setEnabled(true);
	getPnShots().removeAll(); // The shots needs to be empty
	getTxtScore().setText(String.valueOf(game.getScore()));
    }

    private void restart() {
	// We reinitialize the main window components and restart the game
	Console.printlnGreen("\n\nNew Game");

	txtScore.setText("0");
	btnDice.setEnabled(true);
	pnShots.removeAll();

	game.initialize(level);

	for (int i = 0; i < getPnBoard().getComponents().length; i++)
	    ((JButton) getPnBoard().getComponent(i)).setIcon(null);

	for (int i = 0; i < getPnShots().getComponents().length; i++)
	    ((JLabel) getPnShots().getComponent(i)).setIcon(null);

	getPnShots().repaint();
	getPnBoard().setEnabled(false);
    }

    private void prepareBoard(Level level) {
	Integer width = 0, dimension = 0;

	switch (level) {
	case EASY: {
	    width = 1010;
	    dimension = Board.DIM_EASY;
	    break;
	}
	case INTERMIDIATE: {
	    width = 815;
	    dimension = Board.DIM;
	    break;
	}
	case HARD: {
	    width = 610;
	    dimension = Board.DIM_HARD;
	    break;
	}
	}

	getPnBoard().setBounds(20, 250, width, 100);
	getPnBoard().setLayout(new GridLayout(1, dimension, 0, 0));

	// Deletes everything on the inside
	getPnBoard().removeAll();

	// Dynamically generate buttons
	for (int i = 0; i < dimension; i++)
	    getPnBoard().add(newCell(i));

	validate(); // Over the whole JFrame including the panels
	enableBoard(false); // First click on the dice to start
    }

    // To generate the board buttons dynamically
    private JButton newCell(Integer position) {
	JButton button = new JButton();
	button.setBorder(new LineBorder(new Color(0, 255, 102), 5));
	button.setBackground(Color.BLACK);
	button.setActionCommand(position.toString());
	// We have to add the action listener we created for the buttons
	// MyButtonListener
	button.addActionListener(mBl);

	return button;
    }

    class MyButtonListener implements ActionListener {

	public void actionPerformed(ActionEvent e) {
	    // References the button with the setActionCommand
	    // e.getSource() references the component that generates the event,
	    // Cast it in order to get the string representing the position of
	    // the parameter
	    Integer position = Integer
		    .parseInt(((JButton) e.getSource()).getActionCommand());
	    shoot(position);
	}
    }

    private void initGame() {
	Console.printlnGreen("START SPACE INVADERS GAME");

	game.launch();

	// Once launched the number of shots is updated
	paintShots();
	btnDice.setEnabled(false);
	enableBoard(true);
	showPositionInvaderAndMteorite();
    }

    private void enableBoard(boolean state) {
	for (int i = 0; i < getPnBoard().getComponents().length; i++)
	    getPnBoard().getComponent(i).setEnabled(state);

//   	for (Component c : getPnBoard().getComponents())
//   	    c.setEnabled(state);	
    }

    private JLabel newShot() {
	JLabel lblShot = new JLabel();
	lblShot.setIcon(ImageFactory.loadImage("/img/shoot.png"));
	lblShot.setBorder(new LineBorder(new Color(0, 255, 102), 1));
	return lblShot;
    }

    private void paintShots() {
	for (int i = 0; i < game.getShots(); i++)
	    pnShots.add(newShot());

	validate();
    }

    private void removeShot() {
	getPnShots().remove(0);
	pnShots.repaint();
    }

    private void shoot(Integer position) {
	game.shoot(position);
	getTxtScore().setText(String.valueOf(game.getScore()));

	// We have to remove one of the shots
	removeShot();
	paintCell(position);

	updateStateOfTheGame(position);
    }

    private void paintCell(Integer position) {
	String pictureName = game.getBoard().getPicture(position);
	ImageIcon imagen = ImageFactory.loadImage(pictureName);

	// We need to cast it in order to use setIcon()
	((JButton) getPnBoard().getComponent(position)).setIcon(imagen);
	((JButton) getPnBoard().getComponent(position)).setDisabledIcon(imagen);
    }

    private void discoverBoard() {
	for (int i = 0; i < getPnBoard().getComponentCount(); i++)
	    paintCell(i);
    }

    // With it we update the view
    private void updateStateOfTheGame(Integer position) {
	if (game.isGameOver()) {
	    enableBoard(false);
	    discoverBoard();

	    if (game.getGame().isInvaderFound()) {
		Console.printlnBlue("Game Over! You found the invader");
		JOptionPane.showMessageDialog(this,
			"Game Over!\nYou found the invader", "Game Invasion",
			JOptionPane.INFORMATION_MESSAGE);
	    } else if (game.getGame().isMeteoriteFound()) {
		Console.printlnRed("Game Over! A meteorite has destroyed you!");
		JOptionPane.showMessageDialog(this,
			"Game Over!\nA meteorite has destroyed you!",
			"Game Invasion", JOptionPane.INFORMATION_MESSAGE);
	    } else {
		Console.printlnRed("Game Over! You ran of out shots");
		JOptionPane.showMessageDialog(this,
			"Game Over!\nYou ran of out shots", "Game Invasion",
			JOptionPane.INFORMATION_MESSAGE);
	    }
	}
    }

    private void showPositionInvaderAndMteorite() {
	for (int i = 0; i < getPnBoard().getComponentCount(); i++) {
	    if (game.getBoard().getCell(i) instanceof Invader)
		System.out.println("Invader position: " + i);
	    else if (game.getBoard().getCell(i) instanceof Meteorite)
		System.out.println("Meteorite position: " + i);
	}
    }

    private JRadioButtonMenuItem getRdbtnmntmEasy() {
	if (rdbtnmntmEasy == null) {
	    rdbtnmntmEasy = new JRadioButtonMenuItem("Easy");
	    rdbtnmntmEasy.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    level = Level.EASY;
		    initialize(level);
		}
	    });
	    rdbtnmntmEasy.setMnemonic('s');
	    buttonGroup.add(rdbtnmntmEasy);
	}
	return rdbtnmntmEasy;
    }

    private JRadioButtonMenuItem getRdbtnmntmIntermidiate() {
	if (rdbtnmntmIntermidiate == null) {
	    rdbtnmntmIntermidiate = new JRadioButtonMenuItem("Intermidiate");
	    rdbtnmntmIntermidiate.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    level = Level.INTERMIDIATE;
		    initialize(level);
		}
	    });
	    rdbtnmntmIntermidiate.setMnemonic('I');
	    buttonGroup.add(rdbtnmntmIntermidiate);

	}
	return rdbtnmntmIntermidiate;
    }

    private JRadioButtonMenuItem getRdbtnmntmHard() {
	if (rdbtnmntmHard == null) {
	    rdbtnmntmHard = new JRadioButtonMenuItem("Hard");
	    rdbtnmntmHard.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		    level = Level.HARD;
		    initialize(level);
		}
	    });
	    rdbtnmntmHard.setMnemonic('r');
	    buttonGroup.add(rdbtnmntmHard);
	}
	return rdbtnmntmHard;
    }
}
