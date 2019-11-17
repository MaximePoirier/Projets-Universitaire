package vue;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import main.Coeur;

public class Fenetre extends JFrame implements KeyListener {

	private String titre = "Boulder Dash 2 en 1";

	public Fenetre(int width, int height) {
		this.setTitle(titre);
		this.setSize(width, height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setResizable(false);
		this.addKeyListener(this);
		this.setVisible(true);
	}

	public void keyTyped(KeyEvent e) {
		Coeur.controleur.keyTyped(e);
	}

	public void keyPressed(KeyEvent e) {
		Coeur.controleur.keyPressed(e);
	}

	public void keyReleased(KeyEvent e) {
		Coeur.controleur.keyReleased(e);
	}

}
