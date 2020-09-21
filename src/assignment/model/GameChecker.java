package assignment.model;

/**
 * 
 * @author Mazor, Sharif
 * This class is used to check whether a fox, goose and bean game has finished
 *
 */
public class GameChecker {
	
	
	/**
	 * This method returns the game score based on the number of trips the boat made
	 * @param boat
	 * @return score 
	 */
	public int getScore(Boat boat){
		
		//score is zero minus the number of trips the boat made
		int score=0-boat.getNumOfTrips();
		return score;
	}
	
	/**
	 * This method takes four parameters.
	 * It checks them to decide whether the fox, goose and bean game they are in has finished
	 * @param fox
	 * @param farmer
	 * @param goose
	 * @param bean
	 * @return a boolean array of size 3
	 * The first boolean value (index 0) of the array represents whether the game is over
	 * The second boolean value (index 1) of the array represents whether the goose was eaten
	 * The third boolean value (index 2 of the array represents whether the game was won
	 */
	public boolean[] isGameOver(Fox fox, Farmer farmer, Goose goose, Beans bean) {
		
		// isOver represents whether the game has been finished
		// isOver is initially false as the game has not been proven to be finished yet
		boolean isOver = false; 
		
		//preyEaten represents whether the goose has been eaten by the fox 
		//preyEaten is initially false as the goose has not been proven to be eaten yet
		boolean preyEaten=false;
		
		//isWon represents whether the game has been won
		//isWon is initially false as the game has not been proven to be won yet
		boolean isWon=false;
		
		//if none of the parameters supplied are null then
		if((fox!=null) &&(farmer!=null)&&(goose!=null)&&(bean!=null)){
			//if all the parameters are in the position 3 (which represents the west bank) then
			if ((fox.getPosition() == 3) && (goose.getPosition() == 3) 
					&& (bean.getPosition() == 3) && (farmer.getPosition() == 3)) {
				//game is over and was won so both isOver and isWon are assigned to be true
				isOver = true;
				isWon = true;
			}
			//if the farmer is two positions away from the goose then
			if ((farmer.getPosition() == (goose.getPosition() - 2))
					|| (farmer.getPosition() == (goose.getPosition() + 2))) {
				
				//if the goose was eaten by the fox then
				if (goose.isEatenBy(fox)){
					
					//the game is over and the goose was eaten so both isOver and preyEaten are assigned as true
					preyEaten=true;
					isOver = true;
				}
				
				//if the goose ate the bean then
				if(goose.eats(bean)) {
					//the game is over so isOver is set as true
					isOver = true;
					// preyEaten and isWon are still false but isOver is true to represent the goose ate bean
				}
	
			}
		}
		//a boolean array of size 3 is created with the values of isOver, preyEaten and isWon
		boolean[] gameOverChecks={isOver, preyEaten, isWon};
		
		//the boolean array created is returned
		return gameOverChecks;
	}
}
