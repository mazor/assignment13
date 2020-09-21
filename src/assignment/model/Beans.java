package assignment.model; 

import javax.swing.JLabel; 

/**
 * 
 * @author Mazor, Sharif
 * The Beans class is an extension of the Piece class, 
 * this adds a method to check if Beans is eaten by Fox other Piece
 *
 */
public class Beans extends Piece{ 

	// Farmer object is used to calculate if beans gets eaten
	private Farmer farmer; 
	
	/**
	 * This constructor takes two parameters.
	 * @param farmer: assigned as farmer
	 * @param imageLabel: is passed as a parameter in the call to the superclass
	 */
	public Beans(Farmer farmer, JLabel imageLabel){

		//Calls a constructor from Piece with the parameter imageLabel
		super(imageLabel);
		
		//value for farmer is assigned as the first input
		this.farmer=farmer;
		
	}
	/**
	 * This method checks if the Beans is eaten by the Object supplied. 
	 * @param thing
	 * @return true if prey is eaten, otherwise return false
	 */
	public boolean isEatenBy(Object thing){
		//if the Object thing is a Goose then
		if (thing instanceof Goose){ 
			//if the Beans and Goose are in the same position 
			//AND the farmer is not in the same position as the Beans
			//AND the farmer is not in a position that is one greater than the Beans' position
			//AND the farmer is not in a position that is not one less  than the Beans' position then
			if (((this.getPosition()==((Goose)thing).getPosition())&&(farmer.getPosition()!=this.getPosition())
					&&(farmer.getPosition()!=this.getPosition()+1)&& (farmer.getPosition()!=(this.getPosition()-1)))){
				//true is returned as the Beans would be eaten
				return true;
			}  else return false; //otherwise false is returned as the Beans is not eaten by the Goose
			
		} else return false;//otherwise false is returned as the Beans is not eaten by 'thing'
	}
	
}
