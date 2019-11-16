package modele;

import com.badlogic.gdx.math.Vector2;

/**
 * modele du tutoriel
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Tutoriel {
	/**
	 * la position du tutoriel
	 */
	private Vector2 position;
	
	/**
	 * crée un tutoriel
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 */
	public Tutoriel(float x, float y) {
		position = new Vector2(x,y);
	}
	
	/**
	 * le scrolling du tutoriel lorsque la bille monte haut
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que le tutoriel doit parcourir
	 */
	public void Move(float delta, float hauteur) {
		Vector2 v = new Vector2(position.x,position.y);
		v.add(new Vector2(0, -hauteur).scl(delta));
		position.y = v.y;
	}

	
	/**
	 * retourne la position du tutoriel
	 * @return la position du tutoriel
	 */
	public Vector2 getPosition() {
		return position;
	}
	
}
