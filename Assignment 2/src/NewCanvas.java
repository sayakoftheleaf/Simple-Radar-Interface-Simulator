import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class NewCanvas extends JPanel{

	// Marks the center of the map
    private static Point center;
    // The starting position of our app in relation to the computer monitor
	private static Point start;
    // Radius of our circular map
	private static int radius;
    // This is the navigable Ship
	private static Ships MyShip;
    // These are the randomly navigated ships
	private static Ships[] ships;
    // This handles navigation of MyShip
	private JComboBox<String> tempComboBox;
	
    // constructor
	public NewCanvas(JComboBox<String> tempComboBox) {		
        start = new Point(50,50);
		radius = 400;
		center = new Point((int)start.getX() + radius, (int)start.getY() + radius);
        // this ship always starts at the center
		MyShip = new Ships(center, radius, (String) tempComboBox.getSelectedItem());
		ships = new Ships[3];
		this.tempComboBox = tempComboBox;
		
		for (int a = 0; a < ships.length; a++) {
			ships[a] = new Ships(center, radius);
		}
	}
	
    // returns the center of the map
    public Point getCenter() {
    	return center;
    }
    
    // returns the radius of the map
    public int getRadius() {
    	return radius;
    }
    
    // returns a Ships Object given a marker
    // 0-2 for the randomly generated ships
    // 3 for the navigable ship
    public Ships getShip(int marker) {
    	Ships temp = ships[0];
    	switch(marker) {
    		case 0: temp = ships[0];
    			break;
    		case 1: temp = ships[1];
    			break;
    		case 2: temp = ships[2];
    			break;
    		case 3: temp = MyShip;
    			break;
    	}
    	return temp;			
    }
	
    // This is the draw callback
    public void paintComponent (Graphics g) {
    	super.paintComponent(g);
		paintBorders(g);
		if (MyShip.isVisible()) {
			paintMyShip(g, MyShip.getPos());
		}
		for (int a = 0; a < 3; a++) {
			if (ships[a].isVisible()) {
				paintRShip(g, ships[a].getPos());
			}
		}
    }
    
    // Controls behavior at the starting state
    // i.e. when program is fired up or reset is hit
    public void startNew(String temp_dir) {
        // reassignment to objects
    	for (int a = 0; a < ships.length; a++) {
			ships[a] = new Ships(center,radius);
    		ships[a].setVisible(true);
    	}
    	MyShip = new Ships(center,radius, (String) tempComboBox.getSelectedItem());
    	MyShip.setVisible(true);
    }

    // Controls behavior when the program is running
    // This is the function repeatedly called during animation
    public void runningState() {
    	for (int a = 0; a < ships.length; a++) {
			ships[a].changeLocation();
    	}
        // live navigation based on the user selection
    	MyShip.setDirection((String) tempComboBox.getSelectedItem());
    	MyShip.changeLocation();
    }
    
    // paints the map
    public void paintBorders(Graphics g) {
    	g.drawOval(start.x,start.y,(radius*2), (radius*2));
    	g.drawLine(start.x, start.y + radius, (start.x + (radius *2)), start.y + radius);
    	g.drawLine (start.x + radius, start.y, start.x + radius, (start.y + (radius * 2)));
    }
    
    // paints the navigable ship
    public void paintMyShip(Graphics g, Point loc) {
    	g.drawLine(loc.x - 10, loc.y + 10, loc.x + 10, loc.y + 10);
    	g.drawLine(loc.x - 10, loc.y + 10, loc.x, loc.y - 10);
    	g.drawLine(loc.x + 10, loc.y + 10, loc.x, loc.y - 10);
    }
    
    // paints the randomized ships
    public void paintRShip(Graphics g, Point loc) {
    	g.drawOval(loc.x, loc.y, 10, 10);
    }
}
