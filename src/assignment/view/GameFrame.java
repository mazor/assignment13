

package assignment.view; 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import assignment.controller.BoatMoveLeftListener;
import assignment.controller.BoatMoveRightListener;
import assignment.controller.LeftMoveListener;
import assignment.controller.RightMoveListener;
import assignment.model.Beans;
import assignment.model.Boat;
import assignment.model.Farmer;
import assignment.model.Fox;
import assignment.model.GameChecker;
import assignment.model.Goose;
import assignment.model.Piece;


//this class inherits all methods from its parent class (JFrame) and implements Observer
public class GameFrame extends JFrame implements Observer {
	
	// game pieces
	private Fox fox; 
	private Goose goose; 
	private Beans bean; 
	private Farmer farmer; 
	private Boat boat;
	
	// widgets
	private JLabel jlRiverRight;
	private JLabel jlRiverLeft;
	private PicturePanel jpEast;
	private PicturePanel jpWest;
	private Dimension sizeSetting;
	private JButton jbFoxLeft;
	private JButton jbBeanLeft;
	private JButton jbBoatLeft;
	private JButton jbGooseLeft;
	private JButton jbFarmerLeft;
	private JButton jbFoxRight;
	private JButton jbBeanRight;
	private JButton jbGooseRight;
	private JButton jbBoatRight;
	private JButton jbFarmerRight;
	
	
	private int score; //represents the current score
	
	// game logic objects
	private GameChecker checker;
	private boolean[] checks;
	
	/**
	 * The following constructor initialises all the Components and the Pieces of the JFrame
	 */
	public GameFrame() {

		//the tile is set as "Fox, Goose and Bag of Beans" since another constructor is called from JFrame with that parameter
		super("Fox, Goose and Bag of Beans");
		//default close operation is set using the method setDefaultCloseOperation
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
		//checker is initialised as a new Gamechecker
		checker= new GameChecker();
		//checks is initialised
		checks=new boolean[3];
		
		//sizeSetting Dimensions given 200 for width and 780 for height
		sizeSetting = new Dimension(200, 780);
				
		//initFarmer method is called
		initFarmer();
		//initGoose method is called
		initGoose();
		//initFox method is called
		initFox();
		//initBeans method is called
		initBeans();
		//initBoat method is called
		initBoat();
				
		//createWidgets method is called
		createWidgets();
				
		//this is added an observer to fox, goose, bean, farmer and boat
		fox.addObserver(this);
		goose.addObserver(this);
		bean.addObserver(this);
		farmer.addObserver(this);
		boat.addObserver(this);
		

		// set frame size and make it unresizable
		this.setSize(1320, 780);
		this.setResizable(false);
	}
	

	/**
	 * This method is used to add panels to the frame and set layout for the frame.
	 * This method cannot be accessed from outside the class as it is only needed 
	 * once during instantiation of the GameFrame. This preserves encapsulation.
	 */
	private void createWidgets() {

		//layout set as a BorderLayout
		this.setLayout(new BorderLayout());
		//panels initialised and added to the frame
		this.add(initCenterPanel(), BorderLayout.CENTER);
		initWestPanel();
		this.add(jpWest, BorderLayout.WEST);
		initEastPanel();
		this.add(jpEast, BorderLayout.EAST);
		this.add(initSouthPanel(), BorderLayout.SOUTH);

	}
	
