package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gdx.colorswitch.ColorSwitch;

import modele.MenuResetWorld;
import vue.MenuResetRenderer;

/**
 * le controleur du menu reset
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuResetScreen implements Screen {
	/**
	 * le colorSwitch
	 */
	private ColorSwitch main;
	/**
	 * le modele du menu reset
	 */
	private MenuResetWorld world;
	/**
	 * la vue du menu reset
	 */
	private MenuResetRenderer renderer;
	
	/**
	 * crée le modele et la vue du menu reset
	 * @param cs
	 * @param score
	 */
	public MenuResetScreen(ColorSwitch cs, int score) {
		main = cs;
		world = new MenuResetWorld(score);
		renderer = new MenuResetRenderer(world);
		Gdx.input.setInputProcessor(new MenuResetInputHandler(main,world));
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * appelé toutes les frames, lance les méthodes de la vue qui ont besoin d'être appelé toutes les frames
	 * @param delta
	 * correspond au temps que dure une frame
	 */
	@Override
	public void render(float delta) {
		renderer.render();		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

}
