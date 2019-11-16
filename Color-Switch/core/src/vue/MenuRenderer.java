package vue;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.gdx.colorswitch.ColorSwitch;

import modele.CercleObstacle;
import modele.MenuWorld;

/**
 * la vue du menu
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */
public class MenuRenderer {
	/**
	 * le modele du menu
	 */
	private MenuWorld myWorld;
	/**
	 * la camera
	 */
    private OrthographicCamera cam;
    /**
     * permet de dessiner des formes simple
     */
    private ShapeRenderer shapeRenderer;
    /**
     * l'image du titre du jeu
     */
    private Texture titleText;
    /**
     * l'image du bouton jouer
     */
    private Texture buttonPlay;
    /**
     * l'image du bouton quitter
     */
    private TextureRegion buttonExit;
    /**
     * l'image du bouton du mode 1
     */
    private TextureRegion buttonGameMode1;
    /**
     * l'image du bouton du mode 2
     */
    private TextureRegion buttonGameMode2;
    /**
     * l'image du bouton son on
     */
    private TextureRegion buttonSound;
    /**
     * l'image du bouton son off
     */
    private TextureRegion buttonSoundOff;
    /**
     * permet de dessiner les images et le texte
     */
    private SpriteBatch batch;
    /**
     * l'angle des boutons qui tournent
     */
    private float angleButton;
    
    
    /**
     * initialise l'affichage du menu
     * @param world
     * le modele du menu
     */
	public MenuRenderer(MenuWorld world) {
			myWorld = world;
	        cam = new OrthographicCamera();
	        cam.setToOrtho(true, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	        shapeRenderer = new ShapeRenderer();
	        shapeRenderer.setProjectionMatrix(cam.combined);
	        titleText = new Texture("colorSwitchTextMenu.png");
	        buttonPlay = new Texture("buttonPlay.png");
	        Texture buttonExitImg = new Texture("buttonExit.png");
	        buttonExit = new TextureRegion(buttonExitImg);
	        Texture buttonSoundImg = new Texture("buttonSound.png");
	        buttonSound = new TextureRegion(buttonSoundImg);
	        Texture buttonSoundOffImg = new Texture("buttonSoundOff.png");
	        buttonSoundOff = new TextureRegion(buttonSoundOffImg);
	        Texture buttonMouseImg = new Texture("buttonMouseGameMode.png");
	        buttonGameMode1 = new TextureRegion(buttonMouseImg);
	        Texture buttonChronoImg = new Texture("buttonLavaGameMode.png");
	        buttonGameMode2 = new TextureRegion(buttonChronoImg);
	        angleButton = 0;
	        batch = new SpriteBatch();
	}
	
	/**
	 * dessine des arcs de cercle
	 * @param num1
	 * l'index de l'obstacle
	 * @param num2
	 * l'index de l'arc de cercle
	 */
	public void drawArc(int num1, int num2) {
		//Gdx.app.log("renderer", String.valueOf(((CercleObstacle) myWorld.getObstacles()[num1]).getArcs()[num2].getRayon()));
    	shapeRenderer.arc(((CercleObstacle) myWorld.getObstacles()[num1]).getArcs()[num2].getPosition().x,
        		((CercleObstacle) myWorld.getObstacles()[num1]).getArcs()[num2].getPosition().y,
        		((CercleObstacle) myWorld.getObstacles()[num1]).getArcs()[num2].getRayon(),
        		((CercleObstacle) myWorld.getObstacles()[num1]).getArcs()[num2].getAngleDepart(),
        		((CercleObstacle) myWorld.getObstacles()[num1]).getArcs()[num2].getAngle());
    }
	
	/**
	 * dessine l'obstacle cercle
	 * @param num
	 * l'index de l'obstacle
	 */
	public void drawCercle(int num) {
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[0].getCouleur());
        drawArc(num, 0);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[1].getCouleur());
        drawArc(num, 1);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[2].getCouleur());
        drawArc(num, 2);
        shapeRenderer.end();
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[3].getCouleur());
        drawArc(num, 3);
        shapeRenderer.end();     
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.setColor(new Color(41f/255f, 41f/255f, 41f/255f, 1));
        shapeRenderer.circle(((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[0].getPosition().x, ((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[0].getPosition().y, ((CercleObstacle) myWorld.getObstacles()[num]).getArcs()[0].getRayon()*0.83f);
        shapeRenderer.end();
	}
	
	/**
	 * methode appelé à chaque frame pour afficher tous les éléments du menu
	 */
	public void render() {
		Gdx.gl.glClearColor(41f/255f, 41f/255f, 41f/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		drawCercle(0);
		drawCercle(1);
		drawCercle(2);
		batch.begin();
		batch.draw(titleText, Gdx.graphics.getWidth()/2-155*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-200*ColorSwitch.ratioTailleEcran, 306*ColorSwitch.ratioTailleEcran, 143*ColorSwitch.ratioTailleEcran);
		batch.end();
		drawCercle(3);
		drawCercle(4);
		batch.begin();
		batch.draw(buttonPlay, myWorld.getBoutons()[0].getPosition().x-86*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-myWorld.getBoutons()[0].getPosition().y-86*ColorSwitch.ratioTailleEcran,
				172*ColorSwitch.ratioTailleEcran, 172*ColorSwitch.ratioTailleEcran);
		batch.end();
		batch.begin();
		angleButton-=2;;
		batch.draw(buttonExit, myWorld.getBoutons()[1].getPosition().x-43*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-myWorld.getBoutons()[1].getPosition().y-43*ColorSwitch.ratioTailleEcran,
				43*ColorSwitch.ratioTailleEcran,43*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,1,1,angleButton);
		batch.end();
		batch.begin();
		batch.draw(buttonGameMode1, myWorld.getBoutons()[2].getPosition().x-43*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-myWorld.getBoutons()[2].getPosition().y-43*ColorSwitch.ratioTailleEcran,
				43*ColorSwitch.ratioTailleEcran,43*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,1,1,angleButton);
		batch.end();
		batch.begin();
		batch.draw(buttonGameMode2, myWorld.getBoutons()[3].getPosition().x-43*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-myWorld.getBoutons()[3].getPosition().y-43*ColorSwitch.ratioTailleEcran,
				43*ColorSwitch.ratioTailleEcran,43*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,1,1,angleButton);
		batch.end();
		batch.begin();
		if(myWorld.isSon()) {
			batch.draw(buttonSound, myWorld.getBoutons()[4].getPosition().x-43*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-myWorld.getBoutons()[4].getPosition().y-43*ColorSwitch.ratioTailleEcran,
					43*ColorSwitch.ratioTailleEcran,43*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,1,1,angleButton);
		}else {
			batch.draw(buttonSoundOff, myWorld.getBoutons()[4].getPosition().x-43*ColorSwitch.ratioTailleEcran, Gdx.graphics.getHeight()-myWorld.getBoutons()[4].getPosition().y-43*ColorSwitch.ratioTailleEcran,
					43*ColorSwitch.ratioTailleEcran,43*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,86*ColorSwitch.ratioTailleEcran,1,1,angleButton);
		}
		
		batch.end();
	}
}
