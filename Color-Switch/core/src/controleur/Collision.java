package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Vector2;

import modele.TriangleObstacle;
import modele.TripleCercleObstacle;
import modele.CercleSynchroObstacle;
import modele.BarreHorizontale;
import modele.CercleObstacle;
import modele.CarreObstacle;
import modele.GameWorld;

/**
 * gère les collisions du jeu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Collision {
	/**
	 * bruitage quand il y a collision avec une étoile
	 */
	private Sound starSound = Gdx.audio.newSound(Gdx.files.internal("star.wav"));
	/**
	 * bruitage quand il y a collision avec l'object qui fait changer la couleur de la bille
	 */
	private Sound colorSwitchSound = Gdx.audio.newSound(Gdx.files.internal("colorswitch.wav"));
	/**
	 * bruitage quand on meurt
	 */
	private Sound deadSound = Gdx.audio.newSound(Gdx.files.internal("dead.wav"));
	/**
	 * le modele du jeu
	 */
	private GameWorld myWorld;
	
	/**
	 * 
	 * @param gw
	 * le modele du jeu
	 */
	public Collision(GameWorld gw) {
		myWorld = gw;
	}
	
	/**
	 * methode appelé à chaque frame qui vérifie les collisions
	 * @param delta
	 * @throws Exception
	 * throw une exception s'il y a une collision entre la bille et un obstacle
	 */
	public void update(float delta) throws Exception {

		for(int i=0; i<myWorld.getIdObstacle().length; i++) {
			if(myWorld.getSol() != null) {
				collisionSol();
			}
			if(collisionVide()) {
				throw new Exception();
			}
			if(GameWorld.modeDeJeu==2) {
				if(collisionLave()) {
					throw new Exception();
				}
			}else {
				collisionChangeColor(i);
			}
			collisionEtoileScore(i);
			switch (myWorld.getIdObstacle()[i]) {
				case 1: if(collisionBarreHorizontale(i)) {
					throw new Exception();
					}
					break;
			
				case 2: if(collisionCercle(((CercleObstacle)myWorld.getObstacles()[i])/*i*/)) {
					throw new Exception();
					}
					break;
				
				case 3: if(collisionCarre(i)) {
					throw new Exception();
					}
					break;
				
				case 4: if(collisionCercle(((CercleSynchroObstacle)myWorld.getObstacles()[i]).getCercles()[1])
						 ||collisionCercle(((CercleSynchroObstacle)myWorld.getObstacles()[i]).getCercles()[3])) {
					throw new Exception();
					}
					break;
					
				case 5: if(collisionCercle(((TripleCercleObstacle)myWorld.getObstacles()[i]).getCercles()[0])
						 ||collisionCercle(((TripleCercleObstacle)myWorld.getObstacles()[i]).getCercles()[1])
						 ||collisionCercle(((TripleCercleObstacle)myWorld.getObstacles()[i]).getCercles()[2])) {
					throw new Exception();
					}
					break;
					
				case 6: if(collisionTriangle(i)) {
					throw new Exception();
					}
					break;
    		}	
    	}
	}
	
	/**
	 * vérifie la collision de la bille avec le bas de l'écran
	 * @return s'il y a collision ou non
	 */
	public boolean collisionVide() {
		if(myWorld.getBille().getPosition().y-myWorld.getBille().getTaille() > myWorld.getHauteurFenetre()) {
			return true;
		}
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec la barre de début de partie
	 * @return s'il y a collision ou non
	 */
	public boolean collisionSol() {
		if(Intersector.overlaps(myWorld.getBille().getHitBox(), myWorld.getSol().getRectangle())){
			myWorld.getBille().setStart(true);
			myWorld.getBille().setPosition(new Vector2(myWorld.getBille().getPosition().x, myWorld.getSol().getRectangle().y - myWorld.getBille().getTaille()));
			return true;
		}
		
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec la lave
	 * @return s'il y a collision ou non
	 */
	public boolean collisionLave() {
		if(myWorld.getBille().getPosition().y+myWorld.getBille().getTaille()>myWorld.getLava().getPosition().y-myWorld.getLava().getHauteurMaxLave()) {
			return true;
		}
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un des objects qui change la couleur de la bille. Si c'est le cas la bille change de couleur
	 * @param num
	 * l'index de l'object qui change la couleur de la bille
	 * @return s'il y a collision ou non
	 */
	public boolean collisionChangeColor(int num) {
		if(Intersector.overlaps(myWorld.getBille().getHitBox(), myWorld.getChangementCouleurs()[num].getCercle())){
			int random = (int)(Math.random() * GameWorld.couleurs.length);
			if(num == myWorld.getIdObstacle().length - 1) {
				if(myWorld.getIdObstacle()[0] == 6) {
					random = (int)(Math.random() * GameWorld.couleurs.length-1);
					if(GameWorld.couleurs[random].equals(myWorld.getBille().getCouleur())) {
						if(random == GameWorld.couleurs.length-2) {
							myWorld.getBille().setCouleur(GameWorld.couleurs[0]);
						}else {
							myWorld.getBille().setCouleur(GameWorld.couleurs[random+1]);
						}
					}else {
						myWorld.getBille().setCouleur(GameWorld.couleurs[random]);
					}
				}else {
					if(GameWorld.couleurs[random].equals(myWorld.getBille().getCouleur())) {
						if(random == GameWorld.couleurs.length-1) {
							myWorld.getBille().setCouleur(GameWorld.couleurs[0]);
						}else {
							myWorld.getBille().setCouleur(GameWorld.couleurs[random+1]);
						}
					}else {
						myWorld.getBille().setCouleur(GameWorld.couleurs[random]);
					}
				}
			}else {
				if(myWorld.getIdObstacle()[num+1] == 6) {
					random = (int)(Math.random() * GameWorld.couleurs.length-1);
					if(GameWorld.couleurs[random].equals(myWorld.getBille().getCouleur())) {
						if(random == GameWorld.couleurs.length-2) {
							myWorld.getBille().setCouleur(GameWorld.couleurs[0]);
						}else {
							myWorld.getBille().setCouleur(GameWorld.couleurs[random+1]);
						}
					}else {
						myWorld.getBille().setCouleur(GameWorld.couleurs[random]);
					}
				}else {
					if(GameWorld.couleurs[random].equals(myWorld.getBille().getCouleur())) {
						if(random == GameWorld.couleurs.length-1) {
							myWorld.getBille().setCouleur(GameWorld.couleurs[0]);
						}else {
							myWorld.getBille().setCouleur(GameWorld.couleurs[random+1]);
						}
					}else {
						myWorld.getBille().setCouleur(GameWorld.couleurs[random]);
					}
				}
			}
			myWorld.getChangementCouleurs()[num].setPosition(-2000);
			if(GameWorld.son) {
				colorSwitchSound.play();
			}
			
			return true;
		}
		
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec une étoile
	 * @param num
	 * l'index de l'étoile
	 * @return s'il y a collision ou non
	 */
	public boolean collisionEtoileScore(int num) {
		boolean b =Intersector.overlaps(myWorld.getBille().getHitBox(), myWorld.getObstacles()[num].getEtoile().getCercle());
		if(myWorld.getObstacles()[num].getEtoile().isVivant() && Intersector.overlaps(myWorld.getBille().getHitBox(), myWorld.getObstacles()[num].getEtoile().getCercle())){
			myWorld.setScore(myWorld.getScore()+1);
			myWorld.getObstacles()[num].getEtoile().setVivant(false);
			if(GameWorld.son) {
				starSound.play();
			}
			return true;
		}
		
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un obstacle triangle
	 * @param num
	 * l'index de l'obstacle
	 * @return s'il y a collision ou non
	 */
	public boolean collisionTriangle(int num) { //change
		
		return (intersectionRectangleCercleTri(num,0)
				|| intersectionRectangleCercleTri(num,1)
				|| intersectionRectangleCercleTri(num,2));
	}
	
	/**
	 * vérifie la collision de la bille avec un obstacle carré
	 * @param num
	 * l'index de l'obstacle
	 * @return s'il y a collision ou non
	 */
	public boolean collisionCarre(int num) {
		return (intersectionRectangleCercle(num,0)
				|| intersectionRectangleCercle(num,1)
				|| intersectionRectangleCercle(num,2)
				|| intersectionRectangleCercle(num,3));
	}
	
	/**
	 * vérifie la collision de la bille avec un rectangle de l'obstacle triangle
	 * @param num
	 * l'index de l'obstacle
	 * @param num2
	 * l'index d'un rectangle de l'obstacle
	 * @return s'il y a intersection ou non
	 */
	private boolean intersectionRectangleCercleTri(int num, int num2) {  //change
		float[] posSommetsRect = ((TriangleObstacle)myWorld.getObstacles()[num]).getRectangles()[num2].getSommets();
		Vector2 posCercle = myWorld.getBille().getPosition();
		float rayonCercle = myWorld.getBille().getTaille();
		if(((TriangleObstacle)myWorld.getObstacles()[num]).getCouleursRectangles()[num2] != myWorld.getBille().getCouleur()) {
			for (int i = 0; i < posSommetsRect.length; i += 2) {
				if (i == 0) {
		            if (Intersector.intersectSegmentCircle(new Vector2(posSommetsRect[posSommetsRect.length - 2], posSommetsRect[posSommetsRect.length - 1]),
		            		new Vector2(posSommetsRect[i], posSommetsRect[i + 1]), posCercle, rayonCercle*rayonCercle)) {
		                return true;
		            }
		        } else {
		            if (Intersector.intersectSegmentCircle(new Vector2(posSommetsRect[i - 2], posSommetsRect[i - 1]),
		            		new Vector2(posSommetsRect[i], posSommetsRect[i + 1]), posCercle, rayonCercle*rayonCercle)) {
		                return true;
		            }
		        }
			}
		}	
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un rectangle de l'obstacle carré
	 * @param num
	 * l'index de l'obstacle
	 * @param num2
	 * l'index d'un rectangle de l'obstacle
	 * @return s'il y a intersection ou non
	 */
	private boolean intersectionRectangleCercle(int num, int num2) {
		float[] posSommetsRect = ((CarreObstacle)myWorld.getObstacles()[num]).getRectangles()[num2].getSommets();
		Vector2 posCercle = myWorld.getBille().getPosition();
		float rayonCercle = myWorld.getBille().getTaille();
		if(((CarreObstacle)myWorld.getObstacles()[num]).getCouleursRectangles()[num2] != myWorld.getBille().getCouleur()) {
			for (int i = 0; i < posSommetsRect.length; i += 2) {
				if (i == 0) {
		            if (Intersector.intersectSegmentCircle(new Vector2(posSommetsRect[posSommetsRect.length - 2], posSommetsRect[posSommetsRect.length - 1]),
		            		new Vector2(posSommetsRect[i], posSommetsRect[i + 1]), posCercle, rayonCercle*rayonCercle)) {
		                return true;
		            }
		        } else {
		            if (Intersector.intersectSegmentCircle(new Vector2(posSommetsRect[i - 2], posSommetsRect[i - 1]),
		            		new Vector2(posSommetsRect[i], posSommetsRect[i + 1]), posCercle, rayonCercle*rayonCercle)) {
		                return true;
		            }
		        }
			}
		}	
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un obstacle cercle
	 * @param num
	 * l'index de l'obstacle
	 * @return s'il y a collision ou non
	 */
	public boolean collisionCercle(int num) {
		float posBille = myWorld.getBille().getPosition().y;
		float rayonBille = myWorld.getBille().getTaille();
		float posObs = ((CercleObstacle)myWorld.getObstacles()[num]).getPosition().y;
		float rayonObs = ((CercleObstacle)myWorld.getObstacles()[num]).getTaille()*100;
		if((posBille < posObs + rayonObs + rayonBille && posBille > posObs + rayonObs*0.87 - rayonBille)) {
			for(int i=0; i< ((CercleObstacle)myWorld.getObstacles()[num]).getArcs().length; i++){
				float angleDep = ((CercleObstacle)myWorld.getObstacles()[num]).getArcs()[i].getAngleDepart();
				float angleDepartCollision = (float)Math.atan((rayonObs/rayonBille))*5;
				if(angleDep > angleDepartCollision && angleDep <= 90-angleDepartCollision){
					if(!((CercleObstacle)myWorld.getObstacles()[num]).getArcs()[i].getCouleur().equals(myWorld.getBille().getCouleur())) {
						Gdx.app.log("Collision", "boom");
						return true;
					}
				}else if(angleDep > 90-angleDepartCollision && angleDep < 90+angleDepartCollision) {
					Gdx.app.log("Collision", "boom");
					return true;
				}
			}
		}
		else if (posBille > posObs - rayonObs - rayonBille && posBille < posObs - rayonObs*0.83 + rayonBille) {
			for(int i=0; i< ((CercleObstacle)myWorld.getObstacles()[num]).getArcs().length; i++){
				float angleDep = ((CercleObstacle)myWorld.getObstacles()[num]).getArcs()[i].getAngleDepart();
				float angleDepartCollision = (float)Math.atan((rayonObs/rayonBille))*5;
				if(angleDep > 180+angleDepartCollision && angleDep <= 270-angleDepartCollision){
					if(!((CercleObstacle)myWorld.getObstacles()[num]).getArcs()[i].getCouleur().equals(myWorld.getBille().getCouleur())) {
						Gdx.app.log("Collision", "boom");
						return true;
					}
				}else if(angleDep > 270-angleDepartCollision && angleDep < 270+angleDepartCollision) {
					Gdx.app.log("Collision", "boom");
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un obstacle contenant plus d'un cercle
	 * @param cercle
	 * l'obstacle cercle
	 * @return s'il y a collision ou non
	 */
	public boolean collisionCercle(CercleObstacle cercle) {
		Vector2 posBille = myWorld.getBille().getPosition();
		float rayonBille = myWorld.getBille().getTaille();
		Vector2 posObs = cercle.getPosition();
		float rayonObs = cercle.getTaille()*98;
		float distanceBilleCentre = (float) Math.sqrt(Math.pow((posBille.x-posObs.x),2) + Math.pow((posBille.y-posObs.y),2));
		if((distanceBilleCentre < rayonObs+rayonBille && distanceBilleCentre > rayonObs*0.87 - rayonBille)) {
			Vector2 v = new Vector2(posBille.x-posObs.x, posBille.y-posObs.y);
			float angleBille = v.angle();
			float angleDepartCollision = (float) Math.toDegrees((float)Math.atan((rayonBille/distanceBilleCentre)));
			for(int i=0; i< cercle.getArcs().length; i++){
				float angleDep = cercle.getArcs()[i].getAngleDepart();
				if(angleDep < 0) {
					angleDep = angleDep+360;
				}
				//Gdx.app.log("collision", cercle.getArcs()[i].getCouleur().r + " " +  + cercle.getArcs()[i].getCouleur().g + " " + cercle.getArcs()[i].getCouleur().b + " : " + angleDep);
				Gdx.app.log("collision", "angleDep : " + String.valueOf(angleDep) + ", angleBille : " + String.valueOf(angleBille) + ", angleDepartCollision : " + String.valueOf(angleDepartCollision));
				if(angleDep<angleBille+angleDepartCollision && angleDep>angleBille-angleDepartCollision) {
					Gdx.app.log("Collision", "boom");
					return true;
				}else if(angleDep<=angleBille-angleDepartCollision && angleDep>angleBille+angleDepartCollision-90) {
					Gdx.app.log("Collision", cercle.getArcs()[i].getCouleur().r + " " +  + cercle.getArcs()[i].getCouleur().g + " " + cercle.getArcs()[i].getCouleur().b);
					if(!cercle.getArcs()[i].getCouleur().equals(myWorld.getBille().getCouleur())) {
						Gdx.app.log("Collision", "boom");
						return true;
					}
				}
			}
		}
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un rectangle de l'obstacle barre horizontale
	 * @param num
	 * l'index de l'obstacle
	 * @param num2
	 * l'index d'un rectangle de l'obstacle
	 * @return s'il y a collision ou non
	 */
	private boolean collisionRectangle(int num, int num2) {
		if(Intersector.overlaps(myWorld.getBille().getHitBox(), ((BarreHorizontale)myWorld.getObstacles()[num]).getRectangles()[num2])){
			if(!myWorld.getBille().getCouleur().equals(((BarreHorizontale)myWorld.getObstacles()[num]).getCouleursRectangles()[num2])) {
				Gdx.app.log("Collision", "boom");
				return true;
			}
		}
		return false;
	}
	
	/**
	 * vérifie la collision de la bille avec un obstacle barre horizontale
	 * @param num
	 * l'index de l'obstacle
	 * @return s'il y a collision ou non
	 */
	public boolean collisionBarreHorizontale(int num) {
		for(int i=0; i<((BarreHorizontale)myWorld.getObstacles()[num]).getRectangles().length;i++) {
			if(collisionRectangle(num, i)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * retourne le son de la collision avec une étoile
	 * @return le son de la collision avec une étoile
	 */
	public Sound getStarSound() {
		return starSound;
	}

	/**
	 * retourne le son de la collision avec un object qui change la couleur de la bille
	 * @return le son de la collision avec un object qui change la couleur de la bille
	 */
	public Sound getColorSwitchSound() {
		return colorSwitchSound;
	}

	/**
	 * retourne le son de la mort
	 * @return le son de la mort
	 */
	public Sound getDeadSound() {
		return deadSound;
	}

}
