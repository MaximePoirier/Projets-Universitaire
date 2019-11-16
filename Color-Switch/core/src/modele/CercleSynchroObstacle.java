package modele;

/**
 * l'obstacle cercle synchro
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class CercleSynchroObstacle extends Obstacle{

	/**
	 * le tableau des cercles qui composent l'obstacle
	 */
	private CercleObstacle[] cercles;
	
	/**
	 * crée l'obstacle cercle synchro
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
	public CercleSynchroObstacle(float x, float y, float taille, float vitesse, int difficulte) {
		super(x, y, taille, vitesse, difficulte);
		hauteurPlusDistance = 350*taille;
		position.y -= hauteurPlusDistance;
		y -= hauteurPlusDistance;
		etoile.setPosition(y);
		cercles = new CercleObstacle[4];
		cercles[0] = new CercleObstacle(x-105*taille,y+150*taille,taille,-vitesse,difficulte,0,1,2,3);
		cercles[1] = new CercleObstacle(x+105*taille,y+150*taille,taille,vitesse,difficulte,1,0,3,2);
		cercles[2] = new CercleObstacle(x-105*taille,y-150*taille,taille,vitesse/3,difficulte,0,1,2,3);
		cercles[3] = new CercleObstacle(x+105*taille,y-150*taille,taille,-vitesse/3,difficulte,1,0,3,2);
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
		cercles[0].Move(delta, hauteur);
		cercles[1].Move(delta, hauteur);
		cercles[2].Move(delta, hauteur);
		cercles[3].Move(delta, hauteur);
	}

	/**
	 * retourne le tableau des cercles qui composent l'obstacle
	 * @return le tableau des cercles qui composent l'obstacle
	 */
	public CercleObstacle[] getCercles() {
		return cercles;
	}

}
