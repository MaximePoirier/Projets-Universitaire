package tasks;

import main.Coeur;

public class TickTask implements Runnable {
	public TickTask() {

	}

	public void run() {
		try {
			Coeur.tick();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}