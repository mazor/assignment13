package assignment.view;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;


/**
 * 
 * @author Sharif, Mazor
 * This extends JPanel
 * 
 * This is used to paint an image background by overriding the paintComponent
 * of JPanel.
 */
public class PicturePanel extends JPanel {
	
	private static final long serialVersionUID = 3924373739687176422L;
	
	// the image to be used as the background image
	private BufferedImage image;
	
	/**
	 * constructor accepts a String as input. This is the path to the image file
	 * @param path
	 */
	public PicturePanel(String path) {
		try {
			image = ImageIO.read(new File(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Overridden paintComponent which draws an image as a background instead of the existing
	 * text/color paintComponent method
	 */
	@Override 
	protected void paintComponent(Graphics g) {	// cannot override if private
		super.paintComponent(g);
		g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
	}

	
}
