import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

public class NewCanvas extends JPanel{

	private static Point center;
	private static Point start;
	private static int radius;
	private static Ships MyShip;
	private static Ships[] ships;
	
	private Main parent;
	
	public NewCanvas(Main parent) {
		start = new Point(50,50);
		radius = 400;
		center = new Point((int)start.getX() + radius, (int)start.getY() + radius);
		MyShip = new Ships(center, radius, "North");
		ships = new Ships[3];
		this.parent = parent;
		
		for (int a = 0; a < ships.length; a++) {
			ships[a] = new Ships(center, radius);
			//System.out.println("NewCanvas " + ships[a].getPos());
		}
	}
	
    public Point getCenter() {
    	return center;
    }
    
    public int getRadius() {
    	return radius;
    }
	
    // This is the draw callback
    public void paintComponent (Graphics g) {
    	//System.out.println("Inside here");
    	super.paintComponent(g);
		paintBorders(g);
		if (MyShip.isVisible()) {
			paintMyShip(g, MyShip.getPos());
		}
		for (int a = 0; a < 3; a++) {
			if (ships[a].isVisible()) {
				//System.out.print(ships[a].getPos());
				paintRShip(g, ships[a].getPos());
			}
		}
    }
    
    public void startNew(String temp_dir) {
    	for (int a = 0; a < ships.length; a++) {
			ships[a] = new Ships(center,radius);
    		ships[a].setVisible(true);
    	}
    	MyShip = new Ships(center,radius,"North");
    	System.out.println("new Ship pos : "+ MyShip.getPos());
    	System.out.println(getCenter());
    	MyShip.setVisible(true);
    }
    
    public void runningState() {
    	for (int a = 0; a < ships.length; a++) {
			ships[a].changeLocation();
			//System.out.println(ships[a].getPos());
    	}
    	MyShip.changeLocation();
    }
    
    public void paintBorders(Graphics g) {
    	g.drawOval(start.x,start.y,(radius*2), (radius*2));
    	g.drawLine(start.x, start.y + radius, (start.x + (radius *2)), start.y + radius);
    	g.drawLine (start.x + radius, start.y, start.x + radius, (start.y + (radius * 2)));
    }
    
    public void paintMyShip(Graphics g, Point loc) {
    	g.drawLine(loc.x - 10, loc.y + 10, loc.x + 10, loc.y + 10);
    	g.drawLine(loc.x - 10, loc.y + 10, loc.x, loc.y - 10);
    	g.drawLine(loc.x + 10, loc.y + 10, loc.x, loc.y - 10);
    }
    
    public void paintRShip(Graphics g, Point loc) {
    	g.drawOval(loc.x, loc.y, 10, 10);
    }
}
