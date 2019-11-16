package modele;

import com.badlogic.gdx.math.Vector2;

/**
 * classe abstraite des obstacles
 * @author Dany Br�geon, Lo�s Monet, Maxime Poirier
 *
 */
public abstract class Obstacle {
	/**
	 * la position de l'obstacle
	 */
	protected Vector2 position;
	/**
	 * la taille de l'obstacle
	 */
	protected float taille;
	/**
	 * la vitesse de l'obstacle
	 */
	protected float vitesse;
	/**
	 * la difficult� de l'obstacle
	 */
	protected int difficulte;
	/**
	 * l'�toile qui correspond � l'obstacle
	 */
	protected EtoileScore etoile;
	/**
	 * la hauteur de l'obstacle plus la distance entre cet obstacle et les autres
	 */
	protected float hauteurPlusDistance;
	
	/**
	 * cr�e la base d'un obstacle
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 * @param taille
	 * la taille de l'obstacle
	 * @param vitesse
	 * la vitesse de l'obstacle
	 * @param difficulte
	 * la difficult� de l'obstacle
	 */
	public Obstacle(float x, float y, float taille, float vitesse, int difficulte) {
		this.position = new Vector2(x,y);
		this.taille = taille;
		this.vitesse = vitesse;
		this.difficulte = difficulte;
		this.hauteurPlusDistance = 0;
		etoile = new EtoileScore(x,y);
	}
	
	/**
	 * le scrolling de l'obstacle lorsque la bille monte haut
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que l'obstacle doit parcourir
	 */
	public void Move(float delta, float hauteur) {
		Vector2 v = new Vector2(position.x,position.y);
		Vector2 v2 = new Vector2(0, -hauteur).scl(delta);
		v.add(v2);
		position.y = v.y;
		etoile.Move(delta, hauteur);
	}
	
	/**
	 * retourne la position
	 * @return la position
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * retourne la taille
	 * @return la taille
	 */
	public float getTaille() {
		return taille;
	}

	/**
	 * retourne la vitesse
	 * @return la vitesse
	 */
	public float getVitesse() {
		return vitesse;
	}

	/**
	 * retourne la difficult�
	 * @return la difficult�
	 */
	public int getDifficulte() {
		return difficulte;
	}
	
	/**
	 * retourne l'�toile associ� � l'obstacle
	 * @return l'�toile associ� � l'obstacle
	 */
	public EtoileScore getEtoile() {
		return etoile;
	}

	/**
	 * retourne la hauteur de l'obstacle plus la distance entre cet obstacle et les autres
	 * @return la hauteur de l'obstacle plus la distance entre cet obstacle et les autres
	 */
	public float getHauteurPlusDistance() {
		return hauteurPlusDistance;
	}
}
