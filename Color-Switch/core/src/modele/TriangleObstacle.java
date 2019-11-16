package modele;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Vector2;

/**
 * l'obstacle triangle
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class TriangleObstacle extends Obstacle{
	/**
	 * le tableau de rectangles qui composent l'obstacle
	 */
	private TrianglePlus[] rectangles;
	/**
	 * le tableau de couleurs des rectangles qui composent l'obstacle
	 */
	private Color[] couleursRectangles;
	
	/**
	 * crée l'obstacle triangle
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
	public TriangleObstacle(float x, float y, float taille, float vitesse, int difficulte) {
		super(x, y, taille, vitesse, difficulte);
		hauteurPlusDistance = 350*taille;
		position.y -= hauteurPlusDistance;
		y -= hauteurPlusDistance;
		etoile.setPosition(y);
		rectangles = new TrianglePlus[3];
		couleursRectangles = new Color[3];
		couleursRectangles[0] = GameWorld.couleurs[0];//new Color(1,1,0,1);
		couleursRectangles[1] = GameWorld.couleurs[1];
		couleursRectangles[2] = GameWorld.couleurs[2];
		
		for(int i=0; i<rectangles.length; i++) {
			rectangles[i] = new TrianglePlus(position.x-201*taille, position.y-125*taille, 373*taille,25*taille);

			rectangles[i].rotate(position.x, position.y, 120*i);
		}
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
	public TrianglePlus[] getRectangles() {
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