package modele;

import com.badlogic.gdx.Gdx;
import com.gdx.colorswitch.ColorSwitch;

/**
 * la classe principale du modele du menu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuWorld{
	/**
	 * le tableau des boutons présent dans le menu
	 */
	private Bouton[] boutons;
	/**
	 * le tableau des obstacles présent dans le menu
	 */
	private Obstacle[] obstacles;
	/**
	 * le nombre de boutons du menu
	 */
	private int nbBoutons = 5;
	/**
	 * indique si le son est activé ou non
	 */
	private boolean son;
	
	/**
	 * crée tous les boutons et les obstacles
	 * @param sonActif
	 * indique si le son est actif
	 */
	public MenuWorld(boolean sonActif) {
		son = sonActif;
		boutons = new Bouton[nbBoutons];
		boutons[0]= new Bouton(Gdx.graphics.getWidth()/2,Gdx.graphics.getHeight()/2,86);
		boutons[1]= new Bouton(Gdx.graphics.getWidth()*(7f/8f),Gdx.graphics.getHeight()*(11f/12f),43*ColorSwitch.ratioTailleEcran);
		boutons[2]= new Bouton(Gdx.graphics.getWidth()*(1f/8f),Gdx.graphics.getHeight()*(11f/12f),43*ColorSwitch.ratioTailleEcran);
		boutons[3]= new Bouton(Gdx.graphics.getWidth()*(3f/8f),Gdx.graphics.getHeight()*(11f/12f),43*ColorSwitch.ratioTailleEcran);
		boutons[4]= new Bouton(Gdx.graphics.getWidth()*(5f/8f),Gdx.graphics.getHeight()*(11f/12f),43*ColorSwitch.ratioTailleEcran);
		
		obstacles = new Obstacle[5];
		obstacles[0] = new CercleObstacle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+200*2f*ColorSwitch.ratioTailleEcran, 2f*ColorSwitch.ratioTailleEcran,1,1);
		obstacles[1] = new CercleObstacle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+200*1.57f*ColorSwitch.ratioTailleEcran, 1.57f*ColorSwitch.ratioTailleEcran,-1,1);
		obstacles[2] = new CercleObstacle(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2+200*1.2f*ColorSwitch.ratioTailleEcran, 1.2f*ColorSwitch.ratioTailleEcran,1,1);
		obstacles[3] = new CercleObstacle(Gdx.graphics.getWidth()/2-53*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/9+200*0.33f*ColorSwitch.ratioTailleEcran, 0.33f*ColorSwitch.ratioTailleEcran,2,1);
		obstacles[4] = new CercleObstacle(Gdx.graphics.getWidth()/2+55*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/9+200*0.33f*ColorSwitch.ratioTailleEcran, 0.33f*ColorSwitch.ratioTailleEcran,-2,1);
	}
	
	/**
	 * permet d'animer les obstacles du menu
	 * @param delta
	 */
	public void update(float delta) {
		for(int i=0; i<obstacles.length; i++) {
			obstacles[i].Move(delta, 0);
		}
	}

	/**
	 * retourne le tableau de boutons
	 * @return le tableau de boutons
	 */
	public Bouton[] getBoutons() {
		return boutons;
	}
	
	/**
	 * retourne le tableau d'obstacles
	 * @return le tableau d'obstacles
	 */
	public Obstacle[] getObstacles() {
		return obstacles;
	}

	/**
	 *  retourne si le son est activé ou pas
	 * @return le boolean son
	 */
	public boolean isSon() {
		return son;
	}

	/**
	 * met à jour le boolean son
	 * @param son
	 * le nouveau booleen son
	 */
	public void setSon(boolean son) {
		this.son = son;
	}
	
}
