package modele;

import com.badlogic.gdx.math.Vector2;
import com.gdx.colorswitch.ColorSwitch;

/**
 * le modele de la lave
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Lave {
	/**
	 * la position de la lave
	 */
	private Vector2 position;
	/**
	 * la vitesse de montée de la lave
	 */
	private float vitesse;
	/**
	 * la distance maximale entre la lave et le personnage
	 */
	private int distanceMaxPersonnage;
	/**
	 * la hauteur maximale que peut atteindre la lave
	 */
	private int hauteurMaxLave;
	
	/**
	 * crée la lave
	 * @param x
	 * la position x
	 * @param y
	 * la position y
	 * @param vitesse
	 * la vitesse
	 * @param distanceMaxPersonnage
	 * la distance maximale avec la bille
	 */
	public Lave(int x, int y, float vitesse, int distanceMaxPersonnage) {
		position = new Vector2(x,y);
		this.vitesse = vitesse;
		this.distanceMaxPersonnage = distanceMaxPersonnage;
		hauteurMaxLave = (int) (748*ColorSwitch.ratioTailleEcran);
	}
	
	/**
	 * le scrolling de l'obstacle lorsque la bille monte haut et la montée de la lave
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que l'obstacle doit parcourir
	 */
	public void Move(float delta, float hauteur) {
		Vector2 v = new Vector2(position.x,position.y);
		Vector2 v2 = new Vector2(0, -hauteur).scl(delta);
		v.add(v2);
		position.y = v.y-vitesse;
	}

	/**
	 * retourne la position de la lave
	 * @return la position de la lave
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * met à jour la position de la lave
	 * @param y
	 * la nouvelle position en y de la lave
	 */
	public void setPosition(int y) {
		position = new Vector2(position.x, y);
	}

	/**
	 * retourne la vitesse de la lave
	 * @return la vitesse de la lave
	 */
	public float getVitesse() {
		return vitesse;
	}

	/**
	 * retourne la distance max avec la bille
	 * @return la distance max avec la bille
	 */
	public int getDistanceMaxPersonnage() {
		return distanceMaxPersonnage;
	}

	/**
	 * retourne la hauteur max de la lave
	 * @return la hauteur max de la lave
	 */
	public int getHauteurMaxLave() {
		return hauteurMaxLave;
	}	
	
}
