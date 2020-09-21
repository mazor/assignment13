package assignment.model; 

import javax.swing.JLabel;

/**
 * 
 * @author Mazor, Sharif
 * The Fox class is an extension of the Piece class, 
 * this adds a method to check if fox eats Goose
 */
public class Fox extends Piece{

	// used to check if Fox eats the goose
	private Farmer farmer; 
	
	/**
	 * This constructor takes two parameters.
	 * @param farmer: assigned as farmer
	 * @param imageLabel: is passed as a parameter in the call to the superclass
	 */
	public Fox(Farmer farmer, JLabel imageLabel){

		//Calls a constructor from Piece with the parameter imageLabel
		super(imageLabel);
		
		//value for farmer is assigned as the first input
		this.farmer=farmer;
		
	}

	
	/**
	 * This method checks if the Fox can eat the Object supplied. 
	 * @param thing
	 * @return true if Object is eaten, otherwise return false
	 */
	public boolean eats(Object thing){
		//If the Object is a Goose then
		if (thing instanceof Goose){ 
			//if the Fox and Goose are in the same position 
			//AND the farmer is not in the same position as the Fox
			//AND the farmer is not in a position that is one greater than the Fox's position
			//AND the farmer is not in a position that is not one less than the Fox's position then
			if ((farmer.getPosition() != this.getPosition()) && (this.getPosition() == ((Beans)thing).getPosition())
					&& (farmer.getPosition() != this.getPosition() + 1) && (farmer.getPosition() != (this.getPosition()-1))){
				//true is returned as the Goose would be eaten
				return true;
			}  else return false; //otherwise false is returned as the Goose is not eaten by the Fox
			
		} else return false; //otherwise false is returned as the 'thing' is not eaten by the Fox
	}

}
