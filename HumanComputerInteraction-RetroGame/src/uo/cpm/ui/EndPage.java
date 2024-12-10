package uo.cpm.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;
import java.util.List;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import uo.cpm.model.Delivery;

public class EndPage extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane, panel, panelNorthFlow, panelSouth, panelNorth;
    private JButton btnOK;
    private JLabel lblCongrats, lblCongratsLogo;

    private Confirmation parent = null;

    public EndPage(Confirmation parent) {
	this.parent = parent;
	setResizable(false);
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(EndPage.class.getResource("/img/logo.png")));

	setTitle("Video Game: End");

	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 502, 179);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new GridLayout(1, 0, 0, 0));
	contentPane.add(getPanel());
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

    public Confirmation getConfirmation() {
	return parent;
    }

    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setBackground(Color.WHITE);
	    panel.setLayout(new BorderLayout(0, 0));
	    panel.add(getPanelSouth(), BorderLayout.SOUTH);
	    panel.add(getPanelNorth(), BorderLayout.NORTH);
	}
	return panel;
    }

    private JPanel getPanelNorthFlow() {
	if (panelNorthFlow == null) {
	    panelNorthFlow = new JPanel();
	    panelNorthFlow.setBackground(Color.WHITE);
	    panelNorthFlow.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    panelNorthFlow.add(getLblCongratsLogo());
	    panelNorthFlow.add(getLblCongrats());
	}
	return panelNorthFlow;
    }

    private JPanel getPanelSouth() {
	if (panelSouth == null) {
	    panelSouth = new JPanel();
	    panelSouth.setBackground(Color.WHITE);
	    panelSouth.add(getBtnOK());
	}
	return panelSouth;
    }

    private JButton getBtnOK() {
	if (btnOK == null) {
	    btnOK = new JButton("OK");
	    btnOK.setToolTipText("Saves the order into the delivery file");
	    btnOK.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    String id = getConfirmation().getTxtId().getText();
		    String code = getConfirmation().getPrizes()
			    .getStatusPage()
			    .getGameBoard()
			    .getMainWindow()
			    .getTxtCode()
			    .getText();
		    List<String> prizeCodes = getConfirmation().getPrizes()
			    .getAwardsSelection()
			    .getOrder()
			    .getPrizeCodeList();
		    Delivery delivery = new Delivery(id, code, prizeCodes);
		    // Save the order
		    getConfirmation().getPrizes()
			    .getAwardsSelection()
			    .saveOrder(delivery);

		    // Close the remaining windows and reinitialize the app
		    getConfirmation().getPrizes().dispose();
		    getConfirmation().getPrizes().getStatusPage().dispose();
		    getConfirmation().getPrizes()
			    .getStatusPage()
			    .getGameBoard()
			    .dispose();
		    dispose();
		    getConfirmation().getPrizes()
			    .getStatusPage()
			    .getGameBoard()
			    .getMainWindow()
			    .initialize();
		}
	    });
	    btnOK.setBackground(new Color(0, 255, 102));
	    btnOK.setMnemonic('O');
	}
	return btnOK;
    }

    private JLabel getLblCongrats() {
	if (lblCongrats == null) {
	    lblCongrats = new JLabel(
		    "Congrats you can pick up your selected items");
	    lblCongrats
		    .setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 18));
	}
	return lblCongrats;
    }

    private JLabel getLblCongratsLogo() {
	if (lblCongratsLogo == null) {
	    lblCongratsLogo = new JLabel("");
	    lblCongratsLogo.setIcon(new ImageIcon(
		    EndPage.class.getResource("/img/congrats.png")));
	}
	return lblCongratsLogo;
    }

    private JPanel getPanelNorth() {
	if (panelNorth == null) {
	    panelNorth = new JPanel();
	    panelNorth.setLayout(new GridLayout(0, 1, 0, 0));
	    panelNorth.add(getPanelNorthFlow());
	}
	return panelNorth;
    }
}
