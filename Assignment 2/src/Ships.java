import java.awt.*;
import java.awt.geom.*;
import java.util.Random;

public class Ships {
	
	private boolean visbool;
	
	//north = 1, south = 2, east = 3, west = 4
	private int direction;
	
	private Point location;
	protected Point center;
	private int radius;
	
	public Ships (Point ctr, int radius, String dir) {	
		visbool = false;
		this.location = new Point(ctr);
		this.center = new Point(ctr);
		this.radius = radius; 
		setDirection(dir);
	}
	
	public Ships (Point ctr, int radius) {
		visbool = false;
		this.center = new Point(ctr);
		this.radius = radius;
		this.location = generateLocation();
		this.direction = directionGenerator();
	}
	
	public boolean isVisible() {
		return visbool;
	}
	
	public void setVisible(boolean temp) {
		visbool = temp;
	}
	
	public void setDirection(String dir) {
		if (dir.equals("North"))
			this.direction = 1;
		else if (dir.equals("South"))
			this.direction = 2;
		else if (dir.equals("East"))
			this.direction = 3;
		else if (dir.equals("West"))
			this.direction = 4;
	}

	public void setPos(int new_x, int new_y) {
		location.x = new_x;
		location.y = new_y;
	}
	
	public Point getPos() {
		return location;
	}
	
	public void changeLocation() {	
		//System.out.println("ChangeLocation");
		switch (direction) {
			case 1: location.y = location.y - 2;
				break;
			case 2: location.y = location.y + 2;
				break;
			case 3: location.x = location.x + 2;
				break;
			case 4: location.x = location.x - 2;
				break;
		}
			
		
	}
	
	public int directionGenerator() {
		return (generaterandom(4,1));
	}
	
	// generates a random location on the map
	public Point generateLocation() {
		
		int minx = (int) center.getX() - radius;
		int miny = (int) center.getY() - radius;
		int maxx = (int) center.getX() + radius;
		int maxy = (int) center.getY() + radius;
		
		int randomx = generaterandom (maxx, minx); 
		int randomy = generaterandom (maxy,miny);
		
		// recursive call to generate something within the bounds of the map
		while (!(checkBounds(randomx, randomy, (int) center.getX(), (int) center.getY())))
			return generateLocation();
		
		return (new Point(randomx, randomy));
		
	}
	
	//
	public int generaterandom (int max, int min) {
		int temp = ((int) (Math.random() * max)) + min;
		
		// recursive call for edge cases when Math.random
		// doesn't give numbers within the range
		if(temp >= max || temp <= min)
			temp = generaterandom(max, min);
		
		return temp;
	}
	
	// checks whether a given point is within the bounds of the map
	public boolean checkBounds (int x, int y, int x2, int y2) {
		return((x2 - x) * (x2 - x)) + ((y2 - y) * (y2 - y)) < ((radius - 15) * (radius - 15));
			
	}
	
}
