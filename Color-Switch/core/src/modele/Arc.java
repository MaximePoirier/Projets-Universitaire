package modele;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * arc de cercle des obstacles ayant des cercles
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Arc {
	/**
	 * la position de l'arc
	 */
	private Vector2 position;
	/**
	 * le rayon de l'arc
	 */
	private float rayon;
	/**
	 * l'angle de départ de l'arc
	 */
	private float angleDepart;
	/**
	 * l'angle actuel de l'arc
	 */
	private float angle;
	/**
	 * la couleur de l'arc
	 */
	private Color couleur;
	
	/**
	 * crée un arc
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 * @param rayon
	 * le rayon de l'arc
	 * @param angleDepart
	 * l'angle de départ
	 * @param angle
	 * l'angle actuel
	 * @param couleur
	 * la couleur
	 */
	public Arc(float x, float y, float rayon, float angleDepart, float angle, Color couleur) {
		position = new Vector2(x,y);
		this.rayon = rayon;
		this.angleDepart = angleDepart;
		this.angle = angle;
		this.couleur = couleur;
	}

	/**
	 * retourne l'angle de départ
	 * @return l'angle de départ
	 */
	public float getAngleDepart() {
		return angleDepart;
	}

	/**
	 * met à jour l'angle de départ
	 * @param angleDepart
	 * l'angle de départ
	 */
	public void setAngleDepart(float angleDepart) {
		this.angleDepart = angleDepart;
	}

	/**
	 * retourne la couleur de l'arc
	 * @return la couleur de l'arc
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * retourne la position de l'arc
	 * @return la position de l'arc
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * retourne le rayon de l'arc
	 * @return le rayon de l'arc
	 */
	public float getRayon() {
		return rayon;
	}

	/**
	 * retourne l'angle de l'arc
	 * @return l'angle de l'arc
	 */
	public float getAngle() {
		return angle;
	}
	
}
