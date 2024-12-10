package uo.cpm.p6;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p6.rules.Game.Level;
import uo.cpm.p6.service.SpaceInvaders;
import uo.cpm.p6.ui.MainWindow;

public class Main {

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
	final SpaceInvaders game = new SpaceInvaders(Level.INTERMIDIATE);
	EventQueue.invokeLater(new Runnable() {
	    public void run() {
		try {
		    // We use this class as text filed with key = value
		    // It is used for configurations
		    Properties props = new Properties();
		    props.put("logoString", "");

		    // Change the look and field of our application with the fully qualified name
		    HiFiLookAndFeel.setCurrentTheme(props);
		    UIManager.setLookAndFeel("com.jtattoo.plaf.hifi.HiFiLookAndFeel");

		    MainWindow frame = new MainWindow(game);

		    // Center the window and make it visible
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }
}
