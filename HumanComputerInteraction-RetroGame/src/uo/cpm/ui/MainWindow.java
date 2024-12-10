package uo.cpm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.net.URL;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import uo.cpm.model.Game;
import uo.cpm.model.Ticket;
import uo.cpm.service.RetroGame;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private int debugMode;

    private ResourceBundle texts;

    private JPanel contentPane, pnBorder, pnSouth, pnCenter, pnCenterNorth,
	    pnCenterCenter, panelGrid, pnCode, pnTicket;
    private JButton btnStart, btnCancel, btnEs, btnEn;
    private JLabel lblStore, lblCode, lblTicket;
    private JTextField txtCode, txtTicket;
    private JMenuBar menuBar;
    private JMenu mnGame, mnDebug, mnHelp;
    private JSeparator separator1, separator2;
    private JMenuItem mntmNew, mntmExit, mntmContents, mntmAbout, mntmDebug0,
	    mntmDebug1, mntmDebug2;

    private RetroGame game = null;
    private GameBoard board = null;

    public MainWindow(int debugMode) {
//	addComponentListener(new ComponentAdapter() {
//	    @Override
//	    public void componentResized(ComponentEvent e) {
//		System.out.println(((JFrame) e.getSource()).getSize());
//	    }
//	});

	addWindowListener(new WindowAdapter() {
	    @Override
	    public void windowClosing(WindowEvent e) {
		if (checkExit())
		    System.exit(0);
	    }
	});

	this.debugMode = debugMode;
	game = new RetroGame(debugMode);

	texts = ResourceBundle.getBundle("rcs/textos", new Locale("en"));
	setTitle(texts.getString("texto1"));
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(MainWindow.class.getResource("/img/logo.png")));
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 567, 345);
	setJMenuBar(getMenuBar_1());

	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new GridLayout(0, 1, 0, 0));
	contentPane.add(getPnBorder());
	setMinimumSize(new Dimension(527, 345));
	localize(new Locale("en"));
	loadHelp();
    }

    // Add it to the main window and call it from the constructor at the end
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

	hb.enableHelpKey(getRootPane(), "intro", hs); // F1
	hb.enableHelpOnButton(getMntmContents(), "intro", hs);

	// We associate to the contents on menu to the intro
	hb.enableHelp(getMntmContents(), "intro", hs);
    }

    public int getDebugMode() {
	return debugMode;
    }

    private boolean checkExit() {
	if (JOptionPane.showConfirmDialog(this,
		"Are you sure you want to exit?") == JOptionPane.YES_OPTION)
	    return true;
	return false;
    }

    public RetroGame getGame() {
	return game;
    }

    public void initialize() {
	if (board != null)
	    board.dispose();

	// We reinitialize the main window components
	txtCode.setText("");
	txtTicket.setText("");
    }

    // Creates the board, centers it, doesn´t allow to interact with the
    // parent window and sets it visible
    public void showBoard() {
	board = new GameBoard(this);
	board.setLocationRelativeTo(contentPane);
	board.setModal(true);
	board.setVisible(true);
    }

    private void localize(Locale localization) {
	ResourceBundle texts = ResourceBundle.getBundle("rcs/textos",
		localization);

	setTitle(texts.getString("texto1"));
	lblCode.setText(texts.getString("texto2"));
	btnStart.setText(texts.getString("texto4"));
	btnCancel.setText(texts.getString("texto5"));
	mnGame.setText(texts.getString("texto6"));
	mnHelp.setText(texts.getString("texto7"));
	mntmNew.setText(texts.getString("texto8"));
	mntmExit.setText(texts.getString("texto9"));
	mntmContents.setText(texts.getString("texto10"));
	mntmAbout.setText(texts.getString("texto11"));

	// CORRECTION: addition debug mode
	mnDebug.setText(texts.getString("texto17"));
	mntmDebug0.setText(texts.getString("texto19"));
	mntmDebug1.setText(texts.getString("texto20"));
	mntmDebug2.setText(texts.getString("texto21"));

	// Mnemonic
	mnHelp.setMnemonic(texts.getString("texto12").charAt(0));
	btnStart.setMnemonic(texts.getString("texto13").charAt(0));
	mnGame.setMnemonic(texts.getString("texto14").charAt(0));
	mntmExit.setMnemonic(texts.getString("texto15").charAt(0));
	mntmAbout.setMnemonic(texts.getString("texto16").charAt(0));
	mntmAbout.setMnemonic(texts.getString("texto18").charAt(0));

	// CORRECTION: addition debug mode
	mntmDebug0.setMnemonic(texts.getString("texto22").charAt(0));
	mntmDebug1.setMnemonic(texts.getString("texto23").charAt(0));
	mntmDebug2.setMnemonic(texts.getString("texto24").charAt(0));
    }

    private JPanel getPnBorder() {
	if (pnBorder == null) {
	    pnBorder = new JPanel();
	    pnBorder.setBackground(Color.WHITE);
	    pnBorder.setLayout(new BorderLayout(0, 0));
	    pnBorder.add(getPnSouth(), BorderLayout.SOUTH);
	    pnBorder.add(getPnCenter(), BorderLayout.CENTER);
	}
	return pnBorder;
    }

    private JPanel getPnSouth() {
	if (pnSouth == null) {
	    pnSouth = new JPanel();
	    pnSouth.setBackground(Color.WHITE);
	    pnSouth.add(getBtnStart());
	    pnSouth.add(getBtnCancel());
	}
	return pnSouth;
    }

    private JButton getBtnStart() {
	if (btnStart == null) {
	    btnStart = new JButton();
	    btnStart.setToolTipText("To start the game");
	    btnStart.setMnemonic('S');
	    btnStart.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    checkParameters();
		}
	    });
	    btnStart.setForeground(Color.BLACK);
	    btnStart.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnStart.setEnabled(true);
	    btnStart.setBackground(new Color(51, 255, 102));
	}
	return btnStart;
    }

    private void checkParameters() {
	// Checks that the text fields are NOT empty
	if (txtCode.getText().toString().equals(null)
		|| txtCode.getText().toString().trim().isEmpty()
		|| txtTicket.getText().toString().equals(null)
		|| txtTicket.getText().toString().trim().isEmpty()) {
	    JOptionPane.showMessageDialog(null,
		    "The text fields CANNOT be null nor empty", "Warning",
		    JOptionPane.WARNING_MESSAGE);
	} else {
	    Ticket ticket = new Ticket(txtTicket.getText());
	    Optional<Ticket> realTicket = game.getMenu().findTicket(ticket);
	    if (realTicket.isPresent()) {

		// The code of the store does NOT exist
		if (!game.getMenu()
			.getStore()
			.getCode()
			.equals(txtCode.getText())) {
		    JOptionPane.showMessageDialog(null,
			    "The code does NOT correspond to the store.",
			    "Error", JOptionPane.ERROR_MESSAGE);
		}

		// The ticket MUST be valid
		else if (realTicket.get().isValid()) {
		    // The ticket is lower than 20€
		    if (!(realTicket.get().getAmount() >= Ticket.MIN_PRICE))
			JOptionPane.showMessageDialog(null,
				"The price has to be greater than 20€.",
				"Warning", JOptionPane.WARNING_MESSAGE);

		    // The code and ticket are correct so it updates the
		    // ticket to not to be used again
		    else {
			realTicket.get().setValid(false);
			ticket.setValid(false);

			// CORRECTION: delete ticket
			getGame().setTicketToInvalid(ticket);
			getGame().removeTicket();
			showBoard();
		    }
		}

		// The ticket has already been used
		else if (!realTicket.get().isValid()) {
		    JOptionPane.showMessageDialog(null,
			    "The ticket is NOT valid anymore.", "Error",
			    JOptionPane.ERROR_MESSAGE);
		    // Delete
		    getGame().setTicketToInvalid(ticket);
		    getGame().removeTicket();
		}
	    } else {
		JOptionPane.showMessageDialog(null,
			"The ticket does NOT exist.", "Error",
			JOptionPane.ERROR_MESSAGE);
	    }
	}
    }

    private JButton getBtnCancel() {
	if (btnCancel == null) {
	    btnCancel = new JButton("Cancel");
	    btnCancel.setToolTipText("To cancel");
	    btnCancel.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    initialize();
		}
	    });
	    btnCancel.setForeground(Color.BLACK);
	    btnCancel.setFont(new Font("Tahoma", Font.PLAIN, 14));
	    btnCancel.setBackground(new Color(255, 0, 51));
	}
	return btnCancel;
    }

    private JPanel getPnCenter() {
	if (pnCenter == null) {
	    pnCenter = new JPanel();
	    pnCenter.setBackground(Color.WHITE);
	    pnCenter.setLayout(new BorderLayout(0, 0));
	    pnCenter.add(getPnCenterNorth(), BorderLayout.NORTH);
	    pnCenter.add(getPnCenterCenter(), BorderLayout.CENTER);
	}
	return pnCenter;
    }

    private JPanel getPnCenterNorth() {
	if (pnCenterNorth == null) {
	    pnCenterNorth = new JPanel();
	    pnCenterNorth.setBackground(Color.WHITE);
	    pnCenterNorth.add(getLblStore());
	    pnCenterNorth.add(getBtnEs());
	    pnCenterNorth.add(getBtnEn());
	}
	return pnCenterNorth;
    }

    private JLabel getLblStore() {
	if (lblStore == null) {
	    String label = game.getMenu().getStore().getName();
	    lblStore = new JLabel(label);
	    lblStore.setForeground(Color.BLUE);
	    lblStore.setFont(new Font("Stencil", Font.PLAIN, 20));
	}
	return lblStore;
    }

    private JButton getBtnEs() {
	if (btnEs == null) {
	    btnEs = new JButton("ES");
	    btnEs.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    localize(new Locale("es"));
		    btnEn.setEnabled(true);
		    btnEs.setEnabled(false);
		}
	    });
	    btnEs.setIcon(
		    new ImageIcon(MainWindow.class.getResource("/img/es.png")));
	    btnEs.setVerticalTextPosition(SwingConstants.BOTTOM);
	    btnEs.setVerticalAlignment(SwingConstants.BOTTOM);
	    btnEs.setToolTipText("Change language to Spanish");
	    btnEs.setMnemonic('E');
	    btnEs.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnEs.setForeground(Color.BLACK);
	    btnEs.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btnEs.setBackground(Color.WHITE);
	}
	return btnEs;
    }

    private JButton getBtnEn() {
	if (btnEn == null) {
	    btnEn = new JButton("EN");
	    btnEn.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    localize(new Locale("en"));
		    btnEn.setEnabled(false);
		    btnEs.setEnabled(true);
		}
	    });
	    btnEn.setIcon(
		    new ImageIcon(MainWindow.class.getResource("/img/en.png")));
	    btnEn.setVerticalTextPosition(SwingConstants.BOTTOM);
	    btnEn.setToolTipText("Change language to English");
	    btnEn.setMnemonic('N');
	    btnEn.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnEn.setForeground(Color.BLACK);
	    btnEn.setFont(new Font("Tahoma", Font.PLAIN, 12));
	    btnEn.setEnabled(false);
	    btnEn.setBackground(Color.WHITE);
	}
	return btnEn;
    }

    private JPanel getPnCenterCenter() {
	if (pnCenterCenter == null) {
	    pnCenterCenter = new JPanel();
	    pnCenterCenter.setBackground(Color.WHITE);
	    pnCenterCenter.add(getPanelGrid());
	}
	return pnCenterCenter;
    }

    private JPanel getPanelGrid() {
	if (panelGrid == null) {
	    panelGrid = new JPanel();
	    panelGrid.setBackground(Color.WHITE);
	    panelGrid.setLayout(new GridLayout(2, 1, 0, 0));
	    panelGrid.add(getPnCode());
	    panelGrid.add(getPnTicket());
	}
	return panelGrid;
    }

    private JPanel getPnCode() {
	if (pnCode == null) {
	    pnCode = new JPanel();
	    pnCode.setBackground(Color.WHITE);
	    pnCode.add(getLblCode());
	    pnCode.add(getTxtCode());
	}
	return pnCode;
    }

    private JPanel getPnTicket() {
	if (pnTicket == null) {
	    pnTicket = new JPanel();
	    pnTicket.setBackground(Color.WHITE);
	    pnTicket.add(getLblTicket());
	    pnTicket.add(getTxtTicket());
	}
	return pnTicket;
    }

    private JLabel getLblCode() {
	if (lblCode == null) {
	    lblCode = new JLabel();
	    lblCode.setLabelFor(getTxtCode());
	    lblCode.setText((String) null);
	    lblCode.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblCode.setDisplayedMnemonic('C');
	}
	return lblCode;
    }

    public JTextField getTxtCode() {
	if (txtCode == null) {
	    txtCode = new JTextField();
	    txtCode.setToolTipText("Insert the store code");
	    txtCode.setColumns(10);
	}
	return txtCode;
    }

    private JLabel getLblTicket() {
	if (lblTicket == null) {
	    lblTicket = new JLabel("Ticket:");
	    lblTicket.setLabelFor(getTxtTicket());
	    lblTicket.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblTicket.setDisplayedMnemonic('T');
	}
	return lblTicket;
    }

    private JTextField getTxtTicket() {
	if (txtTicket == null) {
	    txtTicket = new JTextField();
	    txtTicket.setToolTipText("Insert the ticket number");
	    txtTicket.setColumns(10);
	}
	return txtTicket;
    }

    private JMenuBar getMenuBar_1() {
	if (menuBar == null) {
	    menuBar = new JMenuBar();
	    menuBar.add(getMnGame());

	    // CORRECTION: addition of the debug mode menu
	    menuBar.add(getMnDebug());
	    menuBar.add(getMnHelp());
	}

	return menuBar;
    }

    private JMenu getMnDebug() {
	if (mnDebug == null) {
	    mnDebug = new JMenu();
	    mnDebug.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mnDebug.setToolTipText("Shows the different debug modes.");
	    mnDebug.setMnemonic('D');
	    mnDebug.add(getMntmDebug0());
	    mnDebug.add(getMntmDebug1());
	    mnDebug.add(getMntmDebug2());
	}

	return mnDebug;
    }

    private JMenuItem getMntmDebug0() {
	if (mntmDebug0 == null) {
	    mntmDebug0 = new JMenuItem();
	    mntmDebug0.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmDebug0.setToolTipText("Changes to debug mode 0");
	    mntmDebug0.setMnemonic('0');
	    mntmDebug0.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    newDebugMode(Game.DEBUG0);
		}
	    });
	}

	return mntmDebug0;
    }

    private JMenuItem getMntmDebug1() {
	if (mntmDebug1 == null) {
	    mntmDebug1 = new JMenuItem();
	    mntmDebug1.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmDebug1.setToolTipText("Changes to debug mode 1");
	    mntmDebug1.setMnemonic('1');
	    mntmDebug1.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    newDebugMode(Game.DEBUG1);
		}
	    });
	}

	return mntmDebug1;
    }

    private JMenuItem getMntmDebug2() {
	if (mntmDebug2 == null) {
	    mntmDebug2 = new JMenuItem();
	    mntmDebug2.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmDebug2.setToolTipText("Changes to debug mode 2");
	    mntmDebug2.setMnemonic('2');
	    mntmDebug2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    newDebugMode(Game.DEBUG2);
		}
	    });
	}

	return mntmDebug2;
    }

    private void newDebugMode(int debugMode) {
	this.debugMode = debugMode;

	game = new RetroGame(debugMode);
    }

    private JMenu getMnGame() {
	if (mnGame == null) {
	    mnGame = new JMenu();
	    mnGame.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mnGame.setToolTipText("Displays the game menu.");
	    mnGame.setMnemonic('G');
	    mnGame.add(getMntmNew());
	    mnGame.add(getSeparator1());
	    mnGame.add(getMntmExit());
	}
	return mnGame;
    }

    private JMenuItem getMntmNew() {
	if (mntmNew == null) {
	    mntmNew = new JMenuItem();
	    mntmNew.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmNew.setToolTipText("To create a new game");
	    mntmNew.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (JOptionPane.showConfirmDialog(rootPane,
			    "Are you sure you want to discard the "
				    + "registration?") == JOptionPane.YES_OPTION)
			initialize();
		}
	    });
	    mntmNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N,
		    InputEvent.CTRL_DOWN_MASK));
	    mntmNew.setMnemonic('N');
	}
	return mntmNew;
    }

    private JSeparator getSeparator1() {
	if (separator1 == null) {
	    separator1 = new JSeparator();
	}
	return separator1;
    }

    private JMenuItem getMntmExit() {
	if (mntmExit == null) {
	    mntmExit = new JMenuItem();
	    mntmExit.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmExit.setToolTipText("To exit the application.");
	    mntmExit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (checkExit())
			System.exit(0);
		}
	    });
	    mntmExit.setMnemonic('E');
	}
	return mntmExit;
    }

    private JMenu getMnHelp() {
	if (mnHelp == null) {
	    mnHelp = new JMenu();
	    mnHelp.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mnHelp.setToolTipText("Displays the help menu.");
	    mnHelp.setMnemonic('H');
	    mnHelp.add(getMntmContents());
	    mnHelp.add(getSeparator2());
	    mnHelp.add(getMntmAbout());
	}
	return mnHelp;
    }

    private JMenuItem getMntmContents() {
	if (mntmContents == null) {
	    mntmContents = new JMenuItem();
	    mntmContents.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmContents.setToolTipText("Displays the contents.");
	    mntmContents.setMnemonic('o');
	}
	return mntmContents;
    }

    private JSeparator getSeparator2() {
	if (separator2 == null) {
	    separator2 = new JSeparator();
	}
	return separator2;
    }

    private JMenuItem getMntmAbout() {
	if (mntmAbout == null) {
	    mntmAbout = new JMenuItem();
	    mntmAbout.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    mntmAbout.setToolTipText("About the project.");
	    mntmAbout.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    JOptionPane.showMessageDialog(rootPane,
			    "MIKEL FERNÁNDEZ ESPARTA\nUO275688\nCPM-L-I4");
		}
	    });
	}
	return mntmAbout;
    }
}
