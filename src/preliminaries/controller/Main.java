package preliminaries.controller;

import java.awt.Rectangle;
import java.util.Observable;
import java.util.Observer;


import preliminaries.model.Plane;
import preliminaries.View.FlightSimView;

/**
 * this is the driver class which acts as the controller
 * in the model view controller style
 *
 */
public class Main {

	public static void main(String[] args) {
		
		// new instance of the UI and the PLane objects	
		Plane myPlane = new Plane();

		FlightSimView mySim = new FlightSimView(myPlane);
		 
		//set the UI to visible
		mySim.setVisible(true);
		
		// initiate the game loop
		myPlane.mainLoop();
	}
}
