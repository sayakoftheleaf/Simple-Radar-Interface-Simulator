import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LocationButton extends JButton implements ActionListener {

	private int marker;
	private MapCanvas tempCanvas;
	private Ships tempShip;

	public LocationButton(String label, int marker, MapCanvas tempcanvas) {
		setText(label);
		this.marker = marker;
		tempCanvas = tempcanvas;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		// Only case 4 needs to be handled, because it needs to display
		// the locations of all the ships. All other cases are similar.
		switch (marker) {
		case 4:
			tempShip = tempCanvas.getShip(0);
			System.out.println(
					"Ship 1 is at : [ x = " + tempShip.getPos().getX() + ", y = " + tempShip.getPos().getY() + "]");
			tempShip = tempCanvas.getShip(1);
			System.out.println(
					"Ship 2 is at :  [ x = " + tempShip.getPos().getX() + ", y = " + tempShip.getPos().getY() + "]");
			tempShip = tempCanvas.getShip(2);
			System.out.println(
					"Ship 3 is at :  [ x = " + tempShip.getPos().getX() + ", y = " + tempShip.getPos().getY() + "]");
			tempShip = tempCanvas.getShip(3);
			System.out.println(
					"This ship is at  [ x = " + tempShip.getPos().getX() + ", y = " + tempShip.getPos().getY() + "]");
			break;
		default:
			tempShip = tempCanvas.getShip(marker);
			System.out.println("[ x = " + tempShip.getPos().getX() + ", y = " + tempShip.getPos().getY() + "]");
			break;
		}
	}
}
