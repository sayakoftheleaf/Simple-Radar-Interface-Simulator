import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaintButton extends JButton implements ActionListener{
	
	private NewCanvas tempCanvas;
	private String label;
	private String direction;
	
	// records the current running state of the engine
	// 1 if game is paused
	// 0 if game is beginning
	// 2 if game is running
	private int pausedstate;
	private Main FrameMain;
	
	public PaintButton(String label, NewCanvas temppanel, String direction, Main Frame){
		tempCanvas = temppanel;
		this.label = label;
		pausedstate = 0;
		FrameMain = Frame;
		setText(this.label);
		this.direction = direction;
		addActionListener(this);
	}
	public int currentState() {
		return pausedstate;
	}
	
	public void flipState() {
		if (pausedstate == 0) pausedstate = 2;
		else if (pausedstate == 1) pausedstate = 2;
		else if (pausedstate == 2) pausedstate = 1;
	}
	public void actionPerformed (ActionEvent e) {
		if (label.equals("Reset")) {
			if (FrameMain.returnState() == 1 || FrameMain.returnState() == 0) {
				tempCanvas.startNew(this.direction);
				tempCanvas.repaint();
			} else if (FrameMain.returnState() == 2) {

				JOptionPane.showMessageDialog(FrameMain,
					    "Cannot Reset, process already running. Please stop and try again.",
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (label.equals("Start")) {
			label = "Stop";
			this.setText(label);
			if (pausedstate == 0) {
				tempCanvas.startNew(this.direction);
				tempCanvas.repaint();
			}
			else if (pausedstate == 1) {}
			flipState();
		}
		else if (label.equals("Stop")) {
			label = "Start";
			setText(label);
			flipState();
			//System.out.println("Paused inside stop : " + pausedstate);
		}
	}

}
