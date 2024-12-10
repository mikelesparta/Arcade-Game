package uo.cpm.p8.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.net.URL;

import javax.help.HelpBroker;
import javax.help.HelpSet;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileNameExtensionFilter;

import uo.cpm.p8.player.MusicPlayer;

public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JPanel contentPane;

    private JMenuBar menuBar;
    private JMenu mnFile, mnPlay, mnOptions, mnHelp;
    private JSeparator separator, separator_1;
    private JMenuItem mntmOpen, mntmExit, mntmContents, mntmAbout;

    private JPanel pnNorth, pnVol, pnCenter, pnLibrary, pnLibraryButtons,
	    pnPlay, pnPlayListButtons;
    private JScrollPane spLibrary, spLibaryPlay;

    private JLabel lblLogo, lblVol, lblLibrary, lblPlayList;
    private JSlider slVolume;
    private JTextField txtVol;
    private JButton btnAdd, btnDelete, btnRew, btnPlay, btnStop, btnFor, btnDel,
	    btnClear, btnClearList2, btnRandom;

    private JList list1, list2;
    private DefaultListModel modelList1 = null, modelList2 = null;

    private MusicPlayer mP = null;

    private JFileChooser selector = null;

    public MainWindow(MusicPlayer mP) {
//	addComponentListener(new ComponentAdapter() {
//	    @Override
//	    public void componentResized(ComponentEvent e) {
//		System.out.println(((JFrame) e.getSource()).getSize());
//	    }
//	});
	setTitle("Music Player");
	setIconImage(Toolkit.getDefaultToolkit()
		.getImage(MainWindow.class.getResource("/img/logoTitulo.png")));
	this.mP = mP;
	setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	setBounds(100, 100, 1036, 678);
	setJMenuBar(getMenuBar_1());
	contentPane = new JPanel();
	contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

	setContentPane(contentPane);
	contentPane.setLayout(new BorderLayout(0, 0));
	contentPane.add(getPnNorth(), BorderLayout.NORTH);
	contentPane.add(getPnCenter(), BorderLayout.CENTER);

	// Set the minimum size of the frame, so that we can see all the
	// components
	this.setMinimumSize(new Dimension(573, 328));
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

	// We associate to list1 add
	hb.enableHelp(getList1(), "add", hs);
    }

    public MusicPlayer getMP() {
	return mP;
    }

    private JFileChooser getSelector() {
	if (selector == null) {
	    selector = new JFileChooser();
	    selector.setMultiSelectionEnabled(true);

	    // In order to go to the desktop instead of Documents which is the
	    // default
//	    String desktopPath = System.getProperty("user.home") + "/Desktop";
//	    selector.setCurrentDirectory(new File(desktopPath));

	    selector.setCurrentDirectory(new File(
		    "C:\\Users\\mikel\\Documents\\UNIOVI\\2 SEGUNDO\\CPM - "
			    + "Comunicacion Persona Maquina\\LAB\\MP3\\Musica MP3 Player"));
	    // To create the filter for .mp3 extension
	    selector.setFileFilter(
		    new FileNameExtensionFilter("mp3 file", "mp3"));
	}

	return selector;
    }

    private JPanel getPnNorth() {
	if (pnNorth == null) {
	    pnNorth = new JPanel();
	    pnNorth.setLayout(new GridLayout(1, 0, 0, 0));
	    pnNorth.add(getLblLogo());
	    pnNorth.add(getSlVolume());
	    pnNorth.add(getPnVol());
	}
	return pnNorth;
    }

    private JLabel getLblLogo() {
	if (lblLogo == null) {
	    lblLogo = new JLabel("");
	    lblLogo.setIcon(new ImageIcon(
		    MainWindow.class.getResource("/img/logo.png")));
	}
	return lblLogo;
    }

    private JSlider getSlVolume() {
	if (slVolume == null) {
	    slVolume = new JSlider();
	    slVolume.setPaintTicks(true);
	    slVolume.setPaintLabels(true);
	    slVolume.addChangeListener(new ChangeListener() {
		@Override
		public void stateChanged(ChangeEvent e) {
		    txtVol.setText(String.valueOf(slVolume.getValue()));
		    mP.setVolume(slVolume.getValue(), slVolume.getMaximum());
		}
	    });
	    slVolume.setMinorTickSpacing(10);
	    slVolume.setMajorTickSpacing(20);
	}
	return slVolume;
    }

    private JPanel getPnVol() {
	if (pnVol == null) {
	    pnVol = new JPanel();
	    pnVol.add(getLblVol());
	    pnVol.add(getTxtVol());
	}
	return pnVol;
    }

    private JLabel getLblVol() {
	if (lblVol == null) {
	    lblVol = new JLabel("Vol:");
	    lblVol.setForeground(new Color(255, 140, 0));
	    lblVol.setFont(new Font("Tahoma", Font.PLAIN, 40));
	    lblVol.setLabelFor(getTxtVol());
	}
	return lblVol;
    }

    private JTextField getTxtVol() {
	if (txtVol == null) {
	    txtVol = new JTextField();
	    txtVol.setFont(new Font("Tahoma", Font.PLAIN, 40));
	    txtVol.setText("50");
	    txtVol.setEditable(false);
	    txtVol.setColumns(2);
	}
	return txtVol;
    }

    private JPanel getPnCenter() {
	if (pnCenter == null) {
	    pnCenter = new JPanel();
	    pnCenter.setLayout(new GridLayout(1, 0, 0, 0));
	    pnCenter.add(getPnLibrary());
	    pnCenter.add(getPnPlay());
	}
	return pnCenter;
    }

    private JPanel getPnLibrary() {
	if (pnLibrary == null) {
	    pnLibrary = new JPanel();
	    pnLibrary.setBorder(new LineBorder(new Color(255, 140, 0), 3));
	    pnLibrary.setLayout(new BorderLayout(0, 0));
	    pnLibrary.add(getLblLibrary(), BorderLayout.NORTH);
	    pnLibrary.add(getPnLibraryButtons(), BorderLayout.SOUTH);
	    pnLibrary.add(getSpLibrary(), BorderLayout.CENTER);
	}
	return pnLibrary;
    }

    private JLabel getLblLibrary() {
	if (lblLibrary == null) {
	    lblLibrary = new JLabel("\u266A Library:");
	    lblLibrary.setForeground(new Color(255, 140, 0));
	    lblLibrary.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblLibrary.setDisplayedMnemonic('L');
	}
	return lblLibrary;
    }

    private JPanel getPnLibraryButtons() {
	if (pnLibraryButtons == null) {
	    pnLibraryButtons = new JPanel();
	    pnLibraryButtons.setLayout(new GridLayout(1, 0, 0, 0));
	    pnLibraryButtons.add(getBtnAdd());
	    pnLibraryButtons.add(getBtnDelete());
	    pnLibraryButtons.add(getBtnClear());
	}
	return pnLibraryButtons;
    }

    private JPanel getPnPlay() {
	if (pnPlay == null) {
	    pnPlay = new JPanel();
	    pnPlay.setBorder(new LineBorder(new Color(255, 140, 0), 3));
	    pnPlay.setLayout(new BorderLayout(0, 0));
	    pnPlay.add(getLblPlayList(), BorderLayout.NORTH);
	    pnPlay.add(getPnPlayListButtons(), BorderLayout.SOUTH);
	    pnPlay.add(getSpLibaryPlay(), BorderLayout.CENTER);
	}
	return pnPlay;
    }

    private JScrollPane getSpLibrary() {
	if (spLibrary == null) {
	    spLibrary = new JScrollPane();
	    spLibrary.setViewportView(getList1());
	}
	return spLibrary;
    }

    private JLabel getLblPlayList() {
	if (lblPlayList == null) {
	    lblPlayList = new JLabel("\u266A Play:");
	    lblPlayList.setForeground(new Color(255, 140, 0));
	    lblPlayList.setFont(new Font("Tahoma", Font.PLAIN, 15));
	    lblPlayList.setDisplayedMnemonic('y');
	}
	return lblPlayList;
    }

    private JPanel getPnPlayListButtons() {
	if (pnPlayListButtons == null) {
	    pnPlayListButtons = new JPanel();
	    pnPlayListButtons.setLayout(new GridLayout(1, 0, 0, 0));
	    pnPlayListButtons.add(getBtnRew());
	    pnPlayListButtons.add(getBtnPlay());
	    pnPlayListButtons.add(getBtnStop());
	    pnPlayListButtons.add(getBtnFor());
	    pnPlayListButtons.add(getBtnDel());
	    pnPlayListButtons.add(getBtnClearList2());
	    pnPlayListButtons.add(getBtnRandom());
	}
	return pnPlayListButtons;
    }

    private JButton getBtnAdd() {
	if (btnAdd == null) {
	    btnAdd = new JButton("Add to playlist");
	    btnAdd.setToolTipText("To add songs to the playlist");
	    btnAdd.setEnabled(false);
	    btnAdd.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    for (Object o : list1.getSelectedValuesList())
			if (!modelList2.contains(o))
			    modelList2.addElement(o);

		    btnRew.setEnabled(true);
		    btnPlay.setEnabled(true);
		    btnStop.setEnabled(true);
		    btnFor.setEnabled(true);
		    btnDel.setEnabled(true);
		    btnClearList2.setEnabled(true);
		    btnRandom.setEnabled(true);
		}
	    });
	    btnAdd.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnAdd.setMnemonic('A');
	}
	return btnAdd;
    }

    private JButton getBtnDelete() {
	if (btnDelete == null) {
	    btnDelete = new JButton("Delete");
	    btnDelete.setToolTipText("To delete songs from the library");
	    btnDelete.setEnabled(false);
	    btnDelete.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    for (Object o : list1.getSelectedValuesList())
			modelList1.removeElement(o);
		}
	    });
	    btnDelete.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnDelete.setMnemonic('D');
	}
	return btnDelete;
    }

    private JButton getBtnClear() {
	if (btnClear == null) {
	    btnClear = new JButton("Clear");
	    btnClear.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnClear.setMnemonic('C');
	    btnClear.setToolTipText("To clear songs from the library");
	    btnClear.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    modelList1.removeAllElements();
		    btnAdd.setEnabled(false);
		    btnDelete.setEnabled(false);
		    btnClear.setEnabled(false);
		}
	    });
	    btnClear.setEnabled(false);
	}
	return btnClear;
    }

    private JButton getBtnRew() {
	if (btnRew == null) {
	    btnRew = new JButton("\u25C4\u25C4");
	    btnRew.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnRew.setEnabled(false);
	    btnRew.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    int selectedInndex = list2.getSelectedIndex();

		    if (selectedInndex > 0) {
			selectedInndex--;
			list2.setSelectedIndex(selectedInndex);
			mP.play(((MyFile) list2.getSelectedValue()).getFile());
		    }
		}
	    });
	}
	return btnRew;
    }

    private JButton getBtnPlay() {
	if (btnPlay == null) {
	    btnPlay = new JButton("\u25BA");
	    btnPlay.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnPlay.setEnabled(false);
	    btnPlay.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    File selectedFile = ((MyFile) list2.getSelectedValue())
			    .getFile();

		    if (selectedFile != null) {
			mP.play(selectedFile);
		    }
		}
	    });
	}
	return btnPlay;
    }

    private JButton getBtnStop() {
	if (btnStop == null) {
	    btnStop = new JButton("\u2588");
	    btnStop.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnStop.setEnabled(false);
	    btnStop.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    mP.stop();
		}
	    });
	}
	return btnStop;
    }

    private JButton getBtnFor() {
	if (btnFor == null) {
	    btnFor = new JButton("\u25BA\u25BA");
	    btnFor.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnFor.setEnabled(false);
	    btnFor.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    int index = list2.getSelectedIndex() + 1;
		    if (index > modelList2.getSize())
			index = modelList2.getSize();
		    list2.setSelectedIndex(index);
		    mP.play(((MyFile) list2.getSelectedValue()).getFile());

//		    int selectedInndex = list2.getSelectedIndex();
//
//		    if (selectedInndex > 0
//			    && selectedInndex < modelList2.getSize()) {
//			selectedInndex++;
//			list2.setSelectedIndex(selectedInndex);
//			mP.play(((MyFile) list2.getSelectedValue()).getFile());
//		    }
		}
	    });
	}
	return btnFor;
    }

    private JButton getBtnDel() {
	if (btnDel == null) {
	    btnDel = new JButton("Del");
	    btnDel.setEnabled(false);
	    btnDel.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    for (Object o : list2.getSelectedValuesList())
			modelList2.removeElement(o);
		}
	    });
	    btnDel.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnDel.setMnemonic('l');
	}
	return btnDel;
    }

    private JButton getBtnClearList2() {
	if (btnClearList2 == null) {
	    btnClearList2 = new JButton("Clear");
	    btnClearList2.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    modelList2.removeAllElements();
		    btnRew.setEnabled(false);
		    btnPlay.setEnabled(false);
		    btnStop.setEnabled(false);
		    btnFor.setEnabled(false);
		    btnDel.setEnabled(false);
		    btnClearList2.setEnabled(false);
		    btnRandom.setEnabled(false);
		}
	    });
	    btnClearList2.setMnemonic('e');
	    btnClearList2.setToolTipText("To clear songs from the library");
	    btnClearList2.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnClearList2.setEnabled(false);
	}
	return btnClearList2;
    }

    private JButton getBtnRandom() {
	if (btnRandom == null) {
	    btnRandom = new JButton("Random");
	    btnRandom.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
//		    int randomIndex = (int) (Math.random()
//			    * list2.getSize().getHeight());
//		    list2.setSelectedIndex(randomIndex);

		    File selectedFile = ((MyFile) list2.getSelectedValue())
			    .getFile();

		    if (selectedFile != null) {
			mP.play(selectedFile);
		    }
		}
	    });
	    btnRandom.setEnabled(false);
	    btnRandom.setFont(new Font("Dialog", Font.PLAIN, 13));
	    btnRandom.setMnemonic('R');
	}
	return btnRandom;
    }

    private JList getList1() {
	if (list1 == null) {
	    list1 = new JList();

	    // Create model object
	    modelList1 = new DefaultListModel();
	    list1.setModel(modelList1);
	}
	return list1;
    }

    private JList getList2() {
	if (list2 == null) {
	    list2 = new JList();
	    list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    // Create model object
	    modelList2 = new DefaultListModel();
	    list2.setModel(modelList2);
	}
	return list2;
    }

    private JScrollPane getSpLibaryPlay() {
	if (spLibaryPlay == null) {
	    spLibaryPlay = new JScrollPane();
	    spLibaryPlay.setViewportView(getList2());
	}
	return spLibaryPlay;
    }

    private JMenuBar getMenuBar_1() {
	if (menuBar == null) {
	    menuBar = new JMenuBar();
	    menuBar.add(getMnFile());
	    menuBar.add(getMnPlay());
	    menuBar.add(getMnOptions());
	    menuBar.add(getMnHelp());
	}
	return menuBar;
    }

    private JMenu getMnFile() {
	if (mnFile == null) {
	    mnFile = new JMenu("File");
	    mnFile.setMnemonic('F');
	    mnFile.add(getMntmOpen());
	    mnFile.add(getSeparator());
	    mnFile.add(getMntmExit());
	}
	return mnFile;
    }

    private JMenu getMnPlay() {
	if (mnPlay == null) {
	    mnPlay = new JMenu("Play");
	    mnPlay.setMnemonic('P');
	}
	return mnPlay;
    }

    private JMenu getMnOptions() {
	if (mnOptions == null) {
	    mnOptions = new JMenu("Options");
	    mnOptions.setMnemonic('O');
	}
	return mnOptions;
    }

    private JMenu getMnHelp() {
	if (mnHelp == null) {
	    mnHelp = new JMenu("Help");
	    mnHelp.setMnemonic('H');
	    mnHelp.add(getMntmContents());
	    mnHelp.add(getSeparator_1());
	    mnHelp.add(getMntmAbout());
	}
	return mnHelp;
    }

    private JMenuItem getMntmOpen() {
	if (mntmOpen == null) {
	    mntmOpen = new JMenuItem("Open");
	    mntmOpen.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    // Opening JFileChooser and centers it to the main window
		    Integer response = getSelector().showOpenDialog(rootPane);

		    if (response == JFileChooser.APPROVE_OPTION) {
			for (File file : getSelector().getSelectedFiles())
			    // No repeated elements
			    if (!modelList1.contains(new MyFile(file)))
				modelList1.addElement(new MyFile(file));

			btnAdd.setEnabled(true);
			btnDelete.setEnabled(true);
			btnClear.setEnabled(true);
		    }
		}
	    });
	    mntmOpen.setMnemonic('O');
	}
	return mntmOpen;
    }

    private JSeparator getSeparator() {
	if (separator == null) {
	    separator = new JSeparator();
	}
	return separator;
    }

    private JMenuItem getMntmExit() {
	if (mntmExit == null) {
	    mntmExit = new JMenuItem("Exit");
	    mntmExit.setMnemonic('E');
	}
	return mntmExit;
    }

    private JMenuItem getMntmContents() {
	if (mntmContents == null) {
	    mntmContents = new JMenuItem("Contents");
	    mntmContents.setMnemonic('C');
	}
	return mntmContents;
    }

    private JSeparator getSeparator_1() {
	if (separator_1 == null) {
	    separator_1 = new JSeparator();
	}
	return separator_1;
    }

    private JMenuItem getMntmAbout() {
	if (mntmAbout == null) {
	    mntmAbout = new JMenuItem("About");
	    mntmAbout.addActionListener(new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent e) {
		    JOptionPane.showMessageDialog(null,
			    "CPM MP3 PLAYER - MIKEL FERNANDEZ ESPARTA");
		}
	    });
	    mntmAbout.setMnemonic('b');
	}
	return mntmAbout;
    }

}
