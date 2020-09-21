package assignment.controller; 

import java.awt.event.ActionEvent; 
import assignment.model.Boat; 

/**
 * 
 * @author Sharif, Mazor
 * Used to move the Boat piece left
 * Extends the LeftMoveListener, which is used to move pieces to the left
 */
public class BoatMoveLeftListener extends LeftMoveListener {
	
	// Reference to the boat being moved
	private Boat boat;

	/**
	 * This constructor takes one parameter, calls the constructor of the superclass and assigns the value for boat
	 * @param boat
	 */
	public BoatMoveLeftListener (Boat boat) {
		
		//superclass' constructor is called with a parameter of boat for piece and null for boat
		super(boat, null);
		
		this.boat = boat;
	}

	/**
	 * This method overrides the actionPerformed method in the superclass
	 * handles button press event
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		/*
		 * Boats can only move right if in position 1 AND if 
		 * the validity checks in boat are passed - max two pieces AND one of which is the Farmer 
		 */
		
		//If boat is in position 1 then
		if ((boat.getPosition()==1)){
			//call actionPerformed from the superclass
			super.actionPerformed(e);
		}
	}
}
