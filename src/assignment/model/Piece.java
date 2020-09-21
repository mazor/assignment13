package assignment.model;


import javax.swing.JLabel; 
import java.util.Observable; 

/**
 * 
 * @author Sharif, Mazor
 * Used to represent the pieces that can move 
 * Extends Observable so that changes are seen by observers
 */
public class Piece extends Observable {
	
	/* represents the position of the Piece:
	 * 0 represents the right (east) bank, 
	 * 1 is the side of the river next to right (east) bank, 
	 * 2 is the side of the river next to left (west) bank, 
	 * 3 is the left (west) bank
	 */
	private int position; 
	
	
	// image of the piece
	private JLabel imageLabel;
	
	/**
	 * The following constructor takes 1 parameter and assigns values for the position and imageLabel
	 * @param imageLabel
	 */
	public Piece (JLabel imageLabel){
		
		//position is assigned initially as 0 as all Pieces will initially be on the right (east) bank 
		position = 0;
		this.imageLabel = imageLabel;
	}
	
	/**
	 * The following constructor takes 2 parameters and assigns values for the position and imageLabel
	 * @param imageLabel
	 * @param position
	 */
	public Piece (JLabel imageLabel, int position){
		
		//position is assigned as the supplied position
		this.position = position;
		//imageLabel is assigned as the imageLabel supplied
		this.imageLabel = imageLabel;
	}
	
	/**
	 * This method returns the value of the position of the Piece
	 * 0 represents the right (east) bank, 1 is the side of the river next to right (east) bank, 
	 * 2 is the side of the river next to left (west) bank, 3 is the left (west) bank
	 * @return position
	 */
	public int getPosition(){
		return position;
	}

	/**
	 * This method is used to 'move' the Piece right (decrease its position) if not at the lowest position value already
	 */
	public void movePositionRight(){
		
		//if position is greater than zero 
		//as if position is 0 then it is on the east bank and it cannot go any further right)
		if (position>0){
			//decrease position by one (as the Piece will have moved a single position to the right)
			position--;
			
			//call setChanged and notifyObservers as this class is Observable and a change has been made
			setChanged();
			notifyObservers();
		}
		
	}
	
	/**
	 * This method is used to 'move' the Piece left (increase its position) if not at the highest position value already
	 */
	public void movePositionLeft(){
		
		//if position is less than 3
		//as if position is 3 then it is on the west bank and it cannot go any further left)
		if (position<3){
			//increase position by one (as the Piece will have moved a single position to the left)
			position++;
			//call setChanged and notifyObservers as this class is Observable and a change has been made
			setChanged();
			notifyObservers();
		}
	}

	/**
	 * the following method returns the value for imageLabel (the image that represents the Piece)
	 * @return imageLabel
	 */
	public JLabel getImageLabel(){
		return imageLabel;
	}
}
