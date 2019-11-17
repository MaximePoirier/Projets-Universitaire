package vue;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

import main.Coeur;
import main.Partie;

public class ScorePanel extends JPanel {

	private Partie partie;

	public ScorePanel() {
		this.partie = Coeur.partieActuelle;

		setPreferredSize(new Dimension(Coeur.FENETRE.getWidth(), (Coeur.FENETRE.getHeight() / 20)));
	}

	protected void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		for (int i = 1; i <= 5; i++) {
			g.drawLine(getWidth() * i / 5, 0, getWidth() * i / 5, getHeight());
		}
		g.drawString("Diamants : " + partie.getNbDiamants() + "/" + partie.getNiveau().getDiamonds_required(),
				getWidth() / 15, getHeight() / 2);
		g.drawString("Score : " + partie.getScore(), getWidth() * 19 / 70, getHeight() / 2);
		g.drawString("Temps restant : " + partie.getTemps_restant(), getWidth() * 45 / 100, getHeight() / 2);
		g.drawString("Niveau : " + (Coeur.niveau + 1) + "/" + Coeur.ensembleDeNiveau.getNombre_de_niveaux(),
				getWidth() * 2 / 3, getHeight() / 2);
		g.drawString("Points/diamant : " + (Coeur.partieActuelle.getNiveau().getDiamond_value()),
				(getWidth() * 42) / 50, getHeight() * 12 / 30);
		g.drawString("(bonus) : " + Coeur.partieActuelle.getNiveau().getDiamond_value_bonus(), (getWidth() * 42) / 50,
				getHeight() * 22 / 30);
	}
}
