package modele;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * l'obstacle carré
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class CarreObstacle extends Obstacle{
	/**
	 * le tableau de rectangles qui composent l'obstacle
	 */
	private RectanglePlus[] rectangles;
	/**
	 * le tableau de couleurs des rectangles qui composent l'obstacle
	 */
	private Color[] couleursRectangles;
	
	/**
	 * crée l'obstacle carré
	 * @param x
	 * la position en x
	 * @param y
	 * la position en y
	 * @param taille
	 * la taille de l'obstacle
	 * @param vitesse
	 * la vitesse de l'obstacle
	 * @param difficulte
	 * la difficulté de l'obstacle
	 */
	public CarreObstacle(float x, float y, float taille, float vitesse, int difficulte) {
		super(x, y, taille, vitesse, difficulte);
		hauteurPlusDistance = /*(float)Math.sqrt(2)*/220*taille;
		position.y -= hauteurPlusDistance;
		y -= hauteurPlusDistance;
		etoile.setPosition(y);
		rectangles = new RectanglePlus[4];
		couleursRectangles = new Color[4];
		couleursRectangles[0] = GameWorld.couleurs[0];//new Color(1,1,0,1);
		couleursRectangles[1] = GameWorld.couleurs[1];
		couleursRectangles[2] = GameWorld.couleurs[2];
		couleursRectangles[3] = GameWorld.couleurs[3];
		rectangles[0] = new RectanglePlus(position.x-100*taille, position.y-100*taille, 183*taille,17*taille);
		rectangles[1] = new RectanglePlus(position.x+100*taille, position.y-100*taille, -17*taille,183*taille);
		rectangles[2] = new RectanglePlus(position.x+100*taille, position.y+100*taille, -183*taille,-17*taille);
		rectangles[3] = new RectanglePlus(position.x-100*taille, position.y+100*taille, 17*taille,-183*taille);
	}

	/**
	 * le scrolling de l'obstacle lorsque la bille monte haut et la rotation de l'obstacle
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que l'obstacle doit parcourir
	 */
	public void Move(float delta, float hauteur) {
		super.Move(delta, hauteur);
		Vector2 v2 = new Vector2(0, -hauteur).scl(delta);
		for(int i=0; i<rectangles.length; i++) {
			
			rectangles[i].y += v2.y;
			rectangles[i].getSommets()[1] += v2.y;
			rectangles[i].getSommets()[3] += v2.y;
			rectangles[i].getSommets()[5] += v2.y;
			rectangles[i].getSommets()[7] += v2.y;
			
		}
		for(int i=0; i<rectangles.length; i++) {
			rectangles[i].rotate(position.x, position.y, 1*vitesse);
			rectangles[i].setAngleTotal(rectangles[i].getAngleTotal()+1*vitesse);
		}
		
	}

	/**
	 * retourne le tableau de rectangles qui composent l'obstacle
	 * @return le tableau de rectangles qui composent l'obstacle
	 */
	public RectanglePlus[] getRectangles() {
		return rectangles;
	}

	/**
	 * retourne le tableau de couleurs des rectangles qui composent l'obstacle
	 * @return le tableau de couleurs des rectangles qui composent l'obstacle
	 */
	public Color[] getCouleursRectangles() {
		return couleursRectangles;
	}

}
