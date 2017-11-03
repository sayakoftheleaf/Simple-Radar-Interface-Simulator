import java.awt.*;
import javax.swing.*;

public class MapCanvas extends JPanel {

	// Marks the center of the map
	private static Point center;

	// The starting position of our app in relation to the computer monitor
	private static Point start;

	// Radius of our circular map
	private static int radius;

	// This is the navigable Ship
	// NOTE : Tried declaring this as the abstract type Ship, but program is
	// is giving weird errors
	private static NavShip MyShip;

	// These are the randomly navigated ships
	private static Ships[] ships;
	
	private Color shipColor;

	// This handles navigation of MyShip
	private JComboBox<String> tempComboBox;

	private stateHandler gamestate;
	private Main Frame;
	private int CollisionWarning;

	// constructor
	public MapCanvas(JComboBox<String> tempComboBox, stateHandler tempstate, Color shipcolor, Main Frame) {

		shipColor = shipcolor;
		gamestate = tempstate;
		this.Frame = Frame;
		CollisionWarning = 0;

		start = new Point(50, 50);
		radius = 400;
		center = new Point((int) start.getX() + radius, (int) start.getY() + radius);
		// this ship always starts at the center
		MyShip = new NavShip(center, radius, (String) tempComboBox.getSelectedItem());
		ships = new Ships[3];
		this.tempComboBox = tempComboBox;

		for (int a = 0; a < ships.length; a++) {
			ships[a] = new RandomShip(center, radius);
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
		switch (marker) {
		case 0:
			temp = ships[0];
			break;
		case 1:
			temp = ships[1];
			break;
		case 2:
			temp = ships[2];
			break;
		case 3:
			temp = MyShip;
			break;
		}
		return temp;
	}

	// This is the draw callback
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		paintBorders(g);
		collisionHandler();
		mapCollision();
		if (MyShip.isVisible()) {
			paintMyShip(g, MyShip.getPos(), MyShip.getDirection());
		}
		for (int a = 0; a < 3; a++) {
			if (ships[a].isVisible()) {
				paintRShip(g, ships[a].getPos(), Color.GREEN);
			}
		}
	}

