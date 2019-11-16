package modele;

import com.badlogic.gdx.Gdx;
import com.gdx.colorswitch.ColorSwitch;

/**
 * le modele du menu reset
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuResetWorld {
	/**
	 * le tableau des boutons du menu reset
	 */
	private Bouton[] boutons;
	/**
	 * le nombre de boutons du menu reset
	 */
	private int nbBoutons = 2;
	/**
	 * le score de la partie terminée
	 */
	private int score;
	
	/**
	 * crée tous les boutons
	 * @param score
	 * le score de la partie terminée
	 */
	public MenuResetWorld(int score) {
		boutons = new Bouton[nbBoutons];
		boutons[0] = new Bouton(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()*(3f/4f),86*ColorSwitch.ratioTailleEcran);
		boutons[1] = new Bouton(Gdx.graphics.getWidth()/6,Gdx.graphics.getHeight()/9,42*ColorSwitch.ratioTailleEcran);
		this.score = score;
	}
	
	/**
	 * retourne le tableau de boutons
	 * @return le tableau de boutons
	 */
	public Bouton[] getBoutons() {
		return boutons;
	}

	/**
	 * retourne le score de la partie terminée
	 * @return le score de la partie terminée
	 */
	public int getScore() {
		return score;
	}

}
