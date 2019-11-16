package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.gdx.colorswitch.ColorSwitch;

import modele.GameWorld;
import modele.MenuResetWorld;

/**
 * la vue du menu reset
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuResetRenderer {
	/**
	 * le modele du menu reset
	 */
	private MenuResetWorld myWorld;
	/**
	 * la camera
	 */
    private OrthographicCamera cam;
    /**
     * permet de dessiner des formes simples
     */
    private ShapeRenderer shapeRenderer;
    /**
     * l'image du bandeau score
     */
    private Texture bandeauScore;
    /**
     * l'image du bandeau meilleur score
     */
    private Texture bandeauBestScore;
    /**
     * l'image du bouton retour au menu
     */
    private Texture buttonHome;
    /**
     * l'image du bouton recommencer une partie
     */
    private Texture buttonReset;
    /**
     * permet de dessiner les images et le texte
     */
    private SpriteBatch batch;
    /**
     * la police de caractere du texte qui affiche le score
     */
    private BitmapFont scoreText;
    
    /**
     * initialise l'affichage de la partie
     * @param world
     * le modele du menu reset
     */
	public MenuResetRenderer(MenuResetWorld world) {
			myWorld = world;
	        cam = new OrthographicCamera();
	        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        shapeRenderer = new ShapeRenderer();
	        shapeRenderer.setProjectionMatrix(cam.combined);
	        bandeauScore = new Texture("menuBandeauScore.png");
	        bandeauBestScore = new Texture("menuBandeauBestScore.png");
	        buttonHome = new Texture("buttonHome.png");
	        buttonReset = new Texture("buttonReset.png");
	        batch = new SpriteBatch();
	        scoreText = new BitmapFont(Gdx.files.internal("arial64.fnt"));
	        scoreText.setColor(Color.WHITE);
	        scoreText.getData().setScale(ColorSwitch.ratioTailleEcran);
	}
	
	/**
	 * methode appelé à chaque frame pour afficher tous les éléments du menu reset
	 */
	public void render() {
		Gdx.gl.glClearColor(0.1f, 0.1f, 0.1f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
		batch.draw(buttonHome, myWorld.getBoutons()[1].getPosition().x-myWorld.getBoutons()[1].getTaille(), Gdx.graphics.getHeight()-myWorld.getBoutons()[1].getPosition().y-myWorld.getBoutons()[1].getTaille(),
				myWorld.getBoutons()[1].getTaille()*2, myWorld.getBoutons()[1].getTaille()*2);
		batch.end();
		batch.begin();
		batch.draw(buttonReset, myWorld.getBoutons()[0].getPosition().x-myWorld.getBoutons()[0].getTaille(), Gdx.graphics.getHeight()-myWorld.getBoutons()[0].getPosition().y-myWorld.getBoutons()[0].getTaille(),
				myWorld.getBoutons()[0].getTaille()*2, myWorld.getBoutons()[0].getTaille()*2);
		batch.end();
		batch.begin();
		if(myWorld.getScore()<10) {
			scoreText.draw(batch, String.valueOf(myWorld.getScore()), Gdx.graphics.getWidth()/2-18*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/1.55f);
		}else {
			scoreText.draw(batch, String.valueOf(myWorld.getScore()), Gdx.graphics.getWidth()/2-36*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/1.55f);
		}
        batch.end();
		batch.begin();
		int offset = 35;
		Preferences ScorePref = Gdx.app.getPreferences("ScorePref");
		if(GameWorld.modeDeJeu==0) {
			if(ScorePref.getInteger("score",0)<10) {
				offset=18;
			}
			scoreText.draw(batch, String.valueOf(Gdx.app.getPreferences("ScorePref").getInteger("score",0)), Gdx.graphics.getWidth()/2-offset*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/2.2f);
		}else if(GameWorld.modeDeJeu==1) {
			if(ScorePref.getInteger("score1",0)<10) {
				offset=18;
			}
			scoreText.draw(batch, String.valueOf(Gdx.app.getPreferences("ScorePref").getInteger("score1",0)), Gdx.graphics.getWidth()/2-offset*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/2.2f);
		}else if(GameWorld.modeDeJeu==2) {
			if(ScorePref.getInteger("score2",0)<10) {
				offset=18;
			}
			scoreText.draw(batch, String.valueOf(Gdx.app.getPreferences("ScorePref").getInteger("score2",0)), Gdx.graphics.getWidth()/2-offset*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/2.2f);
		}

        batch.end();
		batch.begin();
		batch.draw(bandeauScore, Gdx.graphics.getWidth()/2-272*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/1.5f, 544*ColorSwitch.ratioTailleEcran, 49*ColorSwitch.ratioTailleEcran);
		batch.end();
		batch.begin();
		batch.draw(bandeauBestScore, Gdx.graphics.getWidth()/2-272*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()/2, 544*ColorSwitch.ratioTailleEcran, 49*ColorSwitch.ratioTailleEcran);
		batch.end();
	}
}
