package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.gdx.colorswitch.ColorSwitch;
import modele.MenuWorld;

/**
 * gère les inputs dans le menu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuInputHandler implements InputProcessor{

	/**
	 * le modele du menu
	 */
	private MenuWorld myWorld;
	/**
	 * le colorSwitch
	 */
	private ColorSwitch main;
	
	/**
	 * initialise les attributs
	 * @param cs
	 * le colorSwitch
	 * @param world
	 * le modele du menu
	 */
	public MenuInputHandler(ColorSwitch cs, MenuWorld world) {
		myWorld = world;
		main = cs;
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

	//gère les clics sur les boutons du menu
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[0].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[0].getPosition().y),2)) < myWorld.getBoutons()[0].getTaille()){
			main.setScreen(new GameScreen(main,0));
		}
		else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[1].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[1].getPosition().y),2)) < myWorld.getBoutons()[1].getTaille()){
			Gdx.app.exit();
		}
		else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[4].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[4].getPosition().y),2)) < myWorld.getBoutons()[4].getTaille()){
			if(main.isSon()) {
				main.setSon(false);
				myWorld.setSon(false);
			}else {
				main.setSon(true);
				myWorld.setSon(true);
			}
		}
		else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[2].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[2].getPosition().y),2)) < myWorld.getBoutons()[2].getTaille()){
			main.setScreen(new GameScreen(main,1));
		}
		else if(Math.sqrt(Math.pow((Gdx.input.getX()-myWorld.getBoutons()[3].getPosition().x),2) + Math.pow((Gdx.input.getY()-myWorld.getBoutons()[3].getPosition().y),2)) < myWorld.getBoutons()[3].getTaille()){
			main.setScreen(new GameScreen(main,2));
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
