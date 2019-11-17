package main;

import java.awt.event.KeyEvent;

public class Controleur implements Cloneable {
	private boolean haut, bas, gauche, droite, space, toucheR, hautwas, baswas, gauchewas, droitewas,reset;
	private int hautInt, basInt, gaucheInt, droiteInt;
	private int toucheHaut, toucheBas, toucheGauche, toucheDroite, toucheNext;
	private boolean tourParTour;
	private boolean IA;

	public Controleur(int toucheHaut, int toucheGauche, int toucheBas, int toucheDroite, int toucheNext,boolean IA) {
		this.toucheNext = toucheNext;
		this.toucheBas = toucheBas;
		this.toucheDroite = toucheDroite;
		this.toucheGauche = toucheGauche;
		this.toucheHaut = toucheHaut;
		this.IA=IA;
	}

	public void tick() {
		droitewas = false;
		hautwas = false;
		baswas = false;
		gauchewas = false;
		if (!haut) {
			hautInt = 0;
		} else {
			hautwas = true;
		}
		if (!bas) {
			basInt = 0;
		} else {
			baswas = true;
		}
		if (!gauche) {
			gaucheInt = 0;
		} else {
			gauchewas = true;
		}
		if (!droite) {
			droiteInt = 0;
		} else {
			droitewas = true;
		}
	}

	public char getDirection() {
		if (Coeur.partieActuelle.getNiveau().getRockford() == null)
			return ' ';
		if(IA){
			return Coeur.ia.direction();
		}
		if (getMax() == 0) {
			return ' ';
		}
		int x = Coeur.partieActuelle.getNiveau().getRockford().getX();
		int y = Coeur.partieActuelle.getNiveau().getRockford().getY();
		if (hautInt == getMax() && (!hautwas || haut)) {
			if (Coeur.partieActuelle.getNiveau().getRockford().placeLibre(x, y - 1)) {
				return 'h';
			} else {
				return get2eme();
			}
		}
		if (droiteInt == getMax() && (!droitewas || droite)) {
			if (Coeur.partieActuelle.getNiveau().getRockford().placeLibre(x + 1, y)) {
				return 'd';
			} else {
				return get2eme();
			}
		}
		if (gaucheInt == getMax() && (!gauchewas || gauche)) {
			if (Coeur.partieActuelle.getNiveau().getRockford().placeLibre(x - 1, y)) {
				return 'g';
			} else {
				return get2eme();
			}
		}
		if (basInt == getMax() && (!baswas || bas)) {
			if (Coeur.partieActuelle.getNiveau().getRockford().placeLibre(x, y + 1)) {
				return 'b';
			} else {
				return get2eme();
			}
		}
		if (getMax() != 0) {
			resetMax();
			return getDirection();
		}
		return ' ';
	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {
		int max = getMax();
		if (e.getKeyCode() == toucheHaut) {
			haut = true;
			hautInt = max + 1;

		}
		if (e.getKeyCode() == toucheDroite) {
			droite = true;
			droiteInt = max + 1;
		}
		if (e.getKeyCode() == toucheGauche) {
			gauche = true;
			gaucheInt = max + 1;
		}
		if (e.getKeyCode() == toucheBas) {
			bas = true;
			basInt = max + 1;
		}
		if (e.getKeyChar() == ' ') {
			space = true;
		}
		if (e.getKeyCode() == toucheNext) {
			reset=true;
		}
		if (tourParTour)
			Coeur.tick();
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == toucheHaut) {
			haut = false;
		}
		if (e.getKeyCode() == toucheDroite) {
			droite = false;
		}
		if (e.getKeyCode() == toucheGauche) {
			gauche = false;
		}
		if (e.getKeyCode() == toucheBas) {
			bas = false;
		}
		if (e.getKeyChar() == ' ') {
			space = false;
		}
		if (e.getKeyCode() == toucheNext) {
			toucheR = false;
		}
	}

	private void resetMax() {
		int max;
		max = getMax();
		if (max == hautInt) {
			hautInt = 0;
		}
		if (max == basInt) {
			basInt = 0;
		}
		if (max == droiteInt) {
			droiteInt = 0;
		}
		if (max == gaucheInt) {
			gaucheInt = 0;
		}
	}

	private int getMax() {
		int max;
		max = 0;
		if (max < hautInt) {
			max = hautInt;
		}
		if (max < basInt) {
			max = basInt;
		}
		if (max < droiteInt) {
			max = droiteInt;
		}
		if (max < gaucheInt) {
			max = gaucheInt;
		}
		return max;
	}

	private char get2eme() {
		int max = getMax();
		int max2 = 0;
		char max2c = ' ';
		if (max2 < hautInt && hautInt != max) {
			max2 = hautInt;
			max2c = 'h';
		}
		if (max2 < basInt && basInt != max) {
			max2c = 'b';
			max2 = basInt;
		}
		if (max2 < droiteInt && droiteInt != max) {
			max2c = 'd';
			max2 = droiteInt;
		}
		if (max2 < gaucheInt && gaucheInt != max) {
			max2c = 'g';
			max2 = gaucheInt;
		}
		return max2c;
	}

	public Controleur clone() {
		return new Controleur(toucheHaut, toucheGauche, toucheBas, toucheDroite, toucheNext,IA);
	}

	public boolean isSpace() {
		return space;
	}
	public boolean isReset(){
		return reset;
	}

	public void setSpace(boolean space) {
		this.space = space;
	}

	public boolean isToucheR() {
		return toucheR;
	}

	public void setTourParTour(boolean tourParTour) {
		this.tourParTour = tourParTour;
	}
}
