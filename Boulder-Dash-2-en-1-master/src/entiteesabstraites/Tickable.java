package entiteesabstraites;

import java.util.ArrayList;
import java.util.List;

import entitees.Amibe;
import entitees.Mur;
import entitees.MurMagique;
import entitees.Vide;
import entitees.tickables.Diamant;
import entitees.tickables.Explosion;
import entitees.tickables.Pierre;
import entitees.tickables.Rockford;

public abstract class Tickable extends Entitee implements Comparable<Tickable> {
	private boolean chute;
	private char direction;
	private List<Class<? extends Entitee>> deplacementsPossibles = new ArrayList<Class<? extends Entitee>>();
	private boolean actionEffectue;
	private boolean mort;

	protected Tickable(int x, int y) {
		super(x, y);
		deplacementsPossibles.add(Vide.class);
	}

	protected abstract boolean contactAutreEntitee(Entitee entitee);

	public void mourir() {
		super.mourir();
		getPartie_actuelle().getListeTickable().remove(this);
		mort=true;
	}

	public abstract void  tick();

	protected void gererChute() {

		if (chute && placeLibre(getX(), getY() + 1)) {
			seDeplacer('b');
		} else if (getMap()[getX()][getY() + 1].getClass().equals(Vide.class)
				|| (getMap()[getX()][getY() + 1].getClass().equals(MurMagique.class)
						&& getMap()[getX()][getY() + 2].getClass().equals(Vide.class))) {
			chute = true;
			gererChute();
		} else {
			if (!placeLibre(getX(), getY() + 1)) {
				chute = false;
			}
			glisser();
		}

	}

	private void glisser() {
		if (getMap()[getX()][getY() + 1].getClass().equals(Pierre.class)
				|| getMap()[getX()][getY() + 1].getClass().equals(Diamant.class)
				|| getMap()[getX()][getY() + 1].getClass().equals(Mur.class)
				|| getMap()[getX()][getY() + 1].getClass().equals(MurMagique.class)) {
			if (getMap()[getX() + 1][getY()].getClass().equals(Vide.class)
					&& getMap()[getX() + 1][getY() + 1].getClass().equals(Vide.class)) {
				seDeplacer('d');
			} else if (getMap()[getX() - 1][getY()].getClass().equals(Vide.class)
					&& getMap()[getX() - 1][getY() + 1].getClass().equals(Vide.class)) {
				seDeplacer('g');
			}
		}
	}

	protected void exploser(char direction, boolean popDiamants) {
		int x = 0;
		int y = 0;
		if (direction == 'h') {
			y = -1;
		} else if (direction == 'd') {
			x = 1;
		} else if (direction == 'g') {
			x = -1;
		} else if (direction == 'b') {
			y = 1;
		}

		for (int i = -1; i < 2; i++) {
			for (int j = -1; j < 2; j++) {
				getMap()[getX() + i + x][getY() + y + j].mourir();
				if (popDiamants)
					getPartie_actuelle().put(new Diamant(getX() + i + x, getY() + j + y));
				else {
					getPartie_actuelle().put(new Explosion(getX() + i + x, getY() + j + y));
				}
			}
		}
	}

	public void seDeplacer(char direction) {
		int x = 0;
		int y = 0;
		if (direction == 'h') {
			y = -1;
		} else if (direction == 'd') {
			x = 1;
		} else if (direction == 'g') {
			x = -1;
		} else if (direction == 'b') {
			y = 1;
		}
		if (placeLibre(getX() + x, getY() + y) && direction != ' ') {
			if (contactAutreEntitee(getMap()[getX() + x][getY() + y])) {
				getMap()[getX() + x][getY() + y] = this;
				getPartie_actuelle().put(new Vide(getX(), getY()));
				setX(getX() + x);
				setY(getY() + y);
			}
		}
	}

	public int compareTo(Tickable t) {
		return t.getNumeroPriorite() - getNumeroPriorite();
	}

	public int getNumeroPriorite() {
		if (getClass().equals(Rockford.class)) {
			return 5;
		} else if (this instanceof Ennemi) {
			return 4;
		} else if (getClass().equals(Pierre.class) || getClass().equals(Diamant.class)) {
			return 3;
		} else if (getClass().equals(Amibe.class)) {
			return 2;
		} else {
			return 1;
		}
	}

	public boolean placeLibre(int x, int y) {
		for (Class<? extends Entitee> entitee : deplacementsPossibles) {
			if (getMap()[x][y].getClass().equals(entitee)) {
				return true;
			}
		}
		return false;
	}

	public boolean isChute() {
		return chute;
	}

	public void setChute(boolean chute) {
		this.chute = chute;
	}

	public char getDirection() {
		return direction;
	}

	public void setDirection(char direction) {
		this.direction = direction;
	}

	public List<Class<? extends Entitee>> getDeplacementsPossibles() {
		return deplacementsPossibles;
	}

	public boolean isActionEffectue() {
		return actionEffectue;
	}

	public void setActionEffectue(boolean actionEffectue) {
		this.actionEffectue = actionEffectue;
	}

	public boolean isMort() {
		return mort;
	}

	public void setMort(boolean mort) {
		this.mort = mort;
	}
}
