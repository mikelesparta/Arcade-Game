package uo.cpm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class StatusPage extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane, panelBorder, pnNorth, pnCenter, pnSouth, panel;
    private JLabel lblIconWin, lblMessage, lblPoints;
    private JButton btnChoosePrize, btnExit;
    private JTextField txtPoints;

    private GameBoard gameBoard = null;
    private Prizes prizes = null;

    public StatusPage(GameBoard parent) {
	this.gameBoard = parent;
	if (getGameBoard().getMainWindow().getGame().hasWon()
		&& getGameBoard().getMainWindow().getGame().getScore() > 0)
	    setTitle("Video Game: Win");
	else
	    setTitle("Video Game: Lose");
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(StatusPage.class.getResource("/img/logo.png")));
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 421, 264);
	setMinimumSize(new Dimension(290, 260));
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new GridLayout(1, 0, 0, 0));
	contentPane.add(getPanelBorder());
	loadHelp();
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

	hb.enableHelpKey(getRootPane(), "intro", hs); // F1
    }

    public GameBoard getGameBoard() {
	return gameBoard;
    }

    private JPanel getPanelBorder() {
	if (panelBorder == null) {
	    panelBorder = new JPanel();
	    panelBorder.setBackground(Color.WHITE);
	    panelBorder.setLayout(new BorderLayout(0, 0));
	    panelBorder.add(getPnNorth(), BorderLayout.NORTH);
	    panelBorder.add(getPnCenter(), BorderLayout.CENTER);
	    panelBorder.add(getPnSouth(), BorderLayout.SOUTH);
	}
	return panelBorder;
    }

    private JPanel getPnNorth() {
	if (pnNorth == null) {
	    pnNorth = new JPanel();
	    pnNorth.setBackground(Color.WHITE);
	    pnNorth.add(getLblIconWin());
	    pnNorth.add(getLblMessage());
	}
	return pnNorth;
    }

    private JLabel getLblIconWin() {
	if (lblIconWin == null) {
	    lblIconWin = new JLabel("");
	    if (getGameBoard().getMainWindow().getGame().hasWon()
		    && getGameBoard().getMainWindow().getGame().getScore() > 0)
		lblIconWin.setIcon(new ImageIcon(
			StatusPage.class.getResource("/img/check.png")));
	    else
		lblIconWin.setIcon(new ImageIcon(
			StatusPage.class.getResource("/img/fail.png")));
	}
	return lblIconWin;
    }

    private JLabel getLblMessage() {
	if (lblMessage == null) {
	    lblMessage = new JLabel("");
	    // lblMessage.setFont(new Font("Palatino Linotype", Font.PLAIN,
	    // 14));
	    lblMessage.setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
	    if (getGameBoard().getMainWindow().getGame().hasWon()
		    && getGameBoard().getMainWindow().getGame().getScore() > 0)
		lblMessage.setText("YOU WON!");
	    else
		lblMessage.setText("YOU LOST");

	}
	return lblMessage;
    }

    private JPanel getPnCenter() {
	if (pnCenter == null) {
	    pnCenter = new JPanel();
	    pnCenter.setBackground(Color.WHITE);
	    pnCenter.setLayout(new GridLayout(0, 1, 0, 0));
	    pnCenter.add(getPanel());
	}
	return pnCenter;
    }

    private JPanel getPnSouth() {
	if (pnSouth == null) {
	    pnSouth = new JPanel();
	    pnSouth.setBackground(Color.WHITE);
	    pnSouth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    pnSouth.add(getBtnChoosePrize());
	    pnSouth.add(getBtnExit());
	}
	return pnSouth;
    }

    private JButton getBtnChoosePrize() {
	if (btnChoosePrize == null) {
	    btnChoosePrize = new JButton("Choose prize");
	    // If the user won the button is enabled, otherwise not
	    if (getGameBoard().getMainWindow().getGame().hasWon()
		    && getGameBoard().getMainWindow().getGame().getScore() > 0)
		btnChoosePrize.setEnabled(true);
	    else {
		btnChoosePrize.setEnabled(false);
		btnChoosePrize.setVisible(false);
	    }
	    btnChoosePrize.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    showAwards();
		}
	    });
	    btnChoosePrize.setMnemonic('C');
	    btnChoosePrize.setToolTipText(
		    "Shows the list of prizes you may choose from");
	}
	btnChoosePrize.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	btnChoosePrize.setForeground(Color.BLACK);
	btnChoosePrize.setBackground(new Color(51, 255, 102));
	return btnChoosePrize;
    }

    private JButton getBtnExit() {
	if (btnExit == null) {
	    btnExit = new JButton("Exit");
	    btnExit.setToolTipText(
		    "To exit the application if you lost or close the window if you won");
	    btnExit.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (getGameBoard().getMainWindow().getGame().hasWon()
			    && getGameBoard().getMainWindow()
				    .getGame()
				    .getScore() > 0)
			dispose(); // In order to close just the JDialog

		    // Reinitialize the game
		    else {
			getGameBoard().dispose();
			dispose();
			getGameBoard().getMainWindow().initialize();
		    }
		}
	    });
	    btnExit.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnExit.setForeground(Color.BLACK);
	    btnExit.setBackground(new Color(255, 0, 51));
	}
	return btnExit;
    }

    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.add(getLblPoints());
	    panel.add(getTxtPoints());
	}
	return panel;
    }

    private JLabel getLblPoints() {
	if (lblPoints == null) {
	    lblPoints = new JLabel("Total points:");
	    lblPoints.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblPoints.setLabelFor(getTxtPoints());
	    lblPoints.setDisplayedMnemonic('T');
	}
	return lblPoints;
    }

    private JTextField getTxtPoints() {
	if (txtPoints == null) {
	    txtPoints = new JTextField();
	    txtPoints.setText(String.valueOf(
		    getGameBoard().getMainWindow().getGame().getScore()));
	    txtPoints.setToolTipText("Shows the score from the game");
	    txtPoints.setEditable(false);
	    txtPoints.setColumns(10);
	}
	return txtPoints;
    }

    public void showAwards() {
	prizes = new Prizes(this);
	prizes.setLocationRelativeTo(contentPane);
	prizes.setModal(true);
	prizes.setVisible(true);
    }
}