	/**
	 * This method initialises and returns the centre panel of the frame
	 * @return jpCenter 
	 */
	private JPanel initCenterPanel() {
		
		//a new PicturePanel jpCenter is created with an input of a string that represents the location of the background image
		PicturePanel jpCenter = new PicturePanel("src/Images/water.png");
		//the panel's layout is set as a GridLayout with one row and two columns to represent where the left and right side of the river are
		jpCenter.setLayout(new GridLayout(1,2));
	
		//jlRiverRight and jlRiverLeft are where the icons will show up
		//they represent the left and right side of the river respectively
		jlRiverLeft = new JLabel();
		jlRiverRight = new JLabel();
		
		//boat's image is added to each of the JLabels jlRiverLeft and jlRiverRight
		jlRiverLeft.add(boat.getImageLabel());
		jlRiverRight.add(boat.getImageLabel());

		//lRiverLeft and jlRiverRight receive new GridLayouts with 3 rows and one column
		jlRiverLeft.setLayout(new GridLayout(3, 1));
		jlRiverRight.setLayout(new GridLayout(3, 1));
		
		//lRiverLeft and jlRiverRight are added to the centre panel
		jpCenter.add(jlRiverLeft);
		jpCenter.add(jlRiverRight);

		//the centre panel is set as opaque and returned
		jpCenter.setOpaque(true);
		return jpCenter;
	}
	
	/**
	 * This method initialises and returns the west panel of the frame
	 */
	private void initWestPanel() {
		
		//a new PicturePanel jpCenter is created with an input of a string that represents the location of the background image
		jpWest = new PicturePanel("src/Images/grass.png");
		
		//the west panel is set as opaque
		jpWest.setOpaque(true);
		
		//jpWest is set as a GridLayout with 4 rows and one column
		jpWest.setLayout(new GridLayout(4, 1));
		
		//Piece images are added to the panel
		jpWest.add(fox.getImageLabel());
		jpWest.add(goose.getImageLabel());
		jpWest.add(bean.getImageLabel());
		jpWest.add(farmer.getImageLabel());
		
		//size is set for the panel
		jpWest.setMaximumSize(sizeSetting);
		jpWest.setPreferredSize(sizeSetting);
		jpWest.setMaximumSize(sizeSetting);
	
	}
	
	/**
	 * This method initialises and returns the east panel of the frame
	 */
	private void initEastPanel() {
		
		//a new PicturePanel jpCenter is created with an input of a string that represents the location of the background image
		jpEast = new PicturePanel("src/Images/grass.png");
	
		//the east panel is set as opaque
		jpEast.setOpaque(true);

		//jpEast is set as a GridLayout with 4 rows and one column
		jpEast.setLayout(new GridLayout(4, 1));
		
		//Piece images are added to the panel
		jpEast.add(fox.getImageLabel());
		jpEast.add(goose.getImageLabel());
		jpEast.add(bean.getImageLabel());
		jpEast.add(farmer.getImageLabel());
		 
		//size is set for the panel
		jpEast.setMaximumSize(sizeSetting);
		jpEast.setPreferredSize(sizeSetting);
		jpEast.setMaximumSize(sizeSetting);
	
	}
	
