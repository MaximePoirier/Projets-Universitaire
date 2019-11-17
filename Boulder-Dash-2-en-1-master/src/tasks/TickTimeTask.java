package tasks;

import main.Coeur;

public class TickTimeTask implements Runnable {
	public TickTimeTask() {

	}

	public void run() {
		try {
			Coeur.partieActuelle.tickTemps();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
