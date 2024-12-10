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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import uo.cpm.model.Award;
import uo.cpm.service.AwardSelection;

public class Prizes extends JDialog {

    private static final long serialVersionUID = 1L;

    private JPanel panelBorder, pnWest, pnSouth, pnCenter, pnW1, pnW2, pnW3,
	    pnW4, pnEast, pnE2, pnE3, pnE4, pnC1, pnC2, pnC11, pnC12, pnE21,
	    pnR22, pnCenterNorth, pnCenterCenter, pnE1;
    private JScrollPane spOrder;
    private JTextArea txtAreaOrder;
    private JButton btnConfirm, btnCancel, btnAccesories, btnConsole,
	    btnVideogame, btnAffordable, btnAdd, btnRemove, btnOrder;
    private JLabel lblPoints, lblUnits, lblAmount, lblPrizes, lblArticles,
	    lblImageArticles;
    private JTextField txtPoints, txtAmount;
    private JSpinner spUnits;
    private JComboBox<Award> cbAwards;

    private StatusPage statusPage = null;
    private Confirmation confirmation = null;
    private AwardSelection awardsSelection = null;

    public Prizes(StatusPage parent) {
	statusPage = parent;
	awardsSelection = new AwardSelection(
		getStatusPage().getGameBoard().getMainWindow().getGame());
	setTitle("Video Game: Prizes");
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(Prizes.class.getResource("/img/logo.png")));
	setBounds(100, 100, 877, 606);
	setMinimumSize(new Dimension(850, 580));
	getContentPane().setLayout(new GridLayout(0, 1, 0, 0));
	getContentPane().add(getPanelBorder());
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

