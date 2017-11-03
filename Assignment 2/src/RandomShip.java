import java.awt.Point;

public class RandomShip extends Ships {
	// constructor for the randomized ships
	public RandomShip(Point ctr, int radius) {
		super();
		visbool = false;
		center = new Point(ctr);
		super.radius = radius;
		super.location = generateLocation();
		setDirection();
	}

	public void setDirection() {
		int tempdir = directionGenerator();
		if (this.direction == tempdir)
			setDirection();
		else
			this.direction = tempdir;
	}

	// generates a random direction
	public int directionGenerator() {
		return (generaterandom(5, 0));
	}

	// generates a random location on the map
	public Point generateLocation() {

		int minx = (int) center.getX() - radius;
		int miny = (int) center.getY() - radius;
		int maxx = (int) center.getX() + radius;
		int maxy = (int) center.getY() + radius;

		int randomx = generaterandom(maxx, minx);
		int randomy = generaterandom(maxy, miny);

		// recursive call to generate something within the bounds of the map
		while (!(checkBounds(randomx, randomy, (int) center.getX(), (int) center.getY())))
			return generateLocation();

		return (new Point(randomx, randomy));

	}

	// generates a random number within a range
	public int generaterandom(int max, int min) {
		int temp = ((int) (Math.random() * max)) + min;

		// recursive call for edge cases when Math.random
		// doesn't give numbers within the range
		if (temp >= max || temp <= min)
			temp = generaterandom(max, min);

		return temp;
	}

	// checks whether a given point is within the bounds of the map
	public boolean checkBounds(int x, int y, int x2, int y2) {
		return ((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)) < ((radius - 15) * (radius - 15));

	}
}
