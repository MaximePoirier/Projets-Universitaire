package controleur;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.gdx.colorswitch.ColorSwitch;

import modele.GameWorld;
import vue.GameRenderer;

/**
 * le controleur de l'écran jeu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class GameScreen implements Screen{
	/**
	 * le colorSwitch
	 */
	private ColorSwitch main;
	/**
	 * le modele du jeu
	 */
	private GameWorld world;
	/**
	 * la vue du jeu
	 */
	private GameRenderer renderer;
	/**
	 * les collisions du jeu
	 */
	private Collision col;
	/**
	 * le temps compter à partir de la mort
	 */
	private float timeDie=0;
	
	/**
	 * crée le modele, la vue et les collisions du jeu
	 * @param cs
	 * le colorSwitch
	 * @param modeDeJeu
	 * le mode de jeu (0, 1 ou 2)
	 */
	public GameScreen(ColorSwitch cs, int modeDeJeu) {
        Gdx.app.log("GameScreen", "Attached");
        main = cs;
        Gdx.app.log("sreen init", String.valueOf(Gdx.graphics.getHeight()));
        world = new GameWorld(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), modeDeJeu, cs.isSon());
        renderer = new GameRenderer(world);
        col = new Collision(world);
        
        Gdx.input.setInputProcessor(new InputHandler(world.getBille(),world));
    }
	@Override
	public void show() {
		Gdx.app.log("GameScreen", "show called");
		
	}

	/**
	 * appelé toutes les frames, lance les méthodes du modele, de la vue et des collisions qui ont besoin d'être appelé toutes les frames
	 * @param delta
	 * correspond au temps que dure une frame
	 */
	@Override
	public void render(float delta) {
		world.update(delta); // GameWorld updates 
		renderer.render(); // GameRenderer renders
		try {
			
			
			if(GameWorld.die) {
				timeDie+=Gdx.graphics.getDeltaTime();
		        if(timeDie>1.5f){
		        		GameWorld.die=false;
		        		dispose();
		        		main.setScreen(new MenuResetScreen(main, world.getScore()));	
		        		
		        }
		
			}else {
				col.update(delta); // Collision updates
			}
			
			
		} catch(Exception e) {
			
			GameWorld.die=true;
			if(timeDie==0) {
				timeDie=Gdx.graphics.getDeltaTime();
			}
			world.saveScore();
			if(main.isSon()) {
				col.getDeadSound().play();
			}
			
		}
		
	}

	@Override
	public void resize(int width, int height) {	
	}

	@Override
	public void pause() {
		Gdx.app.log("GameScreen", "pause called");
	}

	@Override
	public void resume() {
		Gdx.app.log("GameScreen", "resume called");
		
	}

	@Override
	public void hide() {
		
		dispose();
		
		Gdx.app.log("GameScreen", "hide called");    
		
	}

	@Override
	public void dispose() {		
		world.getBille().getSound().dispose();
		world.getBille().getSoundDie().dispose();
		col.getStarSound().dispose();
		col.getColorSwitchSound().dispose();
		col.getDeadSound().dispose();		
	}

}
