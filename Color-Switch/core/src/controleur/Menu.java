package controleur;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gdx.colorswitch.ColorSwitch;

import modele.GameWorld;
import modele.MenuWorld;
import vue.GameRenderer;
import vue.MenuRenderer;

/**
 * le controleur du menu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class Menu implements Screen{
	
	/**
	 * le colorSwitch
	 */
	private ColorSwitch main;
	/**
	 * le modele du menu
	 */
	private MenuWorld world;
	/**
	 * la vue du menu
	 */
	private MenuRenderer renderer;

	
	/**
	 * crée le modele et la vue du menu
	 * @param cs
	 */
	public Menu(ColorSwitch cs) {
		main = cs;
		world = new MenuWorld(cs.isSon());
		renderer = new MenuRenderer(world);
		Gdx.input.setInputProcessor(new MenuInputHandler(main, world));
	}
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * appelé toutes les frames, lance les méthodes du modele et de la vue qui ont besoin d'être appelé toutes les frames
	 * @param delta
	 * correspond au temps que dure une frame
	 */
	@Override
	public void render(float delta) {
		world.update(delta);
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
