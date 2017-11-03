
public class stateHandler {

	// records the current running state of the engine
	// 1 if game is paused
	// 0 if game is beginning
	// 2 if game is running
	public int pausedState;
	
	public stateHandler() {
		pausedState = 0;
	}
	
	public int currentState() {
		return pausedState;
	}

	public void flipState() {
		if (pausedState == 0)
			pausedState = 2;
		else if (pausedState == 1)
			pausedState = 2;
		else if (pausedState == 2)
			pausedState = 1;
	}
	
}