	// Controls behavior at the starting state
	// i.e. when program is fired up or reset is hit
	public void startNew(String temp_dir) {
		// reassignment to objects
		for (int a = 0; a < ships.length; a++) {
			ships[a] = new RandomShip(center, radius);
			ships[a].setVisible(true);
		}
		MyShip = new NavShip(center, radius, (String) tempComboBox.getSelectedItem());
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

	public void collisionHandler() {

		for (int a = 0; a < 3; a++) {

			if (!(a == 2)) {
				if (ships[a].getCollision() == 0) {
					if (collisionDetector(ships[a], ships[a + 1])) {
						ships[a].setDirection();
						ships[a + 1].setDirection();
						ships[a].addCollision();
						ships[a + 1].addCollision();
					} else if (collisionDetector(ships[a])) {
						ships[a].setDirection();
						ships[a].addCollision();
					}
				} else if (ships[a].getCollision() == 5) {
					ships[a].resetCollision();
				} else
					ships[a].addCollision();
			} else {
				if (ships[a].getCollision() == 0) {
					if (collisionDetector(ships[a], ships[0])) {
						ships[a].setDirection();
						ships[0].setDirection();
						ships[a].addCollision();
						ships[0].addCollision();
					} else if (collisionDetector (ships[a])) {
						ships[a].setDirection();
						ships[a].addCollision();
					}
				} else if (ships[a].getCollision() == 5) {
					ships[a].resetCollision();
				} else
					ships[a].addCollision();
			}
		}

	}

	public boolean collisionDetector(Ships ship1, Ships ship2) {
		double dx = ship1.getPos().getX() - ship2.getPos().getX();
		double dy = ship1.getPos().getY() - ship2.getPos().getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		if (dist < 40)
			return true;
		else
			return false;

	}
	
	public boolean collisionDetector(Ships ship1) {
		double dx = ship1.getPos().getX() - MyShip.getPos().getX();
		double dy = ship1.getPos().getY() - MyShip.getPos().getY();
		double dist = Math.sqrt(dx * dx + dy * dy);

		if (dist < 70)
			return true;
		else
			return false;

	}

	public void mapCollision() {
		for (int a = 0; a < 3; a++) {
			double dx = ships[a].getPos().getX() - center.getX();
			double dy = ships[a].getPos().getY() - center.getY();

			double dist = Math.sqrt(dx * dx + dy * dy);

			if (ships[a].getCollision() == 0) {
				if (dist > (radius - 40)) {
					ships[a].setDirection();
					ships[a].addCollision();
				}
			} else if (ships[a].getCollision() == 1 || ships[a].getCollision() == 2) {
				ships[a].addCollision();
			} else if (ships[a].getCollision() == 3) {
				ships[a].resetCollision();
			}
		}
		
		/* 
		 * This section is not working. Need to see a TA
		 */

		// double dx = MyShip.getPos().getX() - center.getX();
		// double dy = MyShip.getPos().getY() - center.getY();

		// double dist = Math.sqrt(dx * dx + dy * dy);

		// if (dist > (radius - 40)) {
		//if (CollisionWarning == 0) {
		//	gamestate.flipState();
		//	JOptionPane.showMessageDialog(Frame,
		//			"Your Ship is about to collide with terrain. Game paused. Please change direction and Start.",
		//			"Inane error", JOptionPane.ERROR_MESSAGE);
		//} else if (CollisionWarning == 5) {
		//	CollisionWarning = 0;
		//} else {
		//	if (gamestate.currentState() == 1)
		//		gamestate.flipState();
		//	CollisionWarning++;
		//}
	}

	// paints the map
	public void paintBorders(Graphics g) {
		g.drawOval(start.x, start.y, (radius * 2), (radius * 2));
		g.drawLine(start.x, start.y + radius, (start.x + (radius * 2)), start.y + radius);
		g.drawLine(start.x + radius, start.y, start.x + radius, (start.y + (radius * 2)));
	}

	// paints the navigable ship
	public void paintMyShip(Graphics g, Point loc, int direction) {
		if (direction == 1) {
			g.drawLine(loc.x - 10, loc.y + 10, loc.x + 10, loc.y + 10);
			g.drawLine(loc.x - 10, loc.y + 10, loc.x, loc.y - 10);
			g.drawLine(loc.x + 10, loc.y + 10, loc.x, loc.y - 10);
		} else if (direction == 3) {
			g.drawLine(loc.x - 10, loc.y - 10, loc.x - 10, loc.y + 10);
			g.drawLine(loc.x - 10, loc.y - 10, loc.x + 10, loc.y);
			g.drawLine(loc.x - 10, loc.y + 10, loc.x + 10, loc.y);
		} else if (direction == 2) {
			g.drawLine(loc.x + 10, loc.y - 10, loc.x - 10, loc.y - 10);
			g.drawLine(loc.x + 10, loc.y - 10, loc.x, loc.y + 10);
			g.drawLine(loc.x - 10, loc.y - 10, loc.x, loc.y + 10);
		} else if (direction == 4) {
			g.drawLine(loc.x + 10, loc.y + 10, loc.x + 10, loc.y - 10);
			g.drawLine(loc.x + 10, loc.y + 10, loc.x - 10, loc.y);
			g.drawLine(loc.x + 10, loc.y - 10, loc.x - 10, loc.y);
		}
	}
	
	public void changeColor(Color tempcolor) {
		shipColor = tempcolor;
	}

	// paints the randomized ships
	public void paintRShip(Graphics g, Point loc, Color somecolor) {
		g.setColor(Color.BLACK);
		g.drawOval(loc.x, loc.y, 10, 10);
		g.setColor(shipColor);
		g.fillOval(loc.x, loc.y, 10, 10);
	}
}
