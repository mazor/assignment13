package assignment.main;


import assignment.view.GameFrame;

/**
 * 
 * @author Sharif, Sharon
 * This is the driver class, here the view is instantiated and set to visible
 * Has no methods or fields other than static main
 *
 */
public class Main {

	public static void main (String[] args){
		
		// instance of GameFrame
		GameFrame f = new GameFrame();
		
		// set the Frame to visible, otherwise no window will be drawn
		f.setVisible(true);
	
	}
}
