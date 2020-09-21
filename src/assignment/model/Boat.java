package assignment.model; 

import javax.swing.JLabel; 

/**
 * 
 * @author Mazor, Sharif
 * The Boat class is an extension of the Piece class, 
 * this adds a method to check if Beans is eaten by Fox other Piece
 */
public class Boat extends Piece{
	
	// tracks user movements of the boat
	private int numOfTrips;
	
	// number of pieces on the boat
	private int numOfPieces;
	
	// There is space for two pieces (a farmer and an extraPassenger),
	// farmer must be on the boat for it to move
	private Farmer farmer;
	private Piece extraPassenger; 

	/**
	 * This constructor takes two parameters.
	 * @param farmer: assigned as farmer
	 * @param imageLabel: is passed as a parameter in the call to the superclass
	 */
	public Boat(JLabel imageLabel, Farmer farmer){
		
		// Calls a constructor from Piece with the parameters imageLabel and 1
		super(imageLabel, 1);
		
		// numofTrips is initially 0 as the Boat has not gone anywhere
		numOfTrips = 0;
		
		// numofPieces is initially 0 as the Boat has no passengers on it initially
		numOfPieces = 0;
		
		// value for farmer is assigned as the second input
		this.farmer = farmer;
		
		// extraPassenger is assigned as null as the boat does not have any passengers on it.
		extraPassenger = null;
		
	}
	
	/**
	 * returns the number of trips done by the Boat since its creation
	 * @return numOfTrips
	 */
	public int getNumOfTrips(){
		
		return numOfTrips;
		
	}
	
	/**
	 * The following method is used to add a passenger to the Boat. 
	 * It takes a possible passenger of type Piece and a String value 
	 * that tells it to move the Piece left or right if the Boat is full
	 * @param piece
	 * @param leftOrRight
	 */
	public void addPassenger(Piece piece, String leftOrRight){
		
		//if piece is of the type Farmer then
		if (piece instanceof Farmer){
			//if the piece is not the farmer that owns the Boat then
			if (!piece.equals(farmer)){
				//call the method checkAdd with the parameters supplied to this method
				checkAdd(piece, leftOrRight);
			} else 
				numOfPieces++; //otherwise, increase the numOfPieces value by one as the Farmer is now a passenger
		} else 
			checkAdd(piece, leftOrRight); //otherwise, call the method checkAdd with the parameters supplied to this method 
	}
	
	/**
	 * checks whether the supplied piece can be the extraPassenger or not. 
	 * If not then it uses the supplied leftOrRight value to decide where to move the piece.
	 * @param piece
	 * @param leftOrRight
	 */
	private void checkAdd(Piece piece, String leftOrRight){
	
		//if there is no extra passenger (extraPassenger is null) then
		if (extraPassenger == null){
			//extraPassenger is assigned piece as its value
			extraPassenger = piece;
			//numOfPieces is incremented by one as there is now an extraPassenger
			numOfPieces++;
			
		} else 	if (leftOrRight != null){
			if (leftOrRight.equals("Right")){ //otherwise, if the supplied String is "Right" then
				//piece is moved right
				piece.movePositionRight();
			} else {//otherwise,
				//piece is moved left
				piece.movePositionLeft();
			}
		}
	}
	/**
	 * The following method is used to remove a passenger (of type Piece) from the Boat if it is on the Boat
	 * If the passenger is not on the boat, then no change is made).
	 * @param piece
	 */
	public void removePassenger(Piece piece){
		
		//if piece is not null then 
		if ((piece != null) && (extraPassenger != null)){
			//if piece is the extraPassenger then
			if (extraPassenger.equals(piece)){
				//extraPassenger is made equal to null
				extraPassenger = null;
				//numOfPieces is decreased by one
				numOfPieces--;
				
			} else if(farmer.equals(piece)){ //otherwise, if the piece is the Farmer then
				//numOfPieces is decreased by one
				numOfPieces--;
			}
		}
	}
	
	/**
	 * increments the value of numOfTrips by one each time it is called
	 */
	private void incrementNumOfTrips(){
		numOfTrips++;
	}
	
	/**
	 * This method overrides movePositionRight in Piece and is used to move the Boat right
	 */
	@Override
	public void movePositionRight() {
		
		//if farmer has the same position as this Boat AND numOfPieces is less than 3
		if ((farmer.getPosition() == this.getPosition()) && (numOfPieces < 3)){
			//movePositionRight is called from the superclass
			super.movePositionRight();
			//incrementNumOfTrips is called
			incrementNumOfTrips();
			//farmer is moved right
			farmer.movePositionRight();
			//if there is an extraPassenger (it isn't null) then
			if (extraPassenger != null){
				
				//extraPassenger is moved right
				extraPassenger.movePositionRight();
				
			}
		}
	}
	
	@Override
	/**
	 * This method overrides movePositionLeft in Piece and is used to move the Boat left
	 */
	public void movePositionLeft() {
		//if farmer has the same position as this Boat AND numOfPieces is less than 3
		if ((farmer.getPosition() == this.getPosition()) && (numOfPieces < 3)){
			//movePositionLeft is called from the superclass
			super.movePositionLeft();
			//incrementNumOfTrips is called
			incrementNumOfTrips();
			//farmer is moved left
			farmer.movePositionLeft();
			//if there is an extraPassenger (it isn't null) then
			if (extraPassenger != null){
				
				//extraPassenger is moved left
				extraPassenger.movePositionLeft();
			}
		}
	}
}
