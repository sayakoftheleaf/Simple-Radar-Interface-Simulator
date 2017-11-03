import java.awt.*;

abstract class Ships {

	// Controls the visibility of the maps
	// necessary for the initial start state
	protected boolean visbool;

	// keeps track of the current direction
	// local representations of the directions
	// north = 1, south = 2, east = 3, west = 4
	protected int direction;

	// Keeps a record of the present location of the ship
	protected Point location;
	// Local copies of the center and radiuses of the map
	protected Point center;
	protected int radius;

	// This keeps track of collisions
	// NOTE : If a collision has happened once, it gives it a few timer
	// ticks to recover.
	// Otherwise, objects seem to be behaving in unexpected ways
	protected int collisioncounter;

	public Ships() {
		collisioncounter = 0;
	}

	// Increments the collision counter
	public void addCollision() {
		collisioncounter++;
	}

	// Resets the collision counter
	public void resetCollision() {
		collisioncounter = 0;
	}

	public int getCollision() {
		return collisioncounter;
	}

	public boolean isVisible() {
		return visbool;
	}

	public void setVisible(boolean temp) {
		visbool = temp;
	}

	public int getDirection() {
		return this.direction;
	}

	public void setPos(int new_x, int new_y) {
		location.x = new_x;
		location.y = new_y;
	}

	public Point getPos() {
		return location;
	}

	// updates the location for each animation timer
	public void changeLocation() {
		switch (direction) {
		case 1:
			location.y = location.y - 2;
			break;
		case 2:
			location.y = location.y + 2;
			break;
		case 3:
			location.x = location.x + 2;
			break;
		case 4:
			location.x = location.x - 2;
			break;
		}
	}
	abstract void setDirection();
}
