package modele;


import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * rectangle pouvant être rotationner
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class RectanglePlus extends Rectangle{
	/**
	 * le tableau de sommets du rectangle
	 */
	private float[] sommets;
	/**
	 * l'angle du rectangle
	 */
	private float angleTotal;
	
	/**
	 * crée un rectangle
	 * @param x
	 * position en x du premier point du rectangle
	 * @param y
	 * position en y du premier point du rectangle
	 * @param width
	 * le largeur du rectangle
	 * @param height
	 * la hauteur du rectangle
	 */
	public RectanglePlus(float x, float y, float width, float height) {
		super(x,y,width,height);
		sommets = new float[8];
		sommets[0]=x;
		sommets[1]=y;
		sommets[2]=x + width;
		sommets[3]=y;
		sommets[4]=x + width;
		sommets[5]=y + height;
		sommets[6]=x;
		sommets[7]=y + height;
	}
	
	/**
	 * effectue une rotation du rectangle
	 * @param originX
	 * la position x du point autour duquel la rotation s'effectue
	 * @param originY
	 * la position y du point autour duquel la rotation s'effectue
	 * @param angle
	 * l'angle de rotation
	 */
	public void rotate(float originX, float originY,float angle) {		
		Vector2 v0= new Vector2(sommets[0] - originX, sommets[1] - originY);
		v0.rotate(angle);
		sommets[0] = v0.x+originX;
		sommets[1] = v0.y+originY;
			
		x=sommets[0];
		y=sommets[1];
		
		Vector2 v1= new Vector2(sommets[2] - originX, sommets[3] - originY);
		v1.rotate(angle);
		sommets[2] = v1.x+originX;
		sommets[3] = v1.y+originY;
		
		Vector2 v2= new Vector2(sommets[4] - originX, sommets[5] - originY);
		v2.rotate(angle);
		sommets[4] = v2.x+originX;
		sommets[5] = v2.y+originY;
		
		Vector2 v3= new Vector2(sommets[6] - originX, sommets[7] - originY);
		v3.rotate(angle);
		sommets[6] = v3.x+originX;
		sommets[7] = v3.y+originY;
	}

	/**
	 * retourne le tableau de sommets
	 * @return le tableau de sommets
	 */
	public float[] getSommets() {
		return sommets;
	}

	/**
	 * retourne l'angle du rectangle
	 * @return l'angle du rectangle
	 */
	public float getAngleTotal() {
		return angleTotal;
	}

	/**
	 * met à jour l'angle du rectangle
	 * @param angleTotal
	 * le nouvel angle du rectangle
	 */
	public void setAngleTotal(float angleTotal) {
		this.angleTotal = angleTotal;
	}
	
}
