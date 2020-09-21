package preliminaries.View;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.DefaultCaret;

import preliminaries.model.Plane;

/** 
 * this is the view part of the MVC system. This class contains
 * the UI of the whole program, users manipulate components and
 * the values are passed to the controller class
 * 
 * this class has a textArea to display multiline text
 * two sliders, one Horizontal, one Vertical
 * and a reset button which resets all the ui elements
 * @author Sharif
 *
 */
public class FlightSimView extends JFrame implements Observer{
	
	private static final long serialVersionUID = 1L;
	
	// widgets
	private JTextArea jtaScreen;
	private JSlider jsHorizontal;
	private JSlider jsVertical;
	private JButton jbReset;

	// slider widget constants
	public static final int SLIDER_MIN = 0;
	public static final int SLIDER_MAX = 10;

	// panels/panes for the grid of the UI container
	JScrollPane jspTop;
	JPanel jpBottom;
	private Plane plane;

	/**
	 * constructor initilises all the widgets and sets some default values of the UI
	 */
	public FlightSimView(Plane plane) {
		// calls superclass constructor, gives title
		super("Flight Simulator");
		// sets default closing behavior
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// initialises all the widgets/containers
		initWidgets();
		this.plane = plane;
		plane.addObserver(this);

	}

	/**
	 * builds all the widgets of the UI
	 */
	public void initWidgets() {
		
		//textArea of default size 20x40
		jtaScreen = new JTextArea(20, 40);
		
		// sliders with their mix/max values, and default values
		jsHorizontal = new JSlider(SLIDER_MIN, SLIDER_MAX, 5);
		jsVertical = new JSlider(JSlider.VERTICAL, SLIDER_MIN, SLIDER_MAX, 0);
		
		// reset button
		jbReset = new JButton("Reset");
		
		// make textarea read only
		jtaScreen.setEditable(false);
		
		/**
		 * a listener for the reset button
		 * @author Sharif
		 *
		 */
		class ResetBtnListener implements ActionListener {
			// if reset button pressed, resets values of sliders, text area and 
			@Override
			public void actionPerformed(ActionEvent event) {
				if (event.getSource() == jbReset) {
					jsVertical.setValue(SLIDER_MIN);
					jsHorizontal.setValue(5);
					jtaScreen.setText("");
				}
			}
		}

		// add action listener to the reset button
		jbReset.addActionListener(new ResetBtnListener());
		
		// add a change listener to the slider which saves the slider's value to a field
		jsHorizontal.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				plane.setPlaneX(jsHorizontal.getValue());
			}
		});

		// add a change listener to the slider which saves the slider's value to a field
		jsVertical.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				plane.setPlaneSpeed(jsVertical.getValue());
			}
		});

		// set the layout of the screen
		this.setLayout(new GridLayout(2, 1));

		// add scrollpane for the text area, and set scroll rules
		jspTop = new JScrollPane(jtaScreen);
		jspTop.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		jspTop.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

		// set caret so that textrea will autoscroll when new lines added
		DefaultCaret caret = (DefaultCaret) jtaScreen.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);

		// create new panel, add sliders, and set new layout within this panel
		jpBottom = new JPanel();
		jpBottom.setLayout(new BorderLayout());
		jpBottom.add(jsHorizontal, BorderLayout.NORTH);
		jpBottom.add(jsVertical, BorderLayout.CENTER);
		jpBottom.add(jbReset, BorderLayout.SOUTH);

		// add all the panels to the top-level container and pack
		this.add(jspTop);
		this.add(jpBottom);
		this.pack();
	}
	
	@Override
	public void update(Observable o, Object arg) {
		// output plane status the textarea
		jtaScreen.append(plane.getPlaneStatus()); 
	}
}