	/**
	 * This method initialises and returns the south panel of the frame
	 * @return jpSouth 
	 */
	private JPanel initSouthPanel() {

		// JLabels are created
		JLabel jlFox = new JLabel("Fox:");
		JLabel jlBoat = new JLabel("Boat:");
		JLabel jlBean = new JLabel("Bean:");
		JLabel jlGoose = new JLabel("Goose:");
		JLabel jlFarmer = new JLabel("Farmer:");

		// JButtons are created (right and left for each Piece)
		jbFoxLeft = new JButton("<");
		jbBeanLeft = new JButton("<");
		jbBoatLeft = new JButton("<");
		jbGooseLeft = new JButton("<");
		jbFarmerLeft = new JButton("<");
		jbFoxRight = new JButton(">");
		jbBeanRight = new JButton(">");
		jbGooseRight = new JButton(">");
		jbBoatRight = new JButton(">");
		jbFarmerRight = new JButton(">");

		
		
		// ActionListeners created
		ActionListener lmlFoxLeft = new LeftMoveListener(fox, boat);
		ActionListener rmlFoxRight = new RightMoveListener(fox, boat);
		ActionListener lmlBoatLeft = new BoatMoveLeftListener(boat);
		ActionListener rmlBoatRight = new BoatMoveRightListener(boat);
		ActionListener lmlBeanLeft = new LeftMoveListener(bean, boat);
		ActionListener rmlBeanRight = new RightMoveListener(bean, boat);
		ActionListener lmlGooseLeft = new LeftMoveListener(goose, boat);
		ActionListener rmlGooseRight = new RightMoveListener(goose, boat);
		ActionListener lmlFarmerLeft = new LeftMoveListener(farmer, boat);
		ActionListener rmlFarmerRight = new RightMoveListener(farmer, boat);

		// ActionListeners added to each JButton
		jbFoxLeft.addActionListener(lmlFoxLeft);
		jbFoxRight.addActionListener(rmlFoxRight);
		jbBoatRight.addActionListener(rmlBoatRight);
		jbBoatLeft.addActionListener(lmlBoatLeft);
		jbBeanLeft.addActionListener(lmlBeanLeft);
		jbBeanRight.addActionListener(rmlBeanRight);
		jbGooseLeft.addActionListener(lmlGooseLeft);
		jbGooseRight.addActionListener(rmlGooseRight);
		jbFarmerLeft.addActionListener(lmlFarmerLeft);
		jbFarmerRight.addActionListener(rmlFarmerRight);
		
		
		//new JPanel created
		JPanel jpSouth = new JPanel();
		//panel's layout is set
		jpSouth.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		//Labels and buttons are added to the panel
		jpSouth.add(jlBoat);
		jpSouth.add(jbBoatLeft);
		jpSouth.add(jbBoatRight);

		jpSouth.add(jlFox);
		jpSouth.add(jbFoxLeft);
		jpSouth.add(jbFoxRight);

		jpSouth.add(jlGoose); 
		jpSouth.add(jbGooseLeft);
		jpSouth.add(jbGooseRight);

		jpSouth.add(jlBean);
		jpSouth.add(jbBeanLeft);
		jpSouth.add(jbBeanRight);

		jpSouth.add(jlFarmer);
		jpSouth.add(jbFarmerLeft);
		jpSouth.add(jbFarmerRight);

		//panel is returned
		return jpSouth;
	}
	
	/**
	 * Initialises the Farmer (farmer)
	 */
	private void initFarmer(){
		//biFarmer is assigned as null
		BufferedImage biFarmer = null;
	
		//tries the following code
		try {
			//biFarmer is assigned an Image
			biFarmer = ImageIO.read(new File("src/Images/farmer.png"));

		} catch (IOException e) {//catches exception
			//calls the printStackTrace method from e
			e.printStackTrace();
		}
		//a new JLabel jlFarmerPic is created with a parameter of a new ImageIcon with a parameter of biFarmer
		JLabel jlFarmerPic = new JLabel(new ImageIcon(biFarmer));
		//jlFarmerPic's background is set so that it is transparent 
		jlFarmerPic.setBackground(new Color(0,0,0,0));	
		//farmer is assigned as a new Farmer with the parameter of jlFarmerPic
		farmer = new Farmer(jlFarmerPic);


	}
	/**
	 * Initialises the Boat (boat)
	 */
	private void initBoat(){
		
		//biBoat is assigned as null
		BufferedImage biBoat = null;
		Image biBoatScaled = null;
	
		//tries the following code
		try {
			//biBoat is assigned an Image
			biBoat = ImageIO.read(new File("src/Images/boat.png"));
			biBoatScaled = biBoat.getScaledInstance(225, 225, 0);
		} catch (IOException e) {//catches exception
			//calls the printStackTrace method from e
			e.printStackTrace();
		}

		//a new JLabel jlBoatPic is created with a parameter of a new ImageIcon with a parameter of biBoat
		JLabel jlBoatPic = new JLabel(new ImageIcon(biBoatScaled));
		//jlBoatPic's background is set so that it is transparent 
		jlBoatPic.setBackground(new Color(0,0,0,0));
		//boat is assigned as a new Boat with parameters of jlBoatPic and farmer
		boat = new Boat(jlBoatPic,farmer);
		
	}
	/**
	 * Initialises the Beans (bean)
	 */
	private void initBeans(){
		
		//biBeans is assigned as null
		BufferedImage biBeans = null;

		//tries the following code
		try {
			//biVegtable is assigned an Image
			biBeans = ImageIO.read(new File("src/Images/beans.png"));
		} catch (IOException e) {//catches exception
			//calls the printStackTrace method from e
			e.printStackTrace();
		}

		//a new JLabel jlBeans is created with a parameter of a new ImageIcon with a parameter of biBeans
		JLabel jlBeans = new JLabel(new ImageIcon(biBeans));
		//jlBeans's background is set so that it is transparent 
		jlBeans.setBackground(new Color(0,0,0,0));
		//bean is assigned as a new Beans with parameters of farmer and jlBeans
		bean = new Beans(farmer,jlBeans);
		
	}
	/**
	 * Initialises the Fox (fox)
	 */
	private void initFox(){
		
		//biFox is assigned as null
		BufferedImage biFox = null;

		// tries the following code
		try {
			// biFox is assigned an Image
			biFox = ImageIO.read(new File("src/Images/fox.png"));
		} catch (IOException e) {// catches exception
			// calls the printStackTrace method from e
			e.printStackTrace();
		}
		// a new JLabel jlFox is created with a parameter of a new
		// ImageIcon with a parameter of biFox
		JLabel jlFox = new JLabel(new ImageIcon(biFox));
		// jlFox's background is set so that it is transparent
		jlFox.setBackground(new Color(0, 0, 0, 0));
		// fox is assigned as a new Fox with parameters of farmer and jlFox
		fox = new Fox(farmer, jlFox);
	}

