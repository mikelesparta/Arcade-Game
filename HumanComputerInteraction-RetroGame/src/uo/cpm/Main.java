package uo.cpm;

import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.UIManager;

import uo.cpm.model.Game;
import uo.cpm.ui.MainWindow;

public class Main {

    private static int debugMode;

    public static void main(String[] args) {
	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    // To change in JFrame and JDialog the look and feel
		    JDialog.setDefaultLookAndFeelDecorated(true);
		    JFrame.setDefaultLookAndFeelDecorated(true);

		    UIManager.setLookAndFeel(
			    "javax.swing.plaf.nimbus.NimbusLookAndFeel");

		    // DEBUG0 or DEBUG1 or DEBUG2
		    debugMode = Game.DEBUG1;
		    MainWindow frame = new MainWindow(debugMode);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
}
