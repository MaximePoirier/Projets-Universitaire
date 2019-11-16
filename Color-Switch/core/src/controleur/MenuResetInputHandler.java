package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.gdx.colorswitch.ColorSwitch;

import modele.GameWorld;
import modele.MenuResetWorld;

/**
 * gère les inputs dans le menu reset
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuResetInputHandler implements InputProcessor{
	/**
	 * le colorSwitch
	 */
	private ColorSwitch main;
	/**
	 * le modele du menu reset
	 */
	private MenuResetWorld myWorld;
	
	/**
	 * initialise les attributs
	 * @param cs
	 * le colorSwitch
	 * @param world
	 * le modele du menu reset
	 */
	public MenuResetInputHandler (ColorSwitch cs, MenuResetWorld world) {
		main = cs;
		myWorld = world;
	}

	@Override
	public boolean keyDown(int keycode) {
		// TODO Auto-generated method stub
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

	//gère les clics sur les boutons du menu reset
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[0].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[0].getPosition().y),2)) < myWorld.getBoutons()[0].getTaille()){
			main.setScreen(new GameScreen(main,GameWorld.modeDeJeu));
		}
		else if (Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[1].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[1].getPosition().y),2)) < myWorld.getBoutons()[1].getTaille()){
			main.setScreen(new Menu(main));
		}
		return false;
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