	/**
	 * Initialises the Goose (goose)
	 */
	private void initGoose() {

		// biGoose is assigned as null
		BufferedImage biGoose = null;

		// assign an image to the 
		try {
			biGoose = ImageIO.read(new File("src/Images/goose.png"));
		} catch (IOException e) { 
			e.printStackTrace();
		}

		// a new JLabel jlGoose is created with a parameter of a new ImageIcon
		// with a parameter of biGoose
		JLabel jlGoose = new JLabel(new ImageIcon(biGoose));
		// jlGoose's background is set so that it is transparent
		jlGoose.setBackground(new Color(0, 0, 0, 0));
		// goose is assigned as a new Goose with parameters of farmer and jlGoose
		goose = new Goose(farmer, jlGoose);

	}
	
	/**
	 * Used to move the Piece's image
	 */
	private void movePieceImage(Piece piece){
		
		//position is assigned the value of Piece's position
		int position = piece.getPosition();
		
		// a switch statement based on position is used
		switch (position) {
			
			//in the case where position is 0
			case 0:  
				
				//remove the piece's imageLabel from jlRight
				jlRiverRight.remove(piece.getImageLabel());
				//and add it to jpEast
				jpEast.add(piece.getImageLabel());
				
				//break (exit switch)
				break;	
				
			case 1: 			
				
					//remove the piece's imageLabel from jpEast
					jpEast.remove(piece.getImageLabel());
					
					//revalidate and repaint called from jpEast
					jpEast.revalidate();
					jpEast.repaint();
					
					//remove the piece's imageLabel from jlRiverLeft
			    	jlRiverLeft.remove(piece.getImageLabel());
			    	
			    	//fox's imageLabel is added to jlRiverRight
			    	jlRiverRight.add(piece.getImageLabel());
			    	
			    	//break (exit switch)
	                break;
	                
	        case 2:   
	        		//remove the piece's imageLabel from jlRiverRight
	        		jlRiverRight.remove(piece.getImageLabel());
	        		
	        		//remove the piece's imageLabel from jpWest
	            	jpWest.remove(piece.getImageLabel());
	            	
	            	//revalidate and repaint called from jpWest
	            	jpWest.revalidate();
	            	jpWest.repaint();
	            	//revalidate and repaint called from jlRiverRight
	            	jlRiverRight.revalidate();
	            	jlRiverRight.repaint();
	            	
	            	//remove and add the piece's imageLabel to the jlRiverLeft 
	            	jlRiverLeft.remove(piece.getImageLabel());
	            	jlRiverLeft.add(piece.getImageLabel());
	            	
	            	//break (exit switch)
	                break;
	        case 3: 
	        		//remove the piece's imageLabel to the jlRiverLeft 
	            	jlRiverLeft.remove(piece.getImageLabel());
	            	//add the piece's imageLabel to the jpWest 
	            	jpWest.add(piece.getImageLabel());
	            	//break (exit switch)
	                 break;
	       default:
	    	   		//output error message
	            	System.out.println("Invalid Position");
	            	//break (exit switch)
	            	break;
			}
		                     
	}


