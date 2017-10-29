import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PaintButton extends JButton implements ActionListener{
	
	protected NewCanvas tempCanvas;
	protected String label;
	protected String direction;
	
	// records the current running state of the engine
	// true if game is paused
	// false if game is beginning or running
	protected boolean pausedstate;
	protected Main FrameMain;
	
	public PaintButton(String label, NewCanvas temppanel, String direction, Main Frame){
		tempCanvas = temppanel;
		this.label = label;
		pausedstate = false;
		FrameMain = Frame;
		setText(this.label);
		this.direction = direction;
		addActionListener(this);
	}
	public boolean currentState() {
		return pausedstate;
	}
	
	public void flipState() {
		pausedstate = !pausedstate;
	}
	public void actionPerformed (ActionEvent e) {
		if (label.equals("Reset")) {
			if (FrameMain.returnState() == false) {
				System.out.println("Insideresetfalse : " + false);
				tempCanvas.startNew(this.direction);
				tempCanvas.repaint();
			} else if (FrameMain.returnState() == true) {
				System.out.println("Insideresetfalse : " + true);
				JOptionPane.showMessageDialog(FrameMain,
					    "Cannot Reset, process already running. Please stop and try again.",
					    "Inane error",
					    JOptionPane.ERROR_MESSAGE);
			}
		}
		
		if (label.equals("Start")) {
			label = "Stop";
			this.setText(label);
			if (pausedstate == false) {
				tempCanvas.startNew(this.direction);
				tempCanvas.repaint();
			}
			//else if (pausedstate == true) {}
			flipState();
		}
		else if (label.equals("Stop")) {
			label = "Start";
			setText(label);
			flipState();
			System.out.println("Paused inside stop : " + pausedstate);
		}
	}

}
