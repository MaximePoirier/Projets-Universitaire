package com.gdx.colorswitch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import controleur.Menu;

/**
 * permet de changer d'écran
 * @author Dany Brégeon, Loïs Monet, Maxime Poirier
 *
 */

public class ColorSwitch extends Game{

	/**
	 * indique si le son est activé
	 */
    private boolean son;
    /**
     * la hauteur de l'écran par defaut
     */
	public static final float TailleEcranBase = 816;
	/**
	 * le ratio de l'écran par rapport à la taille par defaut
	 */
	public static float ratioTailleEcran;
    
	/**
	 * appelé au démarage de l'application, crée le menu
	 */
	@Override
	public void create() {
		Gdx.app.log("ColorSwitch", "created");
		ratioTailleEcran = Gdx.graphics.getHeight()/TailleEcranBase;
		son = true;
		setScreen(new Menu(this));
	}

	/**
	 * retourne si le son est activé ou pas
	 * @return le booleen son
	 * 
	 */
	public boolean isSon() {
		return son;
	}

	/**
	 * met à jour le boolean son
	 * @param son
	 * le nouveau booleen son
	 */
	public void setSon(boolean son) {
		this.son = son;
	}
	
}
