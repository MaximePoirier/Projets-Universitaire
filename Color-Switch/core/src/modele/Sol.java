package modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * le modele du sol de début de partie
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Sol {
	/**
	 * le rectangle qui correspond au sol
	 */
	private Rectangle rectangle;
	
	/**
	 * crée le sol
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 */
	public Sol(float x, float y) {
		rectangle = new Rectangle(x,y,Gdx.graphics.getWidth()/5,3);
	}
	
	/**
	 * le scrolling du sol lorsque la bille monte haut
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que le sol doit parcourir
	 */
	public void Move(float delta, float hauteur) {
		Vector2 v = new Vector2(rectangle.x,rectangle.y);
		v.add(new Vector2(0, -hauteur).scl(delta));
		rectangle.y = v.y;
	}

	/**
	 * retourne le rectangle qui correspond au sol
	 * @return le rectangle qui correspond au sol
	 */
	public Rectangle getRectangle() {
		return rectangle;
	}
	
}
