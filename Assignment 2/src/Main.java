import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JFrame {
	
	// This needs to be a global variable because
	// this button records the state of the game
	// and other classes need access to this
	// state
	protected PaintButton stateButton;
	
    public static void main (String [] args) {
	java.awt.EventQueue.invokeLater (new Runnable() {
	    public void run() {
		new Main ();
            }
        });
    }

    public Main () {
    	
	// Window setup
	setLocation (50, 50);
	setSize (1000, 1000);
	setDefaultCloseOperation (EXIT_ON_CLOSE);

	Container content = getContentPane();
	content.setLayout (new BorderLayout()); 

	// Text field at top
	JLabel gameLabel = new JLabel ("Ship navigation Simulation");
	gameLabel.setBorder (new LineBorder(Color.BLACK, 2));
	content.add (gameLabel, BorderLayout.NORTH); 
	
	// Drawing canvas in middle
	NewCanvas canvas = new NewCanvas (this);
	canvas.setBorder (new LineBorder(Color.BLACK, 2));
	content.add (canvas, BorderLayout.CENTER);


	// Control panel at bottom
	JPanel controls = new JPanel (); 
	controls.setBorder (new LineBorder(Color.BLACK, 2));
	controls.setLayout (new FlowLayout ()); 

	// Direction selector inside the control panel
	String[] comboStrings = { "North", "South", "East", "West" };
	JComboBox<String> combo = new JComboBox<String> (comboStrings);
	controls.add (combo); 

	// 2 buttons inside control panel
	stateButton = new PaintButton ("Start", canvas, (String) combo.getSelectedItem(), this);
	controls.add (stateButton);
	
	PaintButton resetButton = new PaintButton ("Reset", canvas, (String) combo.getSelectedItem(), this);
	controls.add(resetButton);

	// Now plug the control panel into the main frame
	content.add (controls, BorderLayout.SOUTH); 

	// Settings panel on right
	JPanel settings = new JPanel (); 
	settings.setBorder (new LineBorder(Color.BLACK, 2));
	settings.setLayout (new GridLayout (5, 1)); 

	// Put these inside the settings panel
	JLabel label = new JLabel ("Settings:");
	settings.add (label); 
	JCheckBox graphicsCB = new JCheckBox ("Graphics", true);
	settings.add (graphicsCB); 
	JCheckBox animationCB = new JCheckBox ("Animation", true);
	settings.add (animationCB); 
	JCheckBox javascriptCB = new JCheckBox ("Javascript", false);
	settings.add (javascriptCB); 
	JCheckBox cookiesCB = new JCheckBox ("Cookies", false);
	settings.add (cookiesCB); 

	// Now plug the settings panel into the main frame
	content.add (settings, BorderLayout.EAST); 

	// And show the whole window
	setVisible (true);
    }
    
    public boolean returnState() {
    	return stateButton.currentState();
    }
}

