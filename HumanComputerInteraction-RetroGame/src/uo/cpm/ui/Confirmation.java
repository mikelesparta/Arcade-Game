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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class Confirmation extends JDialog {

    private static final long serialVersionUID = 1L;

    private EndPage endPage = null;

    private JPanel contentPane, panel, panelCenter, pnCenter, pnCenterNorth,
	    pnCenterCenter, pnSouth;
    private JLabel lblId, lblLogo, lbkConfirmation;
    private JTextField txtId;
    private JButton btnFinish;

    private Prizes prizes = null;

    public Confirmation(Prizes parent) {
	this.prizes = parent;
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(Confirmation.class.getResource("/img/logo.png")));
	setTitle("Video Game: Confirmation");
	setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	setBounds(100, 100, 415, 199);
	contentPane = new JPanel();
	contentPane.setBackground(Color.WHITE);
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
	setContentPane(contentPane);
	contentPane.setLayout(new GridLayout(0, 1, 0, 0));
	contentPane.add(getPanel());

	// Set the minimum size of the frame
	this.setMinimumSize(new Dimension(395, 259));
	this.setMaximumSize(new Dimension(600, 360));
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

	hb.enableHelpKey(getRootPane(), "save", hs); // F1
    }

    public Prizes getPrizes() {
	return prizes;
    }

    private JPanel getPanel1() {
	if (panelCenter == null) {
	    panelCenter = new JPanel();
	    panelCenter.setBackground(Color.WHITE);
	    panelCenter.setLayout(new BorderLayout(0, 0));
	    panelCenter.add(getPanelCenter(), BorderLayout.CENTER);
	    panelCenter.add(getPanelSouth(), BorderLayout.SOUTH);
	}
	return panelCenter;
    }

    private JPanel getPanelCenter() {
	if (pnCenter == null) {
	    pnCenter = new JPanel();
	    pnCenter.setBackground(Color.WHITE);
	    pnCenter.setLayout(new BorderLayout(0, 0));
	    pnCenter.add(getPnCenterNorth(), BorderLayout.NORTH);
	    pnCenter.add(getPnCenterCenter(), BorderLayout.CENTER);
	}
	return pnCenter;
    }

    private JPanel getPanelSouth() {
	if (pnSouth == null) {
	    pnSouth = new JPanel();
	    pnSouth.setBackground(Color.WHITE);
	    pnSouth.add(getBtnFinish());
	}
	return pnSouth;
    }

    private JButton getBtnFinish() {
	if (btnFinish == null) {
	    btnFinish = new JButton("Finish");
	    btnFinish.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    if (txtId.getText().toString().equals(null)
			    || txtId.getText().toString().trim().isEmpty())
			JOptionPane.showMessageDialog(null,
				"The text field for the id CANNOT be null nor empty",
				"Warning", JOptionPane.WARNING_MESSAGE);
		    else
			showEndPage();
		}
	    });
	    btnFinish.setForeground(Color.BLACK);
	    btnFinish.setBackground(new Color(0, 255, 102));
	    btnFinish.setToolTipText("Finish your confirmation");
	    btnFinish.setMnemonic('F');
	}
	return btnFinish;
    }

    private JPanel getPanel() {
	if (panel == null) {
	    panel = new JPanel();
	    panel.setLayout(new BorderLayout(0, 0));
	    panel.add(getPanel1(), BorderLayout.CENTER);
	}
	return panel;
    }

    private JPanel getPnCenterNorth() {
	if (pnCenterNorth == null) {
	    pnCenterNorth = new JPanel();
	    pnCenterNorth.setBackground(Color.WHITE);
	    pnCenterNorth.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	    pnCenterNorth.add(getLblLogo());
	    pnCenterNorth.add(getLbkConfirmation());
	}
	return pnCenterNorth;
    }

    private JPanel getPnCenterCenter() {
	if (pnCenterCenter == null) {
	    pnCenterCenter = new JPanel();
	    pnCenterCenter.setBackground(Color.WHITE);
	    pnCenterCenter.add(getLblId());
	    pnCenterCenter.add(getTxtId());
	}
	return pnCenterCenter;
    }

    private JLabel getLblId() {
	if (lblId == null) {
	    lblId = new JLabel("ID:");
	    lblId.setLabelFor(getTxtId());
	    lblId.setDisplayedMnemonic('I');
	}
	return lblId;
    }

    public JTextField getTxtId() {
	if (txtId == null) {
	    txtId = new JTextField();
	    txtId.setToolTipText("Insert the ID of the user");
	    txtId.setColumns(10);
	}
	return txtId;
    }

    public void showEndPage() {
	endPage = new EndPage(this);
	endPage.setLocationRelativeTo(contentPane);
	endPage.setModal(true);
	this.setVisible(false);
	endPage.setVisible(true);
    }

    private JLabel getLblLogo() {
	if (lblLogo == null) {
	    lblLogo = new JLabel("");
	    lblLogo.setIcon(new ImageIcon(
		    Confirmation.class.getResource("/img/logo2.png")));
	}
	return lblLogo;
    }

    private JLabel getLbkConfirmation() {
	if (lbkConfirmation == null) {
	    lbkConfirmation = new JLabel("Confirmation");
	    lbkConfirmation
		    .setFont(new Font("Berlin Sans FB Demi", Font.PLAIN, 25));
	}
	return lbkConfirmation;
    }
}
