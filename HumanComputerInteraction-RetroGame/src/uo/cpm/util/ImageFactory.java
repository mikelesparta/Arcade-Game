package uo.cpm.util;

import javax.swing.ImageIcon;

public class ImageFactory {

    public static ImageIcon loadImage(String fqImageName) {
	return new ImageIcon(ImageFactory.class.getResource(fqImageName));
    }
}
