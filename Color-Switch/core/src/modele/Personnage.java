package modele;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Vector2;
import com.gdx.colorswitch.ColorSwitch;

/**
 * le modele de la bille
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Personnage {
	/**
	 * la position de la bille
	 */
	private Vector2 position;
	/**
	 * la couleur de la bille
	 */
	private Color couleur;
	/**
	 * la hauteur de saut de la bille
	 */
	private float hauteurSaut;
	/**
	 * le 'poids' de la bille
	 */
	private float poids;
	/**
	 * le rayon de la bille
	 */
	private float taille;
	/**
	 * l'acceleration de la bille
	 */
	private float acceleration;
	/**
	 * la hitbox de la bille
	 */
	private Circle hitBox;
	/**
	 * si la bille est sur le sol
	 */
	private boolean start;
	/**
	 * le son du saut
	 */
	private Sound sound = Gdx.audio.newSound(Gdx.files.internal("jump.wav"));
	/**
	 * le son de la mort
	 */
	private Sound soundDie = Gdx.audio.newSound(Gdx.files.internal("dead.wav"));
	/**
	 * la vitesse en x des billes dans l'animation de mort
	 */
	private float vitessex=800f;
	/**
	 * la vitesse en y des billes dans l'animation de mort
	 */
	private float vitessey;
	
	/**
	 * crée la bille
	 * @param x
	 * la position x
	 * @param y
	 * la position y
	 * @param hauteurSaut
	 * la hauteur de saut
	 * @param poids
	 * le poids
	 * @param taille
	 * le rayon
	 */
	public Personnage(float x, float y, float hauteurSaut, float poids, float taille) {
		position = new Vector2(x,y);
		this.hauteurSaut = hauteurSaut;
		this.poids = poids;
		this.taille = taille;
		int random = (int)(Math.random() * GameWorld.couleurs.length);
		couleur = GameWorld.couleurs[random];
		acceleration = 0;
		hitBox = new Circle(position, taille*0.98f);
		start = true;
	}

	/**
	 * méthode appelé à la mort du perosnnage, initialise l'angle et la vitesse des billes de l'animation de mort
	 */
	public void diePersonnage() {
		int rand;
		if(GameWorld.son) {
			soundDie.play();
		}
		vitessex=(float)(Math.random()*(vitessex/10))+(vitessex*9/10);
		vitessey=vitessex;
		rand=(int)(Math.random()*360) ;
		vitessex=(float)Math.cos(rand)*vitessex;
		rand=(int)(Math.random()*360) ;
		vitessey=(float)Math.sin(rand)*vitessey;
	}
	
	/**
	 * gère le déplacement de la bille
	 * @param delta
	 * @return la distance entre la bille et la hauteur max quand la bille dépasse la hauteur max
	 */
	public float update(float delta) {
		if(GameWorld.die) {
			acceleration += poids;
			if (position.x > Gdx.graphics.getWidth() - taille/2 ||position.x< 0 + taille/2) {
	            vitessex = -vitessex;       
	        }
	        if (position.y > Gdx.graphics.getHeight() - taille/2 || position.y < 0 + taille/2) {
	            vitessey = -vitessey;
	        }
	        position.add(new Vector2(vitessex, vitessey+acceleration).scl(delta));
		}
		else if(start) {
			acceleration=0;
		}
		else {
			acceleration += poids;
			if(acceleration>1200*ColorSwitch.ratioTailleEcran) {
				acceleration = 1200*ColorSwitch.ratioTailleEcran;
			}
			float diff=0;
			int hauteurMaxSaut = (int) (Gdx.graphics.getHeight()*0.40);
			if(position.y + (acceleration/60)<hauteurMaxSaut) {
				position.y = hauteurMaxSaut;
				diff = position.y + acceleration - hauteurMaxSaut;
			}else {
				position.add(new Vector2(0, acceleration).scl(delta));
			}
			hitBox.setPosition(position);
			return diff;
		}
		return 0;
	}
	
	/**
	 * gère le saut de la bille
	 */
	public void onClick() {
		if(GameWorld.modeDeJeu==1) {
			acceleration = -hauteurSaut*((Gdx.graphics.getHeight()-Gdx.input.getY())/(Gdx.graphics.getHeight()/2f));
		}else {
			acceleration = -hauteurSaut;
		}
		if(start) {
			start = false;
		}
    }

	/**
	 * retourne la position de la bille
	 * @return la position de la bille
	 */
	public Vector2 getPosition() {
		return position;
	}

	/**
	 * retourne la couleur de la bille
	 * @return la couleur de la bille
	 */
	public Color getCouleur() {
		return couleur;
	}

	/**
	 * retourne la hauteur de saut de la bille
	 * @return la hauteur de saut de la bille
	 */
	public float getHauteurSaut() {
		return hauteurSaut;
	}

	/**
	 * retourne le poids de la bille
	 * @return le poids de la bille
	 */
	public float getPoids() {
		return poids;
	}

	/**
	 * retourne la taille de la bille
	 * @return la taille de la bille
	 */
	public float getTaille() {
		return taille;
	}

	/**
	 * retourne la hitbox de la bille
	 * @return la hitbox de la bille
	 */
	public Circle getHitBox() {
		return hitBox;
	}
	
	/**
	 * retourne le son du saut
	 * @return le son du saut
	 */
	public Sound getSound() {
		return sound;
	}
	
	/**
	 * retourne le son de la mort
	 * @return le son de la mort
	 */
	public Sound getSoundDie() {
		return soundDie;
	}

	/**
	 * met à jour la couleur de la bille
	 * @param couleur
	 * la nouvelle couleur de la bille
	 */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}

	/**
	 * retourne si la bille est sur le sol ou non
	 * @return si la bille est sur le sol ou non
	 */
	public boolean isStart() {
		return start;
	}

	/**
	 * met à jour si la bille est sur le sol ou non
	 * @param start
	 * si la bille est sur le sol ou non
	 */
	public void setStart(boolean start) {
		this.start = start;
	}

	/**
	 * met à jour la position de la bille
	 * @param position
	 * la nouvelle position de la bille
	 */
	public void setPosition(Vector2 position) {
		this.position = position;
	}
	
}
