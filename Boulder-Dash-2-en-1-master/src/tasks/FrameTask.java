package tasks;

import main.Coeur;
import vue.Fenetre;

public class FrameTask implements Runnable {

	private Fenetre fen;

	public FrameTask() {
		this.fen = Coeur.FENETRE;
	}

	public void run() {
		try {
			fen.repaint();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}