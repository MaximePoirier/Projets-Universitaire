package modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * l'obstacle barre horizontale
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class BarreHorizontale extends Obstacle{
	/**
	 * le tableau de rectangles qui composent l'obstacle
	 */
	private Rectangle[] rectangles;
	/**
	 * le tableau des couleurs des rectangles qui composent l'obstacle
	 */
	private Color[] couleursRectangles;

	/**
	 * crée l'obstacle barre horizontale
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
	public BarreHorizontale(float x, float y, float taille, float vitesse, int difficulte) {
		super(x,y, taille,vitesse, difficulte);
		hauteurPlusDistance = 220*taille;
		position.y -= hauteurPlusDistance;
		y -= hauteurPlusDistance;
		etoile.setPosition(y);
		rectangles = new Rectangle[10];
		couleursRectangles = new Color[10];
		couleursRectangles[0] = GameWorld.couleurs[0];
		couleursRectangles[1] = GameWorld.couleurs[1];
		couleursRectangles[2] = GameWorld.couleurs[2];
		couleursRectangles[3] = GameWorld.couleurs[3];
		couleursRectangles[4] = GameWorld.couleurs[0];
		couleursRectangles[5] = GameWorld.couleurs[0];
		couleursRectangles[6] = GameWorld.couleurs[1];
		couleursRectangles[7] = GameWorld.couleurs[2];
		couleursRectangles[8] = GameWorld.couleurs[3];
		couleursRectangles[9] = GameWorld.couleurs[0];
		
		int l = Gdx.graphics.getWidth();
		rectangles[0] = new Rectangle(position.x-l/2, position.y+90*taille, l/4,l/32);
		rectangles[1] = new Rectangle(position.x-l/4, position.y+90*taille, l/4,l/32);
		rectangles[2] = new Rectangle(position.x, position.y+90*taille, l/4,l/32);
		rectangles[3] = new Rectangle(position.x+l/4, position.y+90*taille, l/4,l/32);
		rectangles[4] = new Rectangle(position.x+l/2, position.y+90*taille, l/4,l/32);
		rectangles[5] = new Rectangle(position.x-l/2, position.y-90*taille-l/32, l/4,l/32);
		rectangles[6] = new Rectangle(position.x-l/4, position.y-90*taille-l/32, l/4,l/32);
		rectangles[7] = new Rectangle(position.x, position.y-90*taille-l/32, l/4,l/32);
		rectangles[8] = new Rectangle(position.x+l/4, position.y-90*taille-l/32, l/4,l/32);
		rectangles[9] = new Rectangle(position.x+l/2, position.y-90*taille-l/32, l/4,l/32);
	}
	
	/**
	 * le scrolling de l'obstacle lorsque la bille monte haut et le déplacement horizontal
	 * @param delta
	 * correspond au temps que dure une frame
	 * @param hauteur
	 * la distance que l'obstacle doit parcourir
	 */
	@Override
	public void Move(float delta, float hauteur) {
		super.Move(delta, hauteur);
		Vector2 v2 = new Vector2(0, -hauteur).scl(delta);
		for(int i=0; i<rectangles.length; i++) {
			rectangles[i].y += v2.y;
			
			if(i<5) {
				rectangles[i].x += vitesse;
				if(rectangles[i].x >= Gdx.graphics.getWidth()) {
					rectangles[i].x = -(Gdx.graphics.getWidth()/4);
					if(i==0) {
						couleursRectangles[i] = couleursRectangles[couleursRectangles.length-6];
					}else {
						couleursRectangles[i] = couleursRectangles[i-1];
					}
				}
			}else {
				rectangles[i].x -= vitesse;
				if(rectangles[i].x <= -(Gdx.graphics.getWidth()/4)) {
					rectangles[i].x = Gdx.graphics.getWidth();
					if(i==rectangles.length-1) {
						couleursRectangles[i] = couleursRectangles[couleursRectangles.length-5];
					}else {
						couleursRectangles[i] = couleursRectangles[i+1];
					}
				}
			}
		}
	}
	
	/**
	 * retourne le tableau de rectangles
	 * @return le tableau de rectangles
	 */
	public Rectangle[] getRectangles() {
		return rectangles;
	}

	/**
	 * retourne le tableau de couleurs des rectangles
	 * @return le tableau de couleurs des rectangles
	 */
	public Color[] getCouleursRectangles() {
		return couleursRectangles;
	}

}
