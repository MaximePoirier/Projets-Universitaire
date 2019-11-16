package modele;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * arc de cercle des obstacles ayant des cercles
 * @author Dany Br�geon, Lo�s Monet, Maxime Poirier
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
	 * l'angle de d�part de l'arc
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
	 * cr�e un arc
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 * @param rayon
	 * le rayon de l'arc
	 * @param angleDepart
	 * l'angle de d�part
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
	 * retourne l'angle de d�part
	 * @return l'angle de d�part
	 */
	public float getAngleDepart() {
		return angleDepart;
	}

	/**
	 * met � jour l'angle de d�part
	 * @param angleDepart
	 * l'angle de d�part
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
