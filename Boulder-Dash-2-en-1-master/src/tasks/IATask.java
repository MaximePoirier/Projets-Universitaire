package tasks;

import main.Coeur;

public class IATask implements Runnable {
	public IATask() {

	}

	public void run() {
		try {

			if(Coeur.ia.prete()){
			Coeur.tick();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
