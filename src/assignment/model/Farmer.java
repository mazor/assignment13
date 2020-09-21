package assignment.model;


import javax.swing.JLabel; 

/**
 *
 * @author Mazor, Sharif
 * Used to represent the farmer in the fox, goose and bean game
 *
 */
public class Farmer extends Piece{//this class inherits all methods from its parent class (Piece)

	private String name; //Farmer has a name
	
	/**
	 * This constructor takes one parameter.
	 * @param imageLabel: is passed as a parameter in the call to the superclass
	 */
	public Farmer(JLabel imageLabel){
		//Calls a constructor from Piece with the parameter imageLabel
		super(imageLabel);
		
		//name is assigned as "Old MacDonald"
		this.name="Old McDonald";
	}
	
	/**
	 * This constructor takes two parameters.
	 * @param name: assigned as name
	 * @param imageLabel: is passed as a parameter in the call to the superclass
	 */
	public Farmer(String name, JLabel imageLabel){

		//Calls the constructor from Piece with the parameter imageLabel
		super(imageLabel);
		
		//value for name is assigned as the first input
		this.name=name;
	}


}
