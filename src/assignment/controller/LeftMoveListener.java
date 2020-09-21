package assignment.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import assignment.model.Boat; 
import assignment.model.Piece; 

/**
 * 
 * @author Sharif, Mazor
 * ActionListener for moving pieces to the left
 * on button click, performs checks and moves piece left if
 * that is valid
 *
 */
public class LeftMoveListener implements ActionListener { 
	
	// references piece being moved, and the boat for
	// checking move validity
	private Piece piece; 
	private Boat boat; 
	
	/**
	 * This constructor takes two parameters and assigns the values for piece and boat
	 * @param piece
	 * @param boat
	 */
	public LeftMoveListener(Piece piece, Boat boat){
		
		//value for piece is assigned as the first input
		this.piece=piece;
		//value for boat is assigned as the second input
		this.boat=boat;
	}
	/**
	 * This method moves the piece and checks if it should be added to the boat. 
	 * If so it tries. If it fails then the passenger will move back to where 
	 * they were before this method started
	 */
	public void actionPerformed(ActionEvent e) {
		//if boat is null then
		if (boat==null){
			
			//the piece is cast as Boat as if the Piece has no possible Boat then it must be a Boat itself
			((Boat)piece).movePositionLeft();
			
		} else { //otherwise, (boat is not null)
			// if (the boat and piece are in the same position AND piece is at position 2) 
			// OR (boat is in position 1 AND piece is in position 0) then
			 if (((boat.getPosition()==piece.getPosition())&&(piece.getPosition()==2))||
					 ((boat.getPosition()==1)&&(piece.getPosition()==0))){
				 //piece is moved left by calling movePositionLeft
				 piece.movePositionLeft();
				// if the piece and boat are in the same position 
				 // (this check happens after the piece has moved so the previous if statement does not cover this) then
				 if (piece.getPosition()==boat.getPosition()){
					// addPassenger is called from boat with the parameters of 
					 // piece and the String value "Right" (as if the piece is not able to be added then 
					 //it will have to move left again)
					boat.addPassenger(piece, "Right");
				 } else { //otherwise
					 //removePassenger is called from boat with parameter piece as if they are not in 
					 // the same location anymore then it means piece used to be on the boat and is now not
					boat.removePassenger(piece);
				 }
			}
		}
	}
}
