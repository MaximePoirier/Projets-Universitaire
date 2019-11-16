package modele;

import com.badlogic.gdx.math.Vector2;

/**
 * l'obstacle cercle
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class CercleObstacle extends Obstacle{
	/**
	 * le tableau d'arcs qui composent le cercle
	 */
	private Arc[] arcs;
	
	/**
	 * crée un obstacle cercle
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
	public CercleObstacle(float x, float y, float taille, float vitesse, int difficulte) {
		super(x, y, taille, vitesse, difficulte);
		hauteurPlusDistance = 200*taille;
		position.y -= hauteurPlusDistance;
		y -= hauteurPlusDistance;
		etoile.setPosition(y);
		arcs = new Arc[4];
		arcs[0] = new Arc(x,y,taille*100,0,90, GameWorld.couleurs[0]);
		arcs[1] = new Arc(x,y,taille*100,90,90, GameWorld.couleurs[1]);
		arcs[2] = new Arc(x,y,taille*100,180,90, GameWorld.couleurs[2]);
		arcs[3] = new Arc(x,y,taille*100,270,90, GameWorld.couleurs[3]);
	}
	
	/**
	 * crée un obstacle cercle compris dans un autre obstacle
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
	 * @param couleur1
	 * la couleur du premier arc
	 * @param couleur2
	 * la couleur du deuxieme arc
	 * @param couleur3
	 * la couleur du troisieme arc
	 * @param couleur4
	 * la couleur du quatrieme arc
	 */
	public CercleObstacle(float x, float y, float taille, float vitesse, int difficulte, int couleur1, int couleur2, int couleur3, int couleur4) {
		super(x, y, taille, vitesse, difficulte);
		arcs = new Arc[4];
		arcs[0] = new Arc(x,y,taille*100,0,90, GameWorld.couleurs[couleur1]);
		arcs[1] = new Arc(x,y,taille*100,90,90, GameWorld.couleurs[couleur2]);
		arcs[2] = new Arc(x,y,taille*100,180,90, GameWorld.couleurs[couleur3]);
		arcs[3] = new Arc(x,y,taille*100,270,90, GameWorld.couleurs[couleur4]);
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
		Vector2 v = new Vector2(position.x,position.y);
		for(int i=0; i<arcs.length; i++) {
			/*Vector2 v = new Vector2(arcs[i].getPosition().x,arcs[i].getPosition().y);
			v.add(new Vector2(0, -hauteur).scl(delta));
			position.y = v.y;*/
			arcs[i].getPosition().y = v.y;
			arcs[i].setAngleDepart(((arcs[i].getAngleDepart()+1*vitesse))%360);
		}
	}

	/**
	 * retourne le tableau d'arcs
	 * @return le tableau d'arcs
	 */
	public Arc[] getArcs() {
		return arcs;
	}

}
