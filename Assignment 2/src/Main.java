import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Main extends JFrame implements ActionListener, KeyListener, ChangeListener {

	private stateHandler state;

	private PaintButton stateButton;

	private MapCanvas canvas;
	
	private JColorChooser colorChooser;
	
	private Color shipColor;

	private JComboBox<String> combo;

	public static void main(String[] args) {
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new Main();
			}
		});
	}

	public Main() {
		
		state = new stateHandler();

		// Window setup
		setLocation(50, 50);
		setSize(1600, 1000);
		// addKeyListener (this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		Container content = getContentPane();
		content.setLayout(new BorderLayout());

		shipColor = content.getBackground();
		
		// Text field at top
		JLabel gameLabel = new JLabel("Ship navigation Simulation");
		gameLabel.setBorder(new LineBorder(Color.BLACK, 2));
		content.add(gameLabel, BorderLayout.NORTH);

		// Direction selector inside the control panel
		// This needd to be declared here because the canvas needs
		// access to it
		String[] comboStrings = { "North", "South", "East", "West" };
		combo = new JComboBox<String>(comboStrings);

		// Drawing canvas in middle
		canvas = new MapCanvas(combo, state, shipColor, this);
		canvas.setBorder(new LineBorder(Color.BLACK, 2));
		content.add(canvas, BorderLayout.CENTER);

		// Control panel at bottom
		JPanel controls = new JPanel();
		controls.setBorder(new LineBorder(Color.BLACK, 2));
		controls.setLayout(new FlowLayout());

		controls.add(combo);

		// 2 buttons inside control panel
		stateButton = new PaintButton("Start", canvas, (String) combo.getSelectedItem(), state, this);
		controls.add(stateButton);

		PaintButton resetButton = new PaintButton("Reset", canvas, (String) combo.getSelectedItem(), state, this);
		controls.add(resetButton);

		// Plugging the control panel into the main frame
		content.add(controls, BorderLayout.SOUTH);

		// Output panel on right
		JPanel outp = new JPanel();
		outp.setBorder(new LineBorder(Color.BLACK, 2));
		outp.setLayout(new BoxLayout(outp, BoxLayout.Y_AXIS));

		// Output panel components
		JLabel label = new JLabel("Get current locations of:");
		outp.add(label);

		LocationButton ship1Button = new LocationButton("Ship 1", 0, canvas);
		LocationButton ship2Button = new LocationButton("Ship 2", 1, canvas);
		LocationButton ship3Button = new LocationButton("Ship 3", 2, canvas);
		LocationButton myshipButton = new LocationButton("This ship", 3, canvas);
		LocationButton allButton = new LocationButton("All", 4, canvas);

		outp.add(ship1Button);
		outp.add(ship2Button);
		outp.add(ship3Button);
		outp.add(myshipButton);
		outp.add(allButton);

		JLabel borderLabel = new JLabel("Ship Borders :");
		borderLabel.setFont(new Font("Garamond", Font.BOLD, 16));
		ButtonGroup borderGroup = new ButtonGroup();
		JRadioButton borderYes = new JRadioButton("Add Borders");
		JRadioButton borderNo = new JRadioButton("No Borders");

		borderGroup.add(borderYes);
		borderGroup.add(borderNo);
		outp.add(borderLabel);
		outp.add(borderYes);
		outp.add(borderNo);
		
		colorChooser = new JColorChooser();
		colorChooser.setBorder(BorderFactory.createTitledBorder(
                "Choose Text Color"));
		colorChooser.getSelectionModel().addChangeListener(this);
		outp.add(colorChooser);

		//ButtonGroup colorGroup = new ButtonGroup();

		//JRadioButton colorBlackButton = new JRadioButton("Black");
		//JRadioButton colorGreenButton = new JRadioButton("Green");
		//JRadioButton colorBlueButton = new JRadioButton("Blue");
		//JRadioButton colorOrangeButton = new JRadioButton("Orange");
		//JRadioButton colorTransButton = new JRadioButton("Transparent");

		//colorGroup.add(colorBlueButton);
		//colorGroup.add(colorBlackButton);
		//colorGroup.add(colorGreenButton);
		//colorGroup.add(colorTransButton);
		//colorGroup.add(colorOrangeButton);

		//outp.add(colorBlueButton);
		//outp.add(colorBlackButton);
		//outp.add(colorGreenButton);
		//outp.add(colorTransButton);
		//outp.add(colorOrangeButton);

		// Plugging the output panel into the main frame
		content.add(outp, BorderLayout.EAST);

		// Showing the whole window
		setVisible(true);

		// Start Timer
		Timer timer = new Timer(100, this); // 100 milliseconds
		timer.start();
	}

	// returns whether the game has been paused,
	// hasn't even started, or is presently running
	// public int returnState() {
	// return stateButton.currentState();
	// }

	// Animation segment
	public void actionPerformed(ActionEvent e) {
		// only run this segment when the game
		// is currently running
		if (state.currentState() == 2) {
			canvas.runningState();
			canvas.repaint();
		}
	}
	
    public void stateChanged(ChangeEvent e) {
        canvas.changeColor(colorChooser.getColor());
    }

	public void keyPressed(KeyEvent e) {
		// using the escape character to exit

		/*
		 * This section is not working. Need to see a TA
		 */

		/*
		 * System.out.println("keypressed"); if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		 * System.exit(0); else if (e.getKeyCode() == KeyEvent.VK_UP) {
		 * combo.setSelectedItem("North"); } else if (e.getKeyCode() ==
		 * KeyEvent.VK_DOWN) { combo.setSelectedItem("South"); } else if (e.getKeyCode()
		 * == KeyEvent.VK_LEFT) { combo.setSelectedItem("West"); } else if
		 * (e.getKeyCode() == KeyEvent.VK_RIGHT) { combo.setSelectedItem("East"); }
		 * 
		 */

	}

	public void keyReleased(KeyEvent e) {
	}

	public void keyTyped(KeyEvent e) {
	}
}
