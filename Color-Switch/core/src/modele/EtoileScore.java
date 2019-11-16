package modele;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.gdx.colorswitch.ColorSwitch;

/**
 * le modele d'une etoile score
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class EtoileScore {
	/**
	 * la position de l'etoile
	 */
	private Vector2 position;
	/**
	 * le rayon de l'etoile
	 */
	private float rayon;
	/**
	 * la hitbox de l'etoile
	 */
	private Circle cercle;
	/**
	 * si l'existe ou non
	 */
	private boolean vivant;

	/**
	 * cree une etoile
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 */
	public EtoileScore(float x, float y) {
		position = new Vector2(x,y);
		rayon = 20*ColorSwitch.ratioTailleEcran;
		cercle = new Circle(position,rayon);
		vivant = true;
	}
	
	/**
	 * le scrolling de l'etoile lorsque la bille monte haut
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que l'etoile doit parcourir
	 */
	public void Move(float delta, float hauteur) {
		Vector2 v = new Vector2(position.x,position.y);
		v.add(new Vector2(0, -hauteur).scl(delta));
		position.y = v.y;
		cercle.y = position.y;
	}
		
	/**
	 * retourne la position
	 * @return la position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * retourne le rayon
	 * @return le rayon
	 */
	public float getRayon() {
		return rayon;
	}

	/**
	 * retourne la hitbox
	 * @return la hitbox
	 */
	public Circle getCercle() {
		return cercle;
	}
	
	/**
	 * met à jour la position
	 * @param y
	 * la nouvelle position en y
	 */
	public void setPosition(float y) {
		this.position.y = y;
	}

	/**
	 * retourne si l'etoile existe ou non
	 * @return si l'etoile existe ou non
	 */
	public boolean isVivant() {
		return vivant;
	}

	/**
	 * met à jour si l'etoile existe ou non
	 * @param vivant
	 * l'etoile existe ou non
	 */
	public void setVivant(boolean vivant) {
		this.vivant = vivant;
	}
	
}
