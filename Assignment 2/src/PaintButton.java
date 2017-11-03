import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaintButton extends JButton implements ActionListener {

	private MapCanvas tempCanvas;
	private String label;
	private String direction;

	private stateHandler pausedstate;
	private Main FrameMain;

	public PaintButton(String label, MapCanvas temppanel, String direction, stateHandler state, Main Frame) {
		tempCanvas = temppanel;
		this.label = label;
		FrameMain = Frame;
		pausedstate = state;
		setText(this.label);
		this.direction = direction;
		addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		if (label.equals("Reset")) {
			if (pausedstate.currentState() == 1 || pausedstate.currentState() == 0) {
				tempCanvas.startNew(this.direction);
				tempCanvas.repaint();
			} else if (pausedstate.currentState() == 2) {

				JOptionPane.showMessageDialog(FrameMain,
						"Cannot Reset, process already running. Please stop and try again.", "Inane error",
						JOptionPane.ERROR_MESSAGE);
			}
		}

		if (label.equals("Start")) {
			label = "Stop";
			this.setText(label);
			if (pausedstate.currentState() == 0) {
				tempCanvas.startNew(this.direction);
				tempCanvas.repaint();
			} else if (pausedstate.currentState() == 1) {
			}
			pausedstate.flipState();
		} else if (label.equals("Stop")) {
			label = "Start";
			setText(label);
			pausedstate.flipState();
			// System.out.println("Paused inside stop : " + pausedstate);
		}
	}

}
