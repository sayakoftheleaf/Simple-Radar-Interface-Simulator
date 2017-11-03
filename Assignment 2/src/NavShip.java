import java.awt.Point;

public class NavShip extends Ships {

	// constrcutor overloading
	// this constructor is for the navigable ship
	public NavShip(Point ctr, int radius, String dir) {

		super();
		// not visible yet
		visbool = false;
		super.location = new Point(ctr);
		super.center = new Point(ctr);
		super.radius = radius;
		setDirection(dir);
	}

	// will override this function with the local function
	public void setDirection() {}
	
	// Converts the Strings into the local
	public void setDirection(String dir) {
		if (dir.equals("North"))
			super.direction = 1;
		else if (dir.equals("South"))
			super.direction = 2;
		else if (dir.equals("East"))
			super.direction = 3;
		else if (dir.equals("West"))
			super.direction = 4;
	}

}