	/**
	 * Used to move the Boat's image
	 */
	private void moveBoatImage() {
		
		//position is assigned the value of Boat's position
		int position = boat.getPosition();
		
		// a switch statement based on position is used
		switch (position) {
	
			//in the case where position is 1
			case 1:
				
				//boat's image is removed from jlRiverLeft
				jlRiverLeft.remove(boat.getImageLabel());
				//boat's image is added to jlRiverRight
				jlRiverRight.add(boat.getImageLabel());
				
				//revalidate and repaint called from jlRiverLeft
				jlRiverLeft.revalidate();
				jlRiverLeft.repaint();
				
				//revalidate and repaint called from jlRiverRight
				jlRiverRight.revalidate();
				jlRiverRight.repaint();
				
				//break (exit switch)
				break;
			
			//in the case where position is 2
			case 2:
				
				//boat's image is removed from jlRiverRight
				jlRiverRight.remove(boat.getImageLabel());
				//boat's image is added to jlRiverLeft
				jlRiverLeft.add(boat.getImageLabel());
				
				//revalidate and repaint called from jlRiverLeft
				jlRiverLeft.revalidate();
				jlRiverLeft.repaint();
				
				//revalidate and repaint called from jlRiverRight
				jlRiverRight.revalidate();
				jlRiverRight.repaint();
				
				//break (exit switch)
				break;
			default:
				
			 	//output error message
				System.out.println("Invalid Position");
				//break (exit switch)
				break;
		}

	}

	/**
	 * Update is triggered whenever notifyObservers is called in the Observable object
	 */
	@Override
	public void update(Observable o, Object arg) {

		//movePieceImage is called four times with different parameters to update locations of fox, farmer, goose and bean
		movePieceImage(fox);
		movePieceImage(farmer);
		movePieceImage(goose);
		movePieceImage(bean);

		//moveBoatImage is called to update location of Boat
		moveBoatImage();
		
		//frame is set as visible
		setVisible(true);
		
		//score is updated by calling getScore from checker
		score = checker.getScore(boat);
		
		//the frame's title is updated to show the current score
		this.setTitle("The current score is " + score);
		
		//checks is set as the returned value of calling isGameOver from checker
		checks=checker.isGameOver(fox,farmer, goose, bean);
		
		//if the game is over then
		if (checks[0]){
			
			//if the game was won
			if (checks[2]) {
				
				//the frame's title is updated to show the final score with a final message
				this.setTitle("You win! :) The final score is " + score);
				
			//otherwise, if goose was eaten then
			} else if (checks[1]){
				
				//the frame's title is updated to show that the goose was eaten
				this.setTitle("Game Over: Predator ate prey.");
					
			} else 	{ //otherwise
				
				//the frame's title is updated to show that the bean was eaten
				this.setTitle("Game Over: Prey ate beans.");
				
			}
				
				//all buttons are disabled
				jbBeanLeft.setEnabled(false);
				jbBeanRight.setEnabled(false);
				
				jbFoxRight.setEnabled(false);
				jbFoxLeft.setEnabled(false);
				
				jbBoatLeft.setEnabled(false);
				jbBoatRight.setEnabled(false);
				
				jbGooseLeft.setEnabled(false);
				jbGooseRight.setEnabled(false);
				
				jbFarmerLeft.setEnabled(false);
				jbFarmerRight.setEnabled(false);

				//revalidate and repaint are called
				revalidate();
				repaint();
				
		}
		
	}

	
}
