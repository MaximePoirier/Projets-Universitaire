package modele;

import com.badlogic.gdx.Application.ApplicationType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.gdx.colorswitch.ColorSwitch;

/**
 * le modele principale du jeu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class GameWorld {
	/**
	 * le personnage
	 */
	private Personnage bille;
	/**
	 * la hauteur de la bille
	 */
	private float hauteur;
	/**
	 * le tableau des obstacles présent dans le jeu
	 */
	private Obstacle[] obstacles;
	/**
	 * la hauteur de la fenêtre
	 */
	private int hauteurFenetre;
	/**
	 * la largeur de la fenêtre
	 */
	private int largeurFenetre;
	/**
	 * le score de la partie
	 */
	private int score;
	/**
	 * le tableau des id des obstacles
	 */
	private int[] idObstacle;
	/**
	 * le tableau des objets qui change la couleur de la bille
	 */
	private ChangeColor[] changementCouleurs;
	/**
	 * le sol du début de la partie
	 */
	private Sol sol;
	/**
	 * la lave du mode de jeu 2
	 */
	private Lave lava;
	/**
	 * le tutoriel du début de la partie
	 */
	private Tutoriel tuto;
	/*
	 * le tableau des boutons
	 */
	private Bouton[] boutons;
	/**
	 * le nombres de type d'obstacle
	 */
	public static int nbObstacle = 6;
	/**
	 * les différentes couleurs de la bille et des obstacles
	 */
	public static Color[] couleurs = {new Color(1,1,0,1), new Color(0,1,1,1),new Color(1,0,1,1),new Color(0.5f,0,1,1)};
	/**
	 * le mode de jeu de la partie
	 */
	public static int modeDeJeu = 0;
	/**
	 * indique si le son est activé
	 */
	public static boolean son = true;
	/**
	 * indique si l'on est mort ou non
	 */
	public static boolean die=false;
	/**
	 * le tableau de billes de l'animation de mort
	 */
	private Personnage[] diePerso;
	
	/**
	 * initialise une partie
	 * @param largeurFenetre
	 * la largeur de la fenetre
	 * @param hauteurFenetre
	 * la hauteur de la fenetre
	 * @param mdj
	 * le mode de jeu
	 * @param sonActif
	 * si le son est actif ou non
	 */
	public GameWorld(int largeurFenetre, int hauteurFenetre, int mdj, boolean sonActif) {
		son = sonActif;
		modeDeJeu=mdj;
		if(mdj==2) {
			lava = new Lave(0,(int) (hauteurFenetre*1.85f), 2.3f*ColorSwitch.ratioTailleEcran, (int) (hauteurFenetre*1.55f));
			if(Gdx.app.getType()==ApplicationType.Android) {
				boutons = new Bouton[4];
				boutons[0]= new Bouton(Gdx.graphics.getWidth()*(1f/8f),Gdx.graphics.getHeight()*(4f/8f),43*ColorSwitch.ratioTailleEcran);
				boutons[1]= new Bouton(Gdx.graphics.getWidth()*(1f/8f),Gdx.graphics.getHeight()*(5f/8f),43*ColorSwitch.ratioTailleEcran);
				boutons[2]= new Bouton(Gdx.graphics.getWidth()*(1f/8f),Gdx.graphics.getHeight()*(6f/8f),43*ColorSwitch.ratioTailleEcran);
				boutons[3]= new Bouton(Gdx.graphics.getWidth()*(1f/8f),Gdx.graphics.getHeight()*(7f/8f),43*ColorSwitch.ratioTailleEcran);
			}
		}
		score = 0;
		this.largeurFenetre = largeurFenetre;
		this.hauteurFenetre = hauteurFenetre;
		bille = new Personnage(largeurFenetre/2,hauteurFenetre*0.8f,640*ColorSwitch.ratioTailleEcran,40*ColorSwitch.ratioTailleEcran,16*ColorSwitch.ratioTailleEcran);
		//bille = new Personnage(milieuX,408,16,1,16);
		obstacles = new Obstacle[3];
		idObstacle = new int[3];
		changementCouleurs = new ChangeColor[3];
		//distanceEntreObstacle = 500;
		tuto = new Tutoriel(largeurFenetre/2,0);
		sol = new Sol(largeurFenetre/2-largeurFenetre/10,bille.getPosition().y+bille.getTaille());
		//obstacles[0] = new BarreHorizontale(0, hauteurFenetre/4, 1,2,1);
		//obstacles[0] = new CercleObstacle(largeurFenetre/2, hauteurFenetre/4, 1,2,1);
		//obstacles[1] = new BarreHorizontale(0, hauteurFenetre/4-distanceEntreObstacle, 1,2,1);
		//obstacles[2] = new BarreHorizontale(0, hauteurFenetre/4-2*distanceEntreObstacle, 1,2,1);
		creerObstacle(0, hauteurFenetre/2/*hauteurFenetre/4*/);
		if(getIdObstacle()[0]==6 && bille.getCouleur()==GameWorld.couleurs[3]) {
			bille.setCouleur(GameWorld.couleurs[0]);
		}
		changementCouleurs[0] = new ChangeColor(largeurFenetre/2, obstacles[0].getPosition().y - obstacles[0].getHauteurPlusDistance());
		creerObstacle(1, changementCouleurs[0].getPosition().y);
		changementCouleurs[1] = new ChangeColor(largeurFenetre/2, obstacles[1].getPosition().y - obstacles[1].getHauteurPlusDistance());
		creerObstacle(2, changementCouleurs[1].getPosition().y);
		changementCouleurs[2] = new ChangeColor(largeurFenetre/2, obstacles[2].getPosition().y - obstacles[2].getHauteurPlusDistance());
		//Gdx.app.log("world", String.valueOf(changementCouleurs[0].getPosition().y));
		
	}
	
	/**
	 * le déroulement d'une partie
	 * @param delta
	 */
	public void update(float delta) {
		if(!die) {
			hauteur = bille.update(delta);
		}else {
			if(diePerso==null) {
				bille.setStart(false);
				hauteur = bille.update(delta);
				
				
				if(Gdx.app.getType()==ApplicationType.Android) {
					diePerso=new Personnage[3];
				}else {
					diePerso=new Personnage[(int)(Math.random() *10)+10];
				}
				Gdx.app.log("Personnage",String.valueOf(diePerso.length));
				for(int i=0;i<diePerso.length;i++) {
	
					diePerso[i]=new Personnage(bille.getPosition().x, bille.getPosition().y, bille.getHauteurSaut(), bille.getPoids(), (float)(Math.random() * (bille.getTaille()/4))+bille.getTaille()/4); 
					diePerso[i].setCouleur(GameWorld.couleurs[(int)(Math.random() * GameWorld.couleurs.length)]);
					diePerso[i].diePersonnage();
				}
			}
			for(int i=0;i<diePerso.length;i++) {
				diePerso[i].update(delta);	
			}
		 
		}
		
		
		if(modeDeJeu==2 && !bille.isStart()) {
			lava.Move(delta, hauteur);
			if(lava.getPosition().y-bille.getPosition().y>lava.getDistanceMaxPersonnage()) {
				lava.setPosition((int)bille.getPosition().y+lava.getDistanceMaxPersonnage());
			}
		}
		for(int i=0; i<obstacles.length; i++) {
			obstacles[i].Move(delta, hauteur);
			changementCouleurs[i].Move(delta, hauteur);
		}
		if(sol != null) {
			sol.Move(delta, hauteur);
			if(sol.getRectangle().y > hauteurFenetre+3) {
				sol = null;
			}
		}
		if(tuto != null) {
			tuto.Move(delta, hauteur);
			if(tuto.getPosition().y > hauteurFenetre+3) {
				tuto = null;
			}
		}
		if(obstacles[0].getPosition().y>hauteurFenetre*1.4) {
			creerObstacle(0, changementCouleurs[2].getPosition().y);
			changementCouleurs[0].setPosition(obstacles[0].getPosition().y - obstacles[0].getHauteurPlusDistance());
		}
		else if(obstacles[1].getPosition().y>hauteurFenetre*1.4) {
			creerObstacle(1, changementCouleurs[0].getPosition().y);
			changementCouleurs[1].setPosition(obstacles[1].getPosition().y - obstacles[1].getHauteurPlusDistance());
		}
		else if(obstacles[2].getPosition().y>hauteurFenetre*1.4) {
			creerObstacle(2, changementCouleurs[1].getPosition().y);
			changementCouleurs[2].setPosition(obstacles[2].getPosition().y - obstacles[2].getHauteurPlusDistance());
		}           
    }
	
	/**
	 * crée un obstacle aléatoirement
	 * @param num
	 * l'index de l'obstacle
	 * @param y
	 * la position sur l'axe des y de l'obstacle à créer
	 */
	public void creerObstacle(int num, float y) {
		int random = (int)(Math.random() * nbObstacle) + 1;
		float randomTaille = 1;
		if(modeDeJeu==1) {
			randomTaille = (float) ((Math.random()*0.7f) + 0.7f);
		}
		switch (random) {
		
			case 1:
			obstacles[num] = new BarreHorizontale(largeurFenetre/2, y, 1*randomTaille*ColorSwitch.ratioTailleEcran,2+(float)Math.pow(1.5+score, 1/3f),1);			
			break;
			
			case 2:
			obstacles[num] = new CercleObstacle(largeurFenetre/2, y, 1.2f*randomTaille*ColorSwitch.ratioTailleEcran,(float)Math.pow(1+score, 1/3f),1);			
			break;
					
			case 3:
			obstacles[num] = new CarreObstacle(largeurFenetre/2, y, 1.2f*randomTaille*ColorSwitch.ratioTailleEcran,(float)Math.pow(0.5+score, 1/3f),1);
			break;
			
			case 4:
			obstacles[num] = new CercleSynchroObstacle(largeurFenetre/2, y, 0.95f*randomTaille*ColorSwitch.ratioTailleEcran,(float)Math.pow(1.5f+score, 1/3f),1);
			break;
			
			case 5:
			obstacles[num] = new TripleCercleObstacle(largeurFenetre/2, y, 1.1f*randomTaille*ColorSwitch.ratioTailleEcran,(float)Math.pow(0.75f+score, 1/3f),1);
			break;
			
			case 6:
			obstacles[num] = new TriangleObstacle(largeurFenetre/2, y, 0.7f*randomTaille*ColorSwitch.ratioTailleEcran,(float)Math.pow(0.5+score, 1/3f),1);
			break;
		}
		idObstacle[num] = random;
	}
	
	/**
	 * sauvegarde le meilleur score de chaque mode de jeu
	 */
	public void saveScore() {
		Preferences ScorePref = Gdx.app.getPreferences("ScorePref");
		
		if(modeDeJeu==0) {
			if(score> ScorePref.getInteger("score",0)) {
				ScorePref.putInteger("score",  score);
				Gdx.app.log("Best score", Integer.toString(ScorePref.getInteger("score")));
			}
		}else if(modeDeJeu==1) {
			if(score> ScorePref.getInteger("score1",0)) {
				ScorePref.putInteger("score1",  score);
				Gdx.app.log("Best score", Integer.toString(ScorePref.getInteger("score1")));
			}
		}else if(modeDeJeu==2) {
			if(score> ScorePref.getInteger("score2",0)) {
				ScorePref.putInteger("score2",  score);
				Gdx.app.log("Best score", Integer.toString(ScorePref.getInteger("score2")));
			}
		}
		ScorePref.flush();
	}
	
	/**
	 * retourne la bille
	 * @return la bille
	 */
	public Personnage getBille() {
		return bille;
	}
	
	/**
	 * retourne le tableau des billes de l'animation de mort
	 * @return le tableau des billes de l'animation de mort
	 */
	public Personnage[] getDiePerso() {
		return diePerso;
	}

	/**
	 * retourne le tableau des obstacles
	 * @return le tableau des obstacles
	 */
	public Obstacle[] getObstacles() {
		return obstacles;
	}

	/**
	 * retourne le tableau des id des obstacles
	 * @return le tableau des id des obstacles
	 */
	public int[] getIdObstacle() {
		return idObstacle;
	}

	/**
	 * retourne le tableau des objets qui change la couleur de la bille
	 * @return le tableau des objets qui change la couleur de la bille
	 */
	public ChangeColor[] getChangementCouleurs() {
		return changementCouleurs;
	}

	/**
	 * retourne la hauteur de la fenetre
	 * @return la hauteur de la fenetre
	 */
	public int getHauteurFenetre() {
		return hauteurFenetre;
	}

	/**
	 * retourne la largeur de la fenetre
	 * @return la largeur de la fenetre
	 */
	public int getLargeurFenetre() {
		return largeurFenetre;
	}

	/**
	 * retourne le score
	 * @return le score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * met à jour le score
	 * @param score
	 * le score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * retourne le sol
	 * @return le sol
	 */
	public Sol getSol() {
		return sol;
	}

	/**
	 * retourne la lave
	 * @return la lave
	 */
	public Lave getLava() {
		return lava;
	}

	/**
	 * retourne le tutoriel
	 * @return le tutoriel
	 */
	public Tutoriel getTuto() {
		return tuto;
	}

	/**
	 * retourn le tableau de boutons
	 * @return le tableau de boutons
	 */
	public Bouton[] getBoutons() {
		return boutons;
	}
	
}