	hb.enableHelpKey(getRootPane(), "select", hs); // F1
    }

    public StatusPage getStatusPage() {
	return statusPage;
    }

    public AwardSelection getAwardsSelection() {
	return awardsSelection;
    }

    private JPanel getPanelBorder() {
	if (panelBorder == null) {
	    panelBorder = new JPanel();
	    panelBorder.setBackground(Color.WHITE);
	    panelBorder.setLayout(new BorderLayout(0, 0));
	    panelBorder.add(getPnWest(), BorderLayout.WEST);
	    panelBorder.add(getPnSouth(), BorderLayout.SOUTH);
	    panelBorder.add(getPnCenter(), BorderLayout.CENTER);
	    panelBorder.add(getPnEast(), BorderLayout.EAST);
	}
	return panelBorder;
    }

    private JPanel getPnWest() {
	if (pnWest == null) {
	    pnWest = new JPanel();
	    pnWest.setBackground(Color.WHITE);
	    pnWest.setLayout(new GridLayout(4, 0, 0, 0));
	    pnWest.add(getPnW1());
	    pnWest.add(getPnW2());
	    pnWest.add(getPnW3());
	    pnWest.add(getPnW4());
	}
	return pnWest;
    }

    private JPanel getPnSouth() {
	if (pnSouth == null) {
	    pnSouth = new JPanel();
	    pnSouth.setBackground(Color.WHITE);
	    pnSouth.add(getBtnConfirm());
	    pnSouth.add(getBtnCancel());
	}
	return pnSouth;
    }

    private JPanel getPnCenter() {
	if (pnCenter == null) {
	    pnCenter = new JPanel();
	    pnCenter.setBackground(Color.WHITE);
	    pnCenter.setLayout(new BorderLayout(0, 0));
	    pnCenter.add(getPnCenterNorth(), BorderLayout.NORTH);
	    pnCenter.add(getPnCenterCenter());
	}
	return pnCenter;
    }

    private JButton getBtnConfirm() {
	if (btnConfirm == null) {
	    btnConfirm = new JButton("Confirm");
	    btnConfirm.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    // When you don´t spend all your points
		    if (awardsSelection.getOrderTotalPoints() < getStatusPage()
			    .getGameBoard()
			    .getMainWindow()
			    .getGame()
			    .getScore()) {
			String message = awardsSelection.getDifference()
				+ " points will be lost.";

			// Confirm you want to continue
			if (JOptionPane.showConfirmDialog(null,
				message) == JOptionPane.YES_OPTION)
			    showConfirmation();

		    } else {
			showConfirmation();
		    }
		}
	    });
	    btnConfirm.setEnabled(false);
	    btnConfirm.setToolTipText("To confirm the prizes selected");
	    btnConfirm.setMnemonic('C');
	    btnConfirm.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnConfirm.setForeground(Color.BLACK);
	    btnConfirm.setBackground(new Color(51, 255, 102));
	}
	return btnConfirm;
    }

    private JButton getBtnCancel() {
	if (btnCancel == null) {
	    btnCancel = new JButton("Cancel");
	    btnCancel.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    dispose();
		}
	    });
	    btnCancel.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnCancel.setForeground(Color.BLACK);
	    btnCancel.setBackground(new Color(255, 0, 51));
	}
	return btnCancel;
    }

    private void showConfirmation() {
	confirmation = new Confirmation(this);
	confirmation.setLocationRelativeTo(this);
	confirmation.setModal(true);
	confirmation.setVisible(true);
    }

    private JPanel getPnW1() {
	if (pnW1 == null) {
	    pnW1 = new JPanel();
	    pnW1.setBackground(Color.WHITE);
	    pnW1.add(getBtnAccesories());
	}
	return pnW1;
    }

    private JPanel getPnW2() {
	if (pnW2 == null) {
	    pnW2 = new JPanel();
	    pnW2.setBackground(Color.WHITE);
	    pnW2.add(getBtnConsole());
	}
	return pnW2;
    }

    private JPanel getPnW3() {
	if (pnW3 == null) {
	    pnW3 = new JPanel();
	    pnW3.setBackground(Color.WHITE);
	    pnW3.add(getBtnVideogame());
	}
	return pnW3;
    }

    private JPanel getPnW4() {
	if (pnW4 == null) {
	    pnW4 = new JPanel();
	    pnW4.setBackground(Color.WHITE);
	    pnW4.add(getBtnAffordable());
	}
	return pnW4;
    }

    private JButton getBtnAccesories() {
	if (btnAccesories == null) {
	    btnAccesories = new JButton("Accesories");
	    btnAccesories.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnAccesories.setVerticalTextPosition(SwingConstants.BOTTOM);
	    btnAccesories.setIcon(new ImageIcon(
		    Prizes.class.getResource("/img/accesories.png")));
	    btnAccesories.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    cbAwards.setModel(new DefaultComboBoxModel<Award>(
			    filterComboBox("Accessories")));
		    cbAwards.setSelectedIndex(0);

		    // CORRECTION: icon needs to be visible
		    getLblImageArticles().setVisible(true);
		}
	    });
	    btnAccesories.setMnemonic('c');
	    btnAccesories.setToolTipText(
		    "To select the prizes that are of type accesories");
	    btnAccesories
		    .setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnAccesories.setForeground(Color.BLACK);
	    btnAccesories.setBackground(new Color(0, 153, 153));
	}
	return btnAccesories;
    }

    private JButton getBtnConsole() {
	if (btnConsole == null) {
	    btnConsole = new JButton("Console");
	    btnConsole.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    cbAwards.setModel(new DefaultComboBoxModel<Award>(
			    filterComboBox("Consoles")));
		    cbAwards.setSelectedIndex(0);
		    getLblImageArticles().setVisible(true);
		}
	    });
	    btnConsole.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnConsole.setVerticalTextPosition(SwingConstants.BOTTOM);
	    btnConsole.setIcon(new ImageIcon(
		    Prizes.class.getResource("/img/console.png")));
	    btnConsole.setMnemonic('s');
	    btnConsole.setToolTipText(
		    "To select the prizes that are of type console");
	    btnConsole.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnConsole.setForeground(Color.BLACK);
	    btnConsole.setBackground(new Color(0, 153, 153));
	}
	return btnConsole;
    }

    private JButton getBtnVideogame() {
	if (btnVideogame == null) {
	    btnVideogame = new JButton("Videogame");
	    btnVideogame.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    cbAwards.setModel(new DefaultComboBoxModel<Award>(
			    filterComboBox("Videogames")));
		    cbAwards.setSelectedIndex(0);
		    getLblImageArticles().setVisible(true);
		}
	    });
	    btnVideogame.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnVideogame.setVerticalTextPosition(SwingConstants.BOTTOM);
	    btnVideogame.setIcon(new ImageIcon(
		    Prizes.class.getResource("/img/videogame.png")));
	    btnVideogame.setMnemonic('V');
	    btnVideogame.setToolTipText(
		    "To select the prizes that are of type video game");
	    btnVideogame.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnVideogame.setForeground(Color.BLACK);
	    btnVideogame.setBackground(new Color(0, 153, 153));
	}
	return btnVideogame;
    }

    private JButton getBtnAffordable() {
	if (btnAffordable == null) {
	    btnAffordable = new JButton("Affordable");
	    btnAffordable.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    // Check it is not empty at first to disable the add button
		    checkCanAfford(filterAffordable().length);

		    cbAwards.setModel(new DefaultComboBoxModel<Award>(
			    filterAffordable()));
		    if (filterAffordable().length > 0)
			cbAwards.setSelectedIndex(0);
		}
	    });
	    btnAffordable.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnAffordable.setVerticalTextPosition(SwingConstants.BOTTOM);
	    btnAffordable.setIcon(new ImageIcon(
		    Prizes.class.getResource("/img/affordable.png")));
	    btnAffordable.setMnemonic('f');
	    btnAffordable
		    .setToolTipText("To select the prizes that are affordable");
	    btnAffordable
		    .setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnAffordable.setForeground(Color.BLACK);
	    btnAffordable.setBackground(new Color(0, 153, 153));
	}

	return btnAffordable;
    }

    private Award[] filterComboBox(String type) {
	List<Award> filtered = new LinkedList<>();

	for (Award award : awardsSelection.getAwards())
	    if (award.getType().equals(type))
		filtered.add(award);

	// CORRECTION: if the size of the affordable items is 0, disable add
	// button
	checkCanAfford(filterAffordable().length);

	return filtered.toArray(new Award[filtered.size()]);
    }

    private Award[] filterAffordable() {
	List<Award> filtered = new LinkedList<>();

	for (Award award : awardsSelection.getAwards())
	    if (award.getPoints() <= getStatusPage().getGameBoard()
		    .getMainWindow()
		    .getGame()
		    .getScore() - awardsSelection.getOrderTotalPoints())
		filtered.add(award);

	// CORRECTION: If we cannot afford nothing else the list is empty
	if (filtered.size() == 0) {
	    filtered = new LinkedList<>();
	    getLblImageArticles().setVisible(false);
	    btnRemove.setEnabled(false);
	}

	checkCanAfford(filtered.size());

	return filtered.toArray(new Award[filtered.size()]);
    }

    // CORRECTION: Block the add button if we CANNOT afford nothing else
    private void checkCanAfford(int filteredSize) {
	if (filteredSize == 0)
	    btnAdd.setEnabled(false);
	else
	    btnAdd.setEnabled(true);
    }

    private JPanel getPnEast() {
	if (pnEast == null) {
	    pnEast = new JPanel();
	    pnEast.setBackground(Color.WHITE);
	    pnEast.setLayout(new GridLayout(4, 0, 0, 0));
	    pnEast.add(getPnE1());
	    pnEast.add(getPnE2());
	    pnEast.add(getPnE3());
	    pnEast.add(getPnE4());
	}
	return pnEast;
    }

    private JPanel getPnE2() {
	if (pnE2 == null) {
	    pnE2 = new JPanel();
	    pnE2.setBackground(Color.WHITE);
	    pnE2.add(getLblPoints());
	    pnE2.add(getTxtPoints());
	}
	return pnE2;
    }

    private JPanel getPnE3() {
	if (pnE3 == null) {
	    pnE3 = new JPanel();
	    pnE3.setLayout(new BorderLayout(0, 0));
	    pnE3.add(getPnE21(), BorderLayout.NORTH);
	    pnE3.add(getPnR22());
	}
	return pnE3;
    }

    private JPanel getPnE4() {
	if (pnE4 == null) {
	    pnE4 = new JPanel();
	    pnE4.setBackground(Color.WHITE);
	    pnE4.add(getLblAmount());
	    pnE4.add(getTxtAmount());
	}
	return pnE4;
    }

    private JLabel getLblPoints() {
	if (lblPoints == null) {
	    lblPoints = new JLabel("Points:");
	    lblPoints.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblPoints.setDisplayedMnemonic('P');
	    lblPoints.setLabelFor(getTxtPoints());
	}
	return lblPoints;
    }

    private JTextField getTxtPoints() {
	if (txtPoints == null) {
	    txtPoints = new JTextField();
	    txtPoints.setText(String.valueOf(getStatusPage().getGameBoard()
		    .getMainWindow()
		    .getGame()
		    .getScore()));
	    txtPoints.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    txtPoints.setEditable(false);
	    txtPoints.setToolTipText("Shows the score of the user");
	    txtPoints.setColumns(10);
	}
	return txtPoints;
    }

    private JPanel getPnE21() {
	if (pnE21 == null) {
	    pnE21 = new JPanel();
	    pnE21.setBackground(Color.WHITE);
	    FlowLayout flowLayout = (FlowLayout) pnE21.getLayout();
	    flowLayout.setAlignment(FlowLayout.LEFT);
	    pnE21.add(getLblUnits());
	}
	return pnE21;
    }

    private JPanel getPnR22() {
	if (pnR22 == null) {
	    pnR22 = new JPanel();
	    pnR22.setBackground(Color.WHITE);
	    pnR22.add(getSpUnits());
	    pnR22.add(getBtnAdd());
	    pnR22.add(getBtnRemove());
	}
	return pnR22;
    }

    private JLabel getLblUnits() {
	if (lblUnits == null) {
	    lblUnits = new JLabel("Units:");
	    lblUnits.setLabelFor(getSpUnits());
	    lblUnits.setVerticalAlignment(SwingConstants.BOTTOM);
	    lblUnits.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblUnits.setDisplayedMnemonic('U');
	}
	return lblUnits;
    }

    private JLabel getLblAmount() {
	if (lblAmount == null) {
	    lblAmount = new JLabel("Amount:");
	    lblAmount.setDisplayedMnemonic('t');
	    lblAmount.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblAmount.setLabelFor(getTxtAmount());
	}
	return lblAmount;
    }

    private JTextField getTxtAmount() {
	if (txtAmount == null) {
	    txtAmount = new JTextField();
	    txtAmount.setEditable(false);
	    txtAmount.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    txtAmount.setToolTipText("Shows the amount the user is spending");
	    txtAmount.setColumns(10);
	}
	return txtAmount;
    }

    private JSpinner getSpUnits() {
	if (spUnits == null) {
	    spUnits = new JSpinner();
	    spUnits.setToolTipText("Units per award");
	    spUnits.setModel(new SpinnerNumberModel(Integer.valueOf(1),
		    Integer.valueOf(1), null, Integer.valueOf(1)));
	    spUnits.setBounds(566, 237, 56, 34);
	}
	return spUnits;
    }

    private JButton getBtnAdd() {
	if (btnAdd == null) {
	    btnAdd = new JButton("Add");
	    btnAdd.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    Award selectedItem = (Award) cbAwards.getSelectedItem();
		    Integer units = (Integer) spUnits.getValue();
		    awardsSelection.addToOrder(selectedItem, units);
		    // Not possible to add more awards
		    if (!awardsSelection.isSuccessful()) {
			JOptionPane.showMessageDialog(null,
				"You do NOT have enough points.", "Warning",
				JOptionPane.WARNING_MESSAGE);
		    }

		    txtAmount.setText(String.valueOf(
			    awardsSelection.getOrderTotalPoints()) + " points");
		    btnConfirm.setEnabled(true);
		    btnRemove.setEnabled(true);
		    spUnits.setValue(1);
		    getCbAwards().setSelectedIndex(0);
		}
	    });
	    btnAdd.setMnemonic('A');
	    btnAdd.setToolTipText("To add an article");
	    btnAdd.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnAdd.setForeground(Color.BLACK);
	    btnAdd.setBackground(new Color(51, 255, 102));
	}
	return btnAdd;
    }

    private JButton getBtnRemove() {
	if (btnRemove == null) {
	    btnRemove = new JButton("Remove");
	    btnRemove.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    Award selectedItem = (Award) cbAwards.getSelectedItem();
		    Integer units = (Integer) spUnits.getValue();

		    awardsSelection.removeFromOrder(selectedItem, units);
		    txtAmount.setText(String.valueOf(
			    awardsSelection.getOrderTotalPoints()) + " points");

		    if (txtAmount.getText().equals("0.00 €"))
			btnConfirm.setEnabled(false);

		    if (awardsSelection.getOrderTotalPoints() == 0)
			btnConfirm.setEnabled(false);

		    // Spinner sets back to 1 after removing an item
		    spUnits.setValue(1);

		    if (awardsSelection.getOrderTotalPoints() <= 0)
			btnRemove.setEnabled(false);

		    checkCanAfford(filterAffordable().length);
		}
	    });
	    btnRemove.setEnabled(false);
	    btnRemove.setToolTipText("To remove an article");
	    btnRemove.setMnemonic('R');
	    btnRemove.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnRemove.setForeground(Color.BLACK);
	    btnRemove.setBackground(new Color(255, 0, 51));
	}
	return btnRemove;
    }

    private JPanel getPnCenterNorth() {
	if (pnCenterNorth == null) {
	    pnCenterNorth = new JPanel();
	    pnCenterNorth.setBackground(Color.WHITE);
	    pnCenterNorth.setLayout(new GridLayout(0, 1, 0, 0));
	    pnCenterNorth.add(getLblPrizes());
	}
	return pnCenterNorth;
    }

    private JPanel getPnCenterCenter() {
	if (pnCenterCenter == null) {
	    pnCenterCenter = new JPanel();
	    pnCenterCenter.setBackground(Color.WHITE);
	    pnCenterCenter.setLayout(new GridLayout(2, 0, 0, 0));
	    pnCenterCenter.add(getPnC1());
	    pnCenterCenter.add(getPanel_1_2());
	}
	return pnCenterCenter;
    }

    private JLabel getLblPrizes() {
	if (lblPrizes == null) {
	    lblPrizes = new JLabel("PRIZES");
	    lblPrizes.setHorizontalAlignment(SwingConstants.LEFT);
	    lblPrizes.setHorizontalTextPosition(SwingConstants.LEFT);
	    lblPrizes.setForeground(new Color(148, 0, 211));
	    lblPrizes.setFont(new Font("Javanese Text", Font.PLAIN, 70));
	}
	return lblPrizes;
    }

    private JPanel getPnC1() {
	if (pnC1 == null) {
	    pnC1 = new JPanel();
	    pnC1.setBackground(Color.WHITE);
	    pnC1.setLayout(new GridLayout(0, 1, 0, 0));
	    pnC1.add(getPnC11());
	    pnC1.add(getPnC12());
	}
	return pnC1;
    }

    private JPanel getPanel_1_2() {
	if (pnC2 == null) {
	    pnC2 = new JPanel();
	    pnC2.setBackground(Color.WHITE);
	    pnC2.setLayout(new GridLayout(0, 1, 0, 0));
	    pnC2.add(getLblImageArticles());
	}
	return pnC2;
    }

    private JPanel getPnC11() {
	if (pnC11 == null) {
	    pnC11 = new JPanel();
	    pnC11.setBackground(Color.WHITE);
	    pnC11.setLayout(new GridLayout(0, 1, 0, 0));
	    pnC11.add(getLblArticles());
	}
	return pnC11;
    }

    private JPanel getPnC12() {
	if (pnC12 == null) {
	    pnC12 = new JPanel();
	    pnC12.setBackground(Color.WHITE);
	    FlowLayout flowLayout = (FlowLayout) pnC12.getLayout();
	    flowLayout.setAlignment(FlowLayout.LEFT);
	    pnC12.add(getCbAwards());
	}
	return pnC12;
    }

    private JLabel getLblArticles() {
	if (lblArticles == null) {
	    lblArticles = new JLabel("Articles:");
	    lblArticles.setHorizontalTextPosition(SwingConstants.LEFT);
	    lblArticles.setVerticalTextPosition(SwingConstants.BOTTOM);
	    lblArticles.setVerticalAlignment(SwingConstants.BOTTOM);
	    lblArticles.setLabelFor(getCbAwards());
	    lblArticles.setDisplayedMnemonic('l');
	    lblArticles.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    lblArticles.setHorizontalAlignment(SwingConstants.LEFT);
	}
	return lblArticles;
    }

    private JComboBox<Award> getCbAwards() {
	if (cbAwards == null) {
	    cbAwards = new JComboBox<Award>();
	    cbAwards.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    showAwardPicture();

		    // When the product is not in our order, the Remove button
		    // will be disabled, otherwise it will be able
		    if (!awardsSelection.getOrder()
			    .getAwardsNameList()
			    .contains(((Award) cbAwards.getSelectedItem())
				    .getName()))
			btnRemove.setEnabled(false);
		    else
			btnRemove.setEnabled(true);
		}
	    });
	    cbAwards.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    cbAwards.setToolTipText("Collection of awards to choose");

	    // In order to fill the combo box, loaded from the text file
	    cbAwards.setModel(new DefaultComboBoxModel<Award>(
		    this.awardsSelection.getAwards()));
	    showAwardPicture();
	}
	return cbAwards;
    }

    private void showAwardPicture() {
	// We must use the getter because otherwise the spinner wont be
	// initialized yet and will cause a NullPointerException
	getSpUnits().setValue(1);

	Award product = (Award) cbAwards.getSelectedItem();
	String code = product.getCode();
	String pathPicture = "/img/" + code + ".png";

	// Again we use the getter, if not it won´t get the combobox initialized
	getLblImageArticles().setIcon(
		new ImageIcon(MainWindow.class.getResource(pathPicture)));
    }

    private JLabel getLblImageArticles() {
	if (lblImageArticles == null) {
	    lblImageArticles = new JLabel("");
	    lblImageArticles.setHorizontalTextPosition(SwingConstants.LEFT);
	    lblImageArticles.setHorizontalAlignment(SwingConstants.LEFT);
	}
	return lblImageArticles;
    }

    private JPanel getPnE1() {
	if (pnE1 == null) {
	    pnE1 = new JPanel();
	    pnE1.setBackground(Color.WHITE);
	    pnE1.setLayout(new GridLayout(0, 1, 0, 0));
	    pnE1.add(getBtnOrder());
	    pnE1.add(getSpOrder());
	}
	return pnE1;
    }

    private JButton getBtnOrder() {
	if (btnOrder == null) {
	    btnOrder = new JButton("Order");
	    btnOrder.setHorizontalTextPosition(SwingConstants.CENTER);
	    btnOrder.setToolTipText(
		    "Shows your order with the corresponding awards");
	    btnOrder.setMnemonic('O');
	    btnOrder.setFont(new Font("Palatino Linotype", Font.PLAIN, 14));
	    btnOrder.setForeground(Color.BLACK);
	    btnOrder.setBackground(new Color(0, 153, 153));

	    btnOrder.addMouseListener(new MouseAdapter() {
		@Override
		public void mousePressed(MouseEvent e) {
		    spOrder.setVisible(true);
		    txtAreaOrder.setText(awardsSelection.getOrder().toString());
		}

		@Override
		public void mouseReleased(MouseEvent e) {
		    spOrder.setVisible(false);
		}
	    });

	}
	return btnOrder;
    }

    private JScrollPane getSpOrder() {
	if (spOrder == null) {
	    spOrder = new JScrollPane();
	    spOrder.setViewportView(getTxtAreaOrder());
	    spOrder.setVisible(false);
	}
	return spOrder;
    }

    private JTextArea getTxtAreaOrder() {
	if (txtAreaOrder == null) {
	    txtAreaOrder = new JTextArea();
	    txtAreaOrder.setEditable(false);
	}
	return txtAreaOrder;
    }
}
