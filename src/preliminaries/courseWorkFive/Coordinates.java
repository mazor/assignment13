package preliminaries.courseWorkFive;

	public class Coordinates {
		private int x;
		private int y;

		/**
		 * class constructor, assign x and values of coordinates
		 * @param x
		 * @param y
		 */
		public Coordinates(int x, int y) {
			this.x = x;
			this.y = y;
		}
		
		/**
		 * get the x value of coordinate
		 * @return int x
		 */
		public int getCoordX() {
			return x;
		}
		
		/**
		 * set the x value of coordinate 
		 * @param x
		 */
		public void setCoordX(int x) {
			this.x = x;
		}
		
		/**
		 * get the y value of coordinate
		 * @return int y
		 */
		public int getCoordY(){
			return y;
		}
		
		/**
		 * set the y value of coordinates
		 * @param y
		 */
		public void setCoordY(int y) {
			this.y = y;
		}
		
		/**
		 * Add to the current value of x, rather than  
		 * just inputting a new value
		 * @param increase
		 */
		public void increaseX(int increase) {
			this.x += increase;
		}
		
		/**
		 * subtract from the current value of x,
		 * rather than inputting a new value all together
		 * @param decrease
		 */
		public void decreaseX(int decrease) {
			this.x -= decrease;
		}
		
		/**
		 * add to the value of y,
		 * instead of replacing with a new value
		 * @param increase
		 */
		public void increaseY(int increase) {
			this.y += increase;
		}
		
		/**
		 * decrease the value of y, 
		 * instead of replacing with a new value
		 * @param decrease
		 */
		public void decreaseY(int decrease) {
			this.y -= decrease;
		}
	
}
