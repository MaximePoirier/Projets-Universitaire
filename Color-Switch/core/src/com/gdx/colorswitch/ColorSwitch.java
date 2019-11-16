package com.gdx.colorswitch;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import controleur.Menu;

/**
 * permet de changer d'�cran
 * @author Dany Br�geon, Lo�s Monet, Maxime Poirier
 *
 */

public class ColorSwitch extends Game{

	/**
	 * indique si le son est activ�
	 */
    private boolean son;
    /**
     * la hauteur de l'�cran par defaut
     */
	public static final float TailleEcranBase = 816;
	/**
	 * le ratio de l'�cran par rapport � la taille par defaut
	 */
	public static float ratioTailleEcran;
    
	/**
	 * appel� au d�marage de l'application, cr�e le menu
	 */
	@Override
	public void create() {
		Gdx.app.log("ColorSwitch", "created");
		ratioTailleEcran = Gdx.graphics.getHeight()/TailleEcranBase;
		son = true;
		setScreen(new Menu(this));
	}

	/**
	 * retourne si le son est activ� ou pas
	 * @return le booleen son
	 * 
	 */
	public boolean isSon() {
		return son;
	}

	/**
	 * met � jour le boolean son
	 * @param son
	 * le nouveau booleen son
	 */
	public void setSon(boolean son) {
		this.son = son;
	}
	
}
