package modele;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.gdx.colorswitch.ColorSwitch;

/**
 * le modele de l'objet qui permet de changer la couleur de la bille
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class ChangeColor {
	/**
	 * la position du change color
	 */
	private Vector2 position;
	/**
	 * le rayon du change color
	 */
	private float rayon;
	/**
	 * la hitbox du change color
	 */
	private Circle cercle;
	
	/**
	 * crée un change color
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 */
	public ChangeColor(float x, float y) {
		position = new Vector2(x,y);
		rayon = 20*ColorSwitch.ratioTailleEcran;
		cercle = new Circle(position,rayon);
	}
	
	/**
	 * le scrolling du change color lorsque la bille monte haut
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que le change color doit parcourir
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
	 * retourne la hitbox du change color
	 * @return la hitbox du change color
	 */
	public Circle getCercle() {
		return cercle;
	}

	/**
	 * met à jour la position
	 * @param y
	 * la nouvelle position
	 */
	public void setPosition(float y) {
		this.position.y = y;
	}
	
	
}
