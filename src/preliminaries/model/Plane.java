package preliminaries.model;

import java.awt.Rectangle;
import java.util.Observable;

import preliminaries.courseWorkFive.Coordinates;

/**
 * Plane is the model in the MVC system plane has x, y coordinates of
 * Coordinates type. the plane also has speed, elevation, and how required speed
 * and elevation for take off methods to set plane speed, and x position,
 * methods to calculate y and elevation and one to reset the plane
 * 
 * plane also runs the main loop which performs the simulation once per minute
 * 
 * @author Sharif
 *
 */
public class Plane extends Observable {

	// some constants calculate plane position and speed
	public static final int TAKEOFF_ELEVATION = 5;
	public static final int ASCENT_SPEED = 10;
	public static final int TIME_TO_ASCEND = 5;

	// plane fields
	private int planeSpeed;
	private Coordinates planeCoords;
	private int planeElevation;
	private int timeAtAscentSpeed;

	// simulation fields
	private int timer;
	Rectangle myRunway = new Rectangle(10, 100);
	private String planeStatus;

	/**
	 * initialises the plane with an x position of 5, centre of runway
	 */
	public Plane() {
		// Initialize the plane at the center
		planeCoords = new Coordinates(5, 0);
		timer = 0;
		planeStatus = this.toString();
		
	}

	/**
	 * increases the plane's Y positon based on its speed
	 */
	public void calculateY() {
		planeCoords.increaseY(planeSpeed);
		setChanged();
	}

	/**
	 * calculates the plane's elevation based on whether plane is at ascent
	 * speed, and how long it has been at that speeda
	 * 
	 * plane will ascend if conditions met, or will descend
	 */
	public void calculateElevation() {
		// if plane is above ascent speed, will add one to a ascent speed timer,
		// otherwise timer will return to 0
		if (planeSpeed >= ASCENT_SPEED) {
			timeAtAscentSpeed++;
		}

		if (planeSpeed < ASCENT_SPEED) {
			// decrement instead of reset?
			timeAtAscentSpeed = 0;
		}

		// if plane has been been at the required speed long enough,
		// elevation begins to increase, otherwise decreases until 0
		if (timeAtAscentSpeed >= TIME_TO_ASCEND) {
			planeElevation++;
			setChanged();
		} else if (timeAtAscentSpeed < TIME_TO_ASCEND && planeElevation > 0) {
			planeElevation--;
			setChanged();
		}
	}

	@Override
	public String toString() {
		String output = "Time: " + timer + "\nX: " + planeCoords.getCoordX() + " Y: " + planeCoords.getCoordY()
				+ " Speed: " + planeSpeed + " Elevation: " + planeElevation + "\n";
		System.out.println(output);  // prints out once and stops
		return output;	
	}

	/**
	 * getter for plane speed
	 * 
	 * @return the current speed of plane
	 */
	public int getPlaneSpeed() {
		return planeSpeed;
	}

	/**
	 * setter for plane speed
	 * 
	 * @param sets
	 *            the plane's current planeSpeed
	 */
	public void setPlaneSpeed(int planeSpeed) {
		this.planeSpeed = planeSpeed;
		setChanged();
		//System.out.println(planeSpeed); // works from the view
	}

	/**
	 * getter for plane's elevation
	 * 
	 * @return current elevation of the plane
	 */
	public int getPlaneElevation() {
		return planeElevation;
	}

	/**
	 * sets the plane's x position
	 * 
	 * @param set
	 *            current plane x position
	 */
	public void setPlaneX(int x) {
		planeCoords.setCoordX(x);
		setChanged();
		//System.out.println("" + planeCoords.getCoordX()); //works from the event handler of view
	}

	/**
	 * returns the plane's x value
	 * 
	 * @return the x value of plane
	 */
	public int getPlaneX() {
		return planeCoords.getCoordX();
	}

	/**
	 * getter for plane's Y value
	 * 
	 * @return the current Y value of the plane
	 */
	public int getPlaneY() {
		return planeCoords.getCoordY();
	}
	
	public String getPlaneStatus() {
		//System.out.println(planeStatus);
		return planeStatus;
	}

	/**
	 * resets all the fields of the plane to the default
	 */
	public void resetPlane() {
		planeCoords.setCoordX(5);
		planeCoords.setCoordY(0);
		planeSpeed = 0;
		planeElevation = 0;
		timeAtAscentSpeed = 0;
		setChanged();
	}

	public void mainLoop() {
		
		do {
			// looping game logic
			
			// calculate position
			calculateY();
			calculateElevation();
			
			// update plane status
			planeStatus = this.toString();

			// check if plane meets takeoff condition
			if (getPlaneY() > 100) {
				if (getPlaneElevation() >= TAKEOFF_ELEVATION && getPlaneX() == myRunway.getWidth() / 2) {
					planeStatus += "\nTake off successful";
				} else {
					planeStatus += "\nTake off failed";
				}
			}
			timer++;
			
			// put thread to sleep for 1000ms (1s) to simulate passage of time
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			setChanged();
			notifyObservers();
		} while (planeCoords.getCoordY() <= myRunway.getHeight());
	}
}
