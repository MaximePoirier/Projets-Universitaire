package vue;

import java.awt.Color;
import java.awt.Graphics;
import java.util.List;

import javax.swing.JPanel;

import main.Coeur;

public class FinPanel extends JPanel {

	private List<Integer> scores;

	public FinPanel() {
		this.scores = Coeur.SCORES;
	}

	protected void paintComponent(Graphics g) {
		g.setColor(new Color(88, 41, 0));
		g.fillRect(0, 0, getWidth(), getHeight());
		g.setColor(Color.white);
		int compteur = 1;
		int somme=0;
		g.drawString("FIN DU JEU, SCORES :", getWidth() / 6, getWidth()/10);
		for (Integer i : scores) {
			g.drawString("Niveau " + (compteur) + " : " + i, getWidth() / 3,
					getHeight() * compteur / (scores.size()*2));
			somme+=i;
			compteur++;
		}
		compteur++;
		g.drawString("TOTAL : "+somme, getWidth() / 6, getWidth()/5);
	}
}