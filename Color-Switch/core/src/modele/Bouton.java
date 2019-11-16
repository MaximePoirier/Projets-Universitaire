package modele;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;

/**
 * le modele du bouton
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Bouton{
	/**
	 * la position du bouton
	 */
	private Vector2 position;
	/**
	 * le cercle correspondant à la hitbox du bouton
	 */
	private Circle hitBox;
	/**
	 * la taille du bouton
	 */
	private float taille;
	
	/**
	 * crée un bouton
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 * @param taille
	 * la taille
	 */
	public Bouton(float x, float y, float taille) {
		position = new Vector2(x,y);
		this.taille = taille;
		hitBox = new Circle(position, taille);
	}

	/**
	 * retourne la position
	 * @return la position
	 */
	public Vector2 getPosition() {
		return position;
	}

	
	/**
	 * retourne la hitbox
	 * @return la hitbox
	 */
	public Circle getHitBox() {
		return hitBox;
	}
	
	/**
	 * retourne la taille
	 * @return la taille
	 */
	public float getTaille() {
		return taille;
	}
}
