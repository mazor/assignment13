package assignment.model; 

import javax.swing.JLabel;

/**
 * 
 * @author Mazor, Sharif
 * The Goose class is an extension of the Piece class, 
 * this adds methods to check if Goose is eaten by Fox or eats Beans
 *
 */
public class Goose extends Piece { 

	private Farmer farmer; // each Goose has a Farmer

	/**
	 * This constructor takes two parameters.
	 * 
	 * @param farmer: assigned as farmer
	 * @param imageLabel: is passed as a parameter in the call to the superclass to assign the image for Goose
	 */
	public Goose(Farmer farmer, JLabel imageLabel) {

		// Calls a constructor from Piece with the parameter imageLabel to assign the image for Goose
		super(imageLabel);

		// value for farmer is assigned as the first input
		this.farmer = farmer;

	}

	/**
	 * checks if the Goose is eaten by the Object supplied.
	 * 
	 * @param thing
	 * @return true if Goose is eaten, otherwise return false
	 */
	public boolean isEatenBy(Object thing) {
		
		// if the Object thing is a Fox then
		if (thing instanceof Fox) {
			
			// if the Fox and Goose are in the same position 
			//AND the farmer is not in the same position as the Goose
			//AND the farmer is not in a position that is one greater than the Goose's position
			//AND the farmer is not in a position that is one less than the Goose's position then
			if (((this.getPosition() == ((Fox) thing).getPosition())
					&& (farmer.getPosition() != this.getPosition()) && (farmer.getPosition() != this.getPosition() + 1)
					&& (farmer.getPosition() != (this.getPosition() - 1)))) {
				
				return true;// true is returned as the Goose would be eaten
			
			} else 
				return false; // otherwise false is returned as the Goose is not eaten by the Fox

		} else
			return false;// otherwise false is returned as the Goose is not eaten by the Object thing
	}

	/**
	 * checks if the Goose can eat the Object supplied.
	 * 
	 * @param thing
	 * @return true if Object is eaten, otherwise return false
	 */
	public boolean eats(Object thing) {
		
		// If the Object is a Beans then
		if (thing instanceof Beans) {
			// if the Beans and Goose are in the same position 
			//AND the farmer is not in the same position 
			//AND the farmer is not in a position that is one greater than the Goose's position
			//AND the farmer is not in a position that is not one less than the Goose's position then
			if ((farmer.getPosition() != this.getPosition())
					&& (this.getPosition() == ((Beans) thing).getPosition())
					&& (farmer.getPosition() != this.getPosition() + 1)
					&& (farmer.getPosition() != (this.getPosition() - 1))) {
				// true is returned as the Beans would be eaten
				return true;
			} else
				return false; // otherwise false is returned as the Beans is
								// not eaten by the Goose

		} else
			return false; // otherwise false is returned as the 'thing' is not
							// eaten by the Goose
	}

}
