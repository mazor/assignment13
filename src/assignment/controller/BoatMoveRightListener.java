package assignment.controller; 

import java.awt.event.ActionEvent;
import assignment.model.Boat;

/**
 * 
 * @author Sharif, Mazor
 * Used to move the Boat piece right
 * Extends the RightMoveListener, which is used to move pieces to the right
 */
public class BoatMoveRightListener extends RightMoveListener { 
	
	// references the boat that is being moved
	private Boat boat;
	
	/**
	 * This constructor takes one parameter, calls the constructor of the superclass and assigns the value for boat
	 * @param boat
	 */
	public BoatMoveRightListener (Boat boat) {
			
		//superclass' constructor is called with a parameter of boat for piece and null for boat
		super(boat, null);
		//boat is assigned as the supplied boat
		this.boat = boat;
	}

	/**
	 * This method overrides the actionPerformed method in the superclass
	 * */
	@Override
	public void actionPerformed(ActionEvent e) {
		//Boats can only move right if in position 2
		//AND
		//if the validity checks in boat are passed- max two pieces AND one of which is the Farmer
			
		//If boat is in position 2 then
		if (boat.getPosition()==2){
			//call actionPerformed from the superclass
			super.actionPerformed(e);	
		}
	}
}

