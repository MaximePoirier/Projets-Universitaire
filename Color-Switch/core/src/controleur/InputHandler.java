package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Application.ApplicationType;

import modele.GameWorld;
import modele.Personnage;

/**
 * gère les inputs dans le jeu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class InputHandler implements InputProcessor{
	/**
	 * la bille
	 */
	private Personnage maBille;
	/**
	 * le modele du jeu
	 */
	private GameWorld myWorld;
	
	/**
	 * initialise les attributs
	 * @param bille
	 * la bille
	 * @param world
	 * le modele du jeu
	 */
	public InputHandler(Personnage bille, GameWorld world) {
		maBille = bille;
		myWorld = world;
	}
	
	//permet de changer la couleur de la bille en appuyant sur les touches correzspondantes
	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
		if(GameWorld.modeDeJeu==2) {
			if(keycode == Input.Keys.SPACE) {
				if(maBille.getCouleur()==GameWorld.couleurs[0]) {
					maBille.setCouleur(GameWorld.couleurs[1]);
				}
				else if(maBille.getCouleur()==GameWorld.couleurs[1]) {
					maBille.setCouleur(GameWorld.couleurs[2]);
				}
				else if(maBille.getCouleur()==GameWorld.couleurs[2]) {
					maBille.setCouleur(GameWorld.couleurs[3]);
				}
				else if(maBille.getCouleur()==GameWorld.couleurs[3]) {
					maBille.setCouleur(GameWorld.couleurs[0]);
				}	
			}
			else if(keycode == Input.Keys.NUM_1 || keycode == Input.Keys.A) {
				maBille.setCouleur(GameWorld.couleurs[0]);
			}
			else if(keycode == Input.Keys.NUM_2 || keycode == Input.Keys.Z) {
				maBille.setCouleur(GameWorld.couleurs[1]);
			}
			else if(keycode == Input.Keys.NUM_3 || keycode == Input.Keys.E) {
				maBille.setCouleur(GameWorld.couleurs[2]);
			}
			else if(keycode == Input.Keys.NUM_4 || keycode == Input.Keys.R) {
				maBille.setCouleur(GameWorld.couleurs[3]);
			}
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	//permet de faire sauter la bille quand on appuie sur la souris ou sur l'écran, et gère l'appuie et le multitouch du mode lave sur android
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(GameWorld.modeDeJeu == 2 && Gdx.app.getType() == ApplicationType.Android) {
			if(Gdx.input.isTouched(1)) {
				if(Math.sqrt(Math.pow((Gdx.input.getX(0)-myWorld.getBoutons()[0].getPosition().x),2) + Math.pow((Gdx.input.getY(0)-myWorld.getBoutons()[0].getPosition().y),2)) < myWorld.getBoutons()[0].getTaille()
					|| Math.sqrt(Math.pow((Gdx.input.getX(0)-myWorld.getBoutons()[1].getPosition().x),2) + Math.pow((Gdx.input.getY(0)-myWorld.getBoutons()[1].getPosition().y),2)) < myWorld.getBoutons()[1].getTaille()
					|| Math.sqrt(Math.pow((Gdx.input.getX(0)-myWorld.getBoutons()[2].getPosition().x),2) + Math.pow((Gdx.input.getY(0)-myWorld.getBoutons()[2].getPosition().y),2)) < myWorld.getBoutons()[2].getTaille()
					|| Math.sqrt(Math.pow((Gdx.input.getX(0)-myWorld.getBoutons()[3].getPosition().x),2) + Math.pow((Gdx.input.getY(0)-myWorld.getBoutons()[3].getPosition().y),2)) < myWorld.getBoutons()[3].getTaille()){
						maBille.onClick();
						if(GameWorld.son) {
							maBille.getSound().play();
						}
					}else {
						if(Math.sqrt(Math.pow((Gdx.input.getX(1)-myWorld.getBoutons()[0].getPosition().x),2) + Math.pow((Gdx.input.getY(1)-myWorld.getBoutons()[0].getPosition().y),2)) < myWorld.getBoutons()[0].getTaille()){
							maBille.setCouleur(GameWorld.couleurs[0]);
						}else if(Math.sqrt(Math.pow((Gdx.input.getX(1)-myWorld.getBoutons()[1].getPosition().x),2) + Math.pow((Gdx.input.getY(1)-myWorld.getBoutons()[1].getPosition().y),2)) < myWorld.getBoutons()[1].getTaille()){
							maBille.setCouleur(GameWorld.couleurs[1]);
						}else if(Math.sqrt(Math.pow((Gdx.input.getX(1)-myWorld.getBoutons()[2].getPosition().x),2) + Math.pow((Gdx.input.getY(1)-myWorld.getBoutons()[2].getPosition().y),2)) < myWorld.getBoutons()[2].getTaille()){
							maBille.setCouleur(GameWorld.couleurs[2]);
						}else if(Math.sqrt(Math.pow((Gdx.input.getX(1)-myWorld.getBoutons()[3].getPosition().x),2) + Math.pow((Gdx.input.getY(1)-myWorld.getBoutons()[3].getPosition().y),2)) < myWorld.getBoutons()[3].getTaille()){
							maBille.setCouleur(GameWorld.couleurs[3]);
						}else {
							maBille.onClick();
							if(GameWorld.son) {
								maBille.getSound().play();
							}
						}
					}

			}else {
				if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[0].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[0].getPosition().y),2)) < myWorld.getBoutons()[0].getTaille()){
					maBille.setCouleur(GameWorld.couleurs[0]);
				}else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[1].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[1].getPosition().y),2)) < myWorld.getBoutons()[1].getTaille()){
					maBille.setCouleur(GameWorld.couleurs[1]);
				}else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[2].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[2].getPosition().y),2)) < myWorld.getBoutons()[2].getTaille()){
					maBille.setCouleur(GameWorld.couleurs[2]);
				}else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[3].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[3].getPosition().y),2)) < myWorld.getBoutons()[3].getTaille()){
					maBille.setCouleur(GameWorld.couleurs[3]);
				}else {
					maBille.onClick();
					if(GameWorld.son) {
						maBille.getSound().play();
					}
				}
			}
		}else {
			maBille.onClick();
			if(GameWorld.son) {
				maBille.getSound().play();
			}
		}
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}

}
