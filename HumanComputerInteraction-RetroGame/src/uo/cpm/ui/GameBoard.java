package uo.cpm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import uo.cpm.model.Army;
import uo.cpm.model.Board;
import uo.cpm.model.Cell;
import uo.cpm.model.Game;
import uo.cpm.util.ImageFactory;

public class GameBoard extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane, pnEast, pnCenter, pnCenterCenter, pnEast1,
	    pnEast2, pnEast3, pnSouth, pnArmy, pnBoard;
    private JLabel lblWaves, lblPoints, lblLeaderIcon, lblLeader;
    private JTextField txtWaves, txtPoints;
    private JButton btnFinish, btnExit, btnRules;

    private MyButtonListener mbl = new MyButtonListener();

    private MainWindow mainWindow = null;
    private StatusPage statusPage = null;

    public GameBoard(MainWindow parent) {
	// To create an instance of the main window
	this.mainWindow = parent;
	setTitle("Video Game: Play");
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(GameBoard.class.getResource("/img/logo.png")));

	// Closes the JDialog, only that window, not the whole app
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setMinimumSize(new Dimension(890, 775));
	setBounds(100, 100, 752, 549);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	contentPane.add(getPnEast(), BorderLayout.EAST);
	contentPane.add(getPnCenter(), BorderLayout.CENTER);
	contentPane.add(getPnSouth(), BorderLayout.SOUTH);
	initialize();
	loadHelp();

	// CORRECTION: To change the size of the pictures when we resize the
	// buttons. This avoids the problem of the 0 size exception.
	this.addComponentListener(new ComponentAdapter() {
	    @Override
	    public void componentResized(ComponentEvent e) {
		associateImagesToButtons();
	    }
	});
    }

    private void loadHelp() {
	URL hsURL;
	HelpSet hs;

	try {
	    File fichero = new File("help/Help.hs");
	    hsURL = fichero.toURI().toURL();
	    hs = new HelpSet(null, hsURL);
	} catch (Exception e) {
	    System.out.println("Help not found!");
	    return;
	}

	HelpBroker hb = hs.createHelpBroker();

	hb.enableHelpKey(getRootPane(), "play", hs); // F1
	hb.enableHelpOnButton(getBtnRules(), "play", hs);

	// We associate to the contents on menu to the intro
	hb.enableHelp(getBtnRules(), "play", hs);
    }

    public MainWindow getMainWindow() {
	return mainWindow;
    }

    private void initialize() {
	getMainWindow().getGame().initialize();
	prepareBoard();
	getPnArmy().removeAll(); // The army needs to be empty
	txtPoints.setText(String.valueOf(getMainWindow().getGame().getScore()));
	txtWaves.setText(String.valueOf(getMainWindow().getGame().getWave()));
	prepareArmy();
	enableBoard(true);
    }

    private JPanel getPnEast() {
	if (pnEast == null) {
	    pnEast = new JPanel();
	    pnEast.setLayout(new GridLayout(0, 1, 0, 0));
	    pnEast.add(getPnEast1());
	    pnEast.add(getPnEast2());
	    pnEast.add(getPnEast3());
	}
	return pnEast;
    }

    private JPanel getPnCenter() {
	if (pnCenter == null) {
	    pnCenter = new JPanel();
	    pnCenter.setLayout(new BorderLayout(0, 0));
	    pnCenter.add(getPnArmy(), BorderLayout.NORTH);
	    pnCenter.add(getPnCenterCenter());
	}
	return pnCenter;
    }

    private JPanel getPnEast1() {
	if (pnEast1 == null) {
	    pnEast1 = new JPanel();
	    pnEast1.setBackground(Color.WHITE);
	    pnEast1.add(getLblWaves());
	    pnEast1.add(getTxtWaves());
	}
	return pnEast1;
    }

    private JLabel getLblWaves() {
	if (lblWaves == null) {
	    lblWaves = new JLabel("Waves:");
	    lblWaves.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblWaves.setDisplayedMnemonic('W');
	    lblWaves.setLabelFor(getTxtWaves());
	}
	return lblWaves;
    }

    private JTextField getTxtWaves() {
	if (txtWaves == null) {
	    txtWaves = new JTextField();
	    txtWaves.setHorizontalAlignment(SwingConstants.CENTER);
	    txtWaves.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    txtWaves.setToolTipText(
		    "Shows the number of the wave you are playing");
	    txtWaves.setBackground(Color.WHITE);
	    txtWaves.setEditable(false);
	    txtWaves.setColumns(10);
	}
	return txtWaves;
    }

    private JPanel getPnEast2() {
	if (pnEast2 == null) {
	    pnEast2 = new JPanel();
	    pnEast2.setBackground(Color.WHITE);
	    pnEast2.add(getLblPoints());
	    pnEast2.add(getTxtPoints());
	}
	return pnEast2;
    }

    private JLabel getLblPoints() {
	if (lblPoints == null) {
	    lblPoints = new JLabel("Points:");
	    lblPoints.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblPoints.setLabelFor(getTxtPoints());
	    lblPoints.setDisplayedMnemonic('P');
	}
	return lblPoints;
    }

    private JTextField getTxtPoints() {
	if (txtPoints == null) {
	    txtPoints = new JTextField();
	    txtPoints.setHorizontalAlignment(SwingConstants.CENTER);
	    txtPoints.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    txtPoints.setToolTipText("Shows the score");
	    txtPoints.setEditable(false);
	    txtPoints.setBackground(Color.WHITE);
	    txtPoints.setColumns(10);
	}
	return txtPoints;
    }

    private JPanel getPnSouth() {
	if (pnSouth == null) {
	    pnSouth = new JPanel();
	    pnSouth.setBackground(Color.WHITE);
	    pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    pnSouth.add(getBtnFinish());
	    pnSouth.add(getBtnExit());
	    pnSouth.add(getBtnRules());
	}
	return pnSouth;
    }

    private JButton getBtnFinish() {
	if (btnFinish == null) {
	    btnFinish = new JButton("Finish");
	    btnFinish.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    showStatusGame();
		}
	    });
	    btnFinish.setEnabled(false);
	    btnFinish.setToolTipText(
		    "To finish playing the game once it\u00B4s over");
	    btnFinish.setMnemonic('F');
	    btnFinish.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnFinish.setForeground(Color.BLACK);
	    btnFinish.setBackground(new Color(51, 255, 102));
	}
	return btnFinish;
    }

    private JButton getBtnExit() {
	if (btnExit == null) {
	    btnExit = new JButton("Exit");
	    btnExit.setToolTipText("To exit the game");
	    btnExit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    dispose(); // In order to close just the JDialog
		}
	    });
	    btnExit.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnExit.setForeground(Color.BLACK);
	    btnExit.setBackground(new Color(255, 0, 51));
	}
	return btnExit;
    }

    private JButton getBtnRules() {
	if (btnRules == null) {
	    btnRules = new JButton("Rules");
	    btnRules.setToolTipText("Shows the rules of the game");
	    btnRules.setMnemonic('R');
	    btnRules.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnRules.setForeground(Color.BLACK);
	    btnRules.setBackground(new Color(51, 255, 102));
	}
	return btnRules;
    }

    private JPanel getPnCenterCenter() {
	if (pnCenterCenter == null) {
	    pnCenterCenter = new JPanel();
	    pnCenterCenter.setBackground(Color.WHITE);
	    pnCenterCenter.add(getPnBoard());
	}
	return pnCenterCenter;
    }

    private JPanel getPnArmy() {
	if (pnArmy == null) {
	    pnArmy = new JPanel();
	    pnArmy.setBackground(Color.WHITE);
	}
	return pnArmy;
    }

    private JPanel getPnBoard() {
	if (pnBoard == null) {
	    pnBoard = new JPanel();
	    pnBoard.setBackground(Color.GREEN);
	}
	return pnBoard;
    }

    private void prepareArmy() {
	int dimension = Army.DIM_ARMY;
	getPnArmy().setBounds(20, 250, 600, 100);
	getPnArmy().setLayout(new GridLayout(1, dimension, 0, 0));

	// Deletes everything on the inside
	getPnArmy().removeAll();

	// Dynamically generate buttons
	for (int i = 0; i < dimension; i++)
	    getPnArmy().add(newCellArmy(i));

	// Over the whole JFrame including the panels
	validate();

	paintCellsArmy();
    }

    private void paintCellsArmy() {
	for (int i = 0; i < getPnArmy().getComponentCount(); i++)
	    paintCellArmy(i);
    }

    // To generate the army buttons dynamically
    private JButton newCellArmy(Integer position) {
	JButton button = new JButton();
	button.setBorder(new LineBorder(Color.BLUE));
	button.setBackground(Color.BLACK);
	button.setActionCommand(position.toString());
	button.setEnabled(false);
	return button;
    }

    private void paintCellArmy(Integer position) {
	String pictureName = getMainWindow().getGame()
		.getArmy()
		.getPicture(position);
	ImageIcon imagen = ImageFactory.loadImage(pictureName);

	// We need to cast it in order to use setIcon()
	((JButton) getPnArmy().getComponent(position)).setIcon(imagen);
	((JButton) getPnArmy().getComponent(position)).setDisabledIcon(imagen);
    }

    private void adaptImage(JButton button, String imagePath) {
	ImageIcon tmpImagen = ImageFactory.loadImage(imagePath);
//	ImageIcon tmpImagen = new ImageIcon(getClass().getResource(imagePath));

	float delta = ((button.getWidth() * 100) / tmpImagen.getIconWidth())
		/ 100f;
	if (tmpImagen.getIconHeight() > button.getHeight())
	    delta = ((button.getHeight() * 100) / tmpImagen.getIconHeight())
		    / 100f;

	int ancho = (int) (tmpImagen.getIconWidth() * delta);
	int alto = (int) (tmpImagen.getIconHeight() * delta);

	button.setIcon(null);
	button.setIcon(new ImageIcon(tmpImagen.getImage()
		.getScaledInstance(ancho, alto, Image.SCALE_SMOOTH)));

	repaint();
	validate();
    }

    private void associateImagesToButtons() {
	for (int i = 0; i < pnArmy.getComponentCount(); i++) {
	    JButton boton = (JButton) (pnArmy.getComponents()[i]);
	    String pictureName = getMainWindow().getGame()
		    .getArmy()
		    .getPicture(i);
	    adaptImage(boton, pictureName);
	}

	repaint();
	validate();
    }

    private void prepareBoard() {
	int dimension = Board.DIM;

	getPnBoard().setBounds(20, 250, 660, 100);
	getPnBoard().setLayout(new GridLayout(dimension, dimension, 0, 0));

	// Deletes everything on the inside
	getPnBoard().removeAll();

	// Dynamically generate buttons
	for (int i = 0; i < dimension; i++) {
	    for (int j = 0; j < dimension; j++) {
		getPnBoard().add(newCellBoard(i, j));
	    }
	}

	// Over the whole JFrame including the panels
	validate();

	for (int i = 0; i < dimension; i++)
	    for (int j = 0; j < dimension; j++)
		paintCellBoard(i, j);

	validate();
	repaint();
	enableBoard(true);
    }

    private void enableBoard(boolean state) {
	for (Component c : getPnBoard().getComponents())
	    c.setEnabled(state);
    }

    // To generate the board buttons dynamically
    private JButton newCellBoard(Integer x, Integer y) {
	JButton button = new JButton();
	button.setBorder(new LineBorder(Color.BLUE));
	button.setBackground(Color.BLACK);
	button.setEnabled(false);
	button.addActionListener(mbl);
	Integer pos = x * Board.DIM + y;
	button.setActionCommand(pos.toString());
	return button;
    }

    private void paintCellBoard(Integer x, Integer y) {
	String pictureName = getMainWindow().getGame()
		.getBoard()
		.getPicture(x, y);
	ImageIcon imagen = ImageFactory.loadImage(pictureName);

	int sum = x * Board.DIM + y;

	Component[] components = getPnBoard().getComponents();

	((JButton) components[sum]).setIcon(imagen);
	((JButton) components[sum]).setDisabledIcon(imagen);
    }

    class MyButtonListener implements ActionListener {
	@Override
	public void actionPerformed(ActionEvent e) {
	    // References the button that generates the event with the
	    // setActionCommand e.getSource()
	    // Cast it in to get the string representing the position of
	    // the parameter
	    Integer position = Integer
		    .parseInt(((JButton) e.getSource()).getActionCommand());
	    Integer x = position / Board.DIM;
	    // The rest of the division provides the y coordinate
	    Integer y = position % Board.DIM;

	    Cell newCell = getMainWindow().getGame().getArmy().getCell(0);
	    shoot(x, y, newCell);
	}
    }

    private void shoot(Integer x, Integer y, Cell newCell) {
	getMainWindow().getGame().shoot(x, y, newCell);
	paintCellBoard(x, y);
	repaint();
	validate();

	// When it successfully selects an empty cell to place your army,
	// the board is updated and it is removed from the army team
	if (getMainWindow().getGame().isSucessful()) {
	    getMainWindow().getGame().getArmy().removeCell(0);
	    paintCellsArmy();
	}

	// No more shots left then next wave comes, 5 new invaders appear
	// and 5 new members in the army
	endWave();

	// If it´s over, it enables the Finish button and shows a message
	gameOver();
    }

    public void endWave() {
	if (getMainWindow().getGame().getShots() == 0) {

	    // Set back the nº of shots to 5 and generate a new army
	    getMainWindow().getGame().setShots(Game.MAX_SHOTS);
	    getMainWindow().getGame()
		    .getArmy()
		    .shuffle(getMainWindow().getDebugMode());

	    // Update the nº of the wave
	    // CORRECTION: avoid business logic
	    getMainWindow().getGame().incWave();

	    txtWaves.setText(
		    String.valueOf(getMainWindow().getGame().getWave()));

	    paintCellsArmy();

	    // Remove the possible colonies once you have placed the army
	    removeColonies();
	}
    }

    public void removeColonies() {
	getMainWindow().getGame().getBoard().removePairs();

	for (int i = 0; i < Board.DIM; i++)
	    for (int j = 0; j < Board.DIM; j++)
		paintCellBoard(i, j);

	// CORRECTION: update score
	txtPoints.setText(String.valueOf(getMainWindow().getGame().getScore()));

	validate();
	repaint();
    }

    private boolean isGameOver() {
	return getMainWindow().getGame().isGameOver();
    }

    private boolean isBoardFull() {
	return getMainWindow().getGame().isBoardFull();
    }

    private boolean isLeaderEliminated() {
	return getMainWindow().getGame().isLeaderEliminated();
    }

    public void gameOver() {
	if (isGameOver()) {

	    // CORRECTION: the value of the wave does not keep on incrementing
	    if (getMainWindow().getGame().getWave() > Game.MAX_WAVE)
		txtWaves.setText(String.valueOf(Game.MAX_WAVE));

	    btnFinish.setEnabled(true);

	    // CORRECTION: depending how the game ended show a different message
	    // and update the score when is full
	    if (isBoardFull()) {
		txtPoints.setText(
			String.valueOf(getMainWindow().getGame().getScore()));

		JOptionPane.showMessageDialog(null,
			"GAME OVER\n BOARD IS FULL");
	    } else if (isLeaderEliminated())
		JOptionPane.showMessageDialog(null,
			"GAME OVER\n LEADER WAS ELIMINATED");
	    else
		JOptionPane.showMessageDialog(null,
			"GAME OVER\n 10 rounds over");

	    enableBoard(false);
	    getPnArmy().removeAll();

	    validate();
	    repaint();
	}
    }

    public void showStatusGame() {
	statusPage = new StatusPage(this);
	statusPage.setLocationRelativeTo(contentPane);
	statusPage.setModal(true);
	statusPage.setVisible(true);
    }

    private JPanel getPnEast3() {
	if (pnEast3 == null) {
	    pnEast3 = new JPanel();
	    pnEast3.setBackground(Color.WHITE);
	    pnEast3.add(getLblLeader());
	    pnEast3.add(getLblLeaderIcon());
	}
	return pnEast3;
    }

    private JLabel getLblLeaderIcon() {
	if (lblLeaderIcon == null) {
	    lblLeaderIcon = new JLabel("");
	    lblLeaderIcon.setIcon(new ImageIcon(
		    GameBoard.class.getResource("/img/mario.png")));
	    lblLeaderIcon.setHorizontalTextPosition(SwingConstants.CENTER);
	    lblLeaderIcon.setHorizontalAlignment(SwingConstants.CENTER);
	}
	return lblLeaderIcon;
    }

    private JLabel getLblLeader() {
	if (lblLeader == null) {
	    lblLeader = new JLabel("Leader:");
	    lblLeader.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	}
	return lblLeader;
    }
}