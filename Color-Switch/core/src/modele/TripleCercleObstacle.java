package modele;

/**
 * l'obstacle triple cercle
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class TripleCercleObstacle extends Obstacle{
	/**
	 * le tableau des cercles qui composent l'obstacle
	 */
	private CercleObstacle[] cercles;
	
	/**
	 * crée l'obstacle triple cercle
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
	public TripleCercleObstacle(float x, float y, float taille, float vitesse, int difficulte) {
		super(x, y, taille, vitesse, difficulte);
		hauteurPlusDistance = 400*taille;
		position.y -= hauteurPlusDistance;
		y -= hauteurPlusDistance;
		etoile.setPosition(y);
		cercles = new CercleObstacle[3];
		cercles[0] = new CercleObstacle(x,y+200*taille,taille,vitesse,difficulte,0,1,2,3);
		cercles[1] = new CercleObstacle(x,y,taille,-vitesse,difficulte,3,2,1,0);
		cercles[2] = new CercleObstacle(x,y-200*taille,taille,vitesse,difficulte,0,1,2,3);
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
	}
	
	/**
	 * retourne le tableau des cercles qui composent l'obstacle
	 * @return le tableau des cercles qui composent l'obstacle
	 */
	public CercleObstacle[] getCercles() {
		return cercles;
	}

}
