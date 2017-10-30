import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;

public class Main extends JFrame implements ActionListener, KeyListener{
	
	// This needs to be a global variable because
	// this button records the state of the game
	// and other classes need access to this
	// state
	protected PaintButton stateButton;
	
	protected NewCanvas canvas;
	
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
	setSize (1100, 1000);
	setDefaultCloseOperation (EXIT_ON_CLOSE);
	addKeyListener(this);

	Container content = getContentPane();
	content.setLayout (new BorderLayout()); 

	// Text field at top
	JLabel gameLabel = new JLabel ("Ship navigation Simulation");
	gameLabel.setBorder (new LineBorder(Color.BLACK, 2));
	content.add (gameLabel, BorderLayout.NORTH); 
	
	// Drawing canvas in middle
	canvas = new NewCanvas (this);
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

	// Output panel on right
	JPanel outp = new JPanel (); 
	outp.setBorder (new LineBorder(Color.BLACK, 2));
	outp.setLayout (new BoxLayout(outp, BoxLayout.Y_AXIS)); 

	// Put these inside the settings panel
	JLabel label = new JLabel ("Get current locations of:");
	outp.add (label); 
	
	LocationButton ship1Button = new LocationButton("Ship 1", 0, canvas);
	LocationButton ship2Button = new LocationButton("Ship 2", 1, canvas);
	LocationButton ship3Button = new LocationButton("Ship 3", 2, canvas);
	LocationButton myshipButton = new LocationButton ("This ship", 3, canvas);
	LocationButton allButton = new LocationButton("All", 4, canvas);
	
	outp.add(ship1Button);
	outp.add(ship2Button);
	outp.add(ship3Button);
	outp.add(myshipButton);
	outp.add(allButton);
	//JCheckBox graphicsCB = new JCheckBox ("Graphics", true);
	//settings.add (graphicsCB); 
	//JCheckBox animationCB = new JCheckBox ("Animation", true);
	//settings.add (animationCB); 
	//JCheckBox javascriptCB = new JCheckBox ("Javascript", false);
	//settings.add (javascriptCB); 
	//JCheckBox cookiesCB = new JCheckBox ("Cookies", false);
	//settings.add (cookiesCB); 

	// Plugging the output panel into the main frame
	content.add (outp, BorderLayout.EAST); 

	// And show the whole window
	setVisible (true);
	
	// start Timer
	Timer timer = new Timer(100, this); //100 milliseconds
	timer.start();
    }
    
    public int returnState() {
    	return stateButton.currentState();
    }
    
    public void actionPerformed (ActionEvent e) {
    	if (returnState() == 2) {
    		canvas.runningState();
    		canvas.repaint();
    	}
    }
    
    public void keyPressed(KeyEvent e) {
    	if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
    		System.exit(0);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}
}

