package uo.cpm.p8;

import java.awt.EventQueue;
import java.util.Properties;

import javax.swing.UIManager;

import com.jtattoo.plaf.hifi.HiFiLookAndFeel;

import uo.cpm.p8.player.MusicPlayer;
import uo.cpm.p8.ui.MainWindow;

public class Main {

    public static void main(String[] args) {

	EventQueue.invokeLater(new Runnable() {
	    @Override
	    public void run() {
		try {
		    final MusicPlayer mP = new MusicPlayer();

		    Properties props = new Properties();
		    props.put("logoString", "");

		    // Change the look and field of our application with the
		    // fully qualified name
		    HiFiLookAndFeel.setCurrentTheme(props);
		    UIManager.setLookAndFeel(
			    "com.jtattoo.plaf.hifi.HiFiLookAndFeel");

		    MainWindow frame = new MainWindow(mP);
		    frame.setLocationRelativeTo(null);
		    frame.setVisible(true);
		} catch (Exception e) {
		    e.printStackTrace();
		}
	    }
	});
    }

}
